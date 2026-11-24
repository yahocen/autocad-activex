// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.testsupport;

import io.github.yahocen.acad.Application;
import io.github.yahocen.acad.common.AcadException;
import io.github.yahocen.acad.common.Internal;
import io.github.yahocen.acad.constant.AutoCadVersion;
import io.github.yahocen.acad.document.Document;
import io.github.yahocen.acad.document.base.Point;
import org.junit.jupiter.api.Assumptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 多版本 AutoCAD 测试入口。
 * <p>
 * 版本选择规则（按优先级）：
 * <ol>
 *   <li>{@code -Dacad.versions=24.3,25}：逗号分隔的 ProgID 后缀或枚举名（AUTOCAD_2024）</li>
 *   <li>默认：探测本机注册表，跑所有已安装版本</li>
 *   <li>本机无 AutoCAD：assumeTrue 跳过，不报错（CI 无 CAD 环境友好）</li>
 * </ol>
 * 辅助开关：{@code -Dacad.visible=true} 让被测 CAD 窗口可见，便于人工观察；
 * {@code -Dacad.launch=exe} 强制全部走「直接启动 exe + GetActiveObject」路径。
 * </p>
 * <p>
 * 启动策略（两台常见坑的自愈）：
 * <ol>
 *   <li>优先 ProgID CoCreate；连上后核对 Version 属性与 ProgID 版本号。</li>
 *   <li>版本不符（旧版本卸载后 ProgID 的 CLSID 被新版本重定向）或 CoCreate 失败
 *       （如 VMR 等工具把 DOTNET_ROOT 指向缺少 .NET 8 的目录，AutoCAD 2025/2026
 *       启动时弹 .NET 安装提示阻断 COM 初始化）时，回退为直接启动该版本的 acad.exe：
 *       清掉 DOTNET_ROOT 环境变量后启动子进程，再用 GetActiveObject(ProgID) 连接。
 *       exe 路径来自扫描 {@code C:\Program Files\Autodesk\AutoCAD <版本>\**\acad.exe}，
 *       或用 {@code -Dacad.exe.<后缀>=路径} 显式指定。</li>
 * </ol>
 * </p>
 */
public final class AcadTestHarness {

    private static final Path AUTODESK_BASE = Path.of("C:\\Program Files\\Autodesk");

    private AcadTestHarness() {
    }

    /**
     * 解析要测试的 AutoCAD 版本列表。
     */
    public static List<AutoCadVersion> selectedVersions() {
        String prop = System.getProperty("acad.versions", "").trim();
        if (prop.isEmpty() || prop.equalsIgnoreCase("all")) {
            List<AutoCadVersion> installed = AutoCadVersion.detectInstalled();
            Assumptions.assumeTrue(!installed.isEmpty(),
                    "本机未探测到可启动的 AutoCAD（注册表无有效的 AutoCAD.Application.* ProgID），跳过多版本测试");
            return installed;
        }
        List<AutoCadVersion> selected = new ArrayList<>();
        for (String token : prop.split(",")) {
            String t = token.trim();
            if (t.isEmpty()) {
                continue;
            }
            selected.add(parse(t));
        }
        return selected;
    }

    private static AutoCadVersion parse(String token) {
        try {
            return AutoCadVersion.fromName(token);
        } catch (IllegalArgumentException e) {
            return AutoCadVersion.fromProgId(token);
        }
    }

    /**
     * 被测 CAD 是否可见（默认隐藏提速）。
     */
    public static boolean visible() {
        return Boolean.getBoolean("acad.visible");
    }

    /**
     * 启动指定版本的 AutoCAD、新建文档、执行测试体，最后关文档、退 CAD。
     * <p>
     * 测试体里抛出的异常（含断言失败）会先走清理再原样上抛；
     * 图元级别的失败建议用 {@link CadSession#step} 收集，不要直接抛。
     * 退出后等待实例从 ROT 撤销，防止下个测试的复用预检连到濒死实例
     * （COM 对象已断开，表现为 Invoke 报"句柄 ID"错误）。
     * </p>
     */
    public static void withNewCad(AutoCadVersion version, Consumer<CadSession> body) {
        Point.resetBasePoint();
        System.out.printf("==== 启动 %s (%s) ====%n", version.getRelease(), version.getProgId());
        Application app = launch(version);
        CadSession session = null;
        try {
            Document doc = app.getDocuments().add();
            try {
                session = new CadSession(version, app, doc);
                body.accept(session);
            } finally {
                closeQuietly(doc);
            }
        } finally {
            quitAndWaitGone(app, version);
            if (session != null) {
                System.out.println("==== " + session.summary() + " ====");
            }
        }
    }

    /**
     * 供直接持有 Application 的测试使用：退出并等待实例从 ROT 消失（最多 60 秒）。
     */
    public static void shutdown(Application app, AutoCadVersion version) {
        quitAndWaitGone(app, version);
    }

    /** CoCreate 失败过的 ProgID（同 JVM 内不再重试，直启 exe 提速，如 .NET 弹窗阻断的 2026） */
    private static final java.util.Set<String> COCREATE_FAILED = java.util.concurrent.ConcurrentHashMap.newKeySet();

    /**
     * 智能启动：优先复用运行实例 → ProgID CoCreate → 直接启动 exe 再连接。
     * <p>
     * ProgID 被劫持的版本（{@link AutoCadVersion#isProgIdHijacked()}，卸载残留被新版本重定向）
     * 跳过 CoCreate——以错误 CLSID 激活会让 AutoCAD 自行崩溃留下僵尸进程占住 ROT。
     * </p>
     */
    public static Application launch(AutoCadVersion version) {
        String suffix = version.getProgId().substring("AutoCAD.Application.".length());
        // 1. 复用预检：已有本版本的健康运行实例则直接连接（免重复拉起，也绕开 CoCreate 卡顿）
        Application running = tryConnectRunning(version, suffix);
        if (running != null) {
            System.out.println("复用已运行的 " + version.getRelease() + " 实例");
            return running;
        }
        boolean hijacked = version.isProgIdHijacked();
        if (hijacked) {
            System.out.println("ProgID " + version.getProgId() + " 已被其他版本劫持，跳过 CoCreate 直接定位 exe");
        }
        if (!hijacked && !"exe".equalsIgnoreCase(System.getProperty("acad.launch"))
                && !COCREATE_FAILED.contains(version.getProgId())) {
            try {
                Application app = Application.createNewAcad(version, visible());
                if (app.getVersion().startsWith(suffix)) {
                    return app;
                }
                // ProgID 被重定向到别的版本（如卸载旧版后 CLSID 指到新版 exe）：
                // 该实例与本目标版本共用同一 CLSID 的 ROT 项，必须等它退干净再直启 exe，
                // 否则 GetActiveObject 会连到这个濒死实例（Can't map names to dispid）
                System.out.printf("ProgID %s 启动的实例版本不符（%s），回退直接启动 exe%n",
                        version.getProgId(), app.getVersion());
                quitAndWaitGone(app, version);
            } catch (AcadException e) {
                COCREATE_FAILED.add(version.getProgId());
                System.out.println("ProgID 启动失败，回退直接启动 exe：" + e.getMessage());
            }
        }
        return startExeAndConnect(version, resolveExe(version));
    }

    private static Application tryConnectRunning(AutoCadVersion version, String suffix) {
        try {
            Application app = Application.connectToActiveAcad(version);
            if (app.getVersion().startsWith(suffix)) {
                app.setVisible(visible());
                return app;
            }
        } catch (Exception noRunning) {
            // 无运行实例
        }
        return null;
    }

    /**
     * 退出错误实例并等待其从 ROT 消失（连不上即视为已退出），最多 60 秒。
     */
    private static void quitAndWaitGone(Application app, AutoCadVersion version) {
        quitQuietly(app);
        for (int i = 0; i < 60; i++) {
            try {
                Application probe = Application.connectToActiveAcad(version);
                // 还能连上：再踢一次直到退干净
                quitQuietly(probe);
            } catch (Exception gone) {
                return;
            }
            Internal.sleep(1000);
        }
        System.out.println("等待错误实例退出超时（继续尝试直启 exe）");
    }

    /**
     * 解析指定版本的 acad.exe：先取 -Dacad.exe.<版本后缀>，再扫描 Autodesk 默认安装目录。
     */
    private static Path resolveExe(AutoCadVersion version) {
        String suffix = version.getProgId().substring("AutoCAD.Application.".length());
        String override = System.getProperty("acad.exe." + suffix);
        if (override != null && !override.isBlank()) {
            return Path.of(override.trim());
        }
        try (var walk = Files.walk(AUTODESK_BASE, 3)) {
            Path exe = walk.filter(p -> p.getFileName().toString().equalsIgnoreCase("acad.exe"))
                    .filter(p -> p.toString().contains(version.getRelease()))
                    .findFirst()
                    .orElse(null);
            if (exe != null) {
                return exe;
            }
        } catch (IOException ignore) {
            // 目录不存在等，走下面的报错
        }
        throw new IllegalStateException("找不到 " + version.getRelease()
                + " 的 acad.exe，可用 -Dacad.exe." + suffix + "=<路径> 指定");
    }

    /**
     * 直接启动 exe（清掉 DOTNET_ROOT，规避 .NET 版本管理器导致 2025/2026 弹窗），
     * 再用 GetActiveObject(ProgID) 连接并核对版本，最多等待 150 秒。
     * 连到错误版本（同 CLSID 的残留实例）时踢掉重试。
     */
    private static Application startExeAndConnect(AutoCadVersion version, Path exe) {
        System.out.println("直接启动 " + exe);
        String suffix = version.getProgId().substring("AutoCAD.Application.".length());
        try {
            ProcessBuilder pb = new ProcessBuilder(exe.toString());
            pb.environment().keySet().removeIf(k -> k.startsWith("DOTNET_ROOT"));
            Process process = pb.start();
            for (int i = 0; i < 150; i++) {
                try {
                    Application app = Application.connectToActiveAcad(version);
                    try {
                        if (app.getVersion().startsWith(suffix)) {
                            app.setVisible(visible());
                            return app;
                        }
                        // 连到了同 CLSID 的错误版本残留实例：踢掉，等正确的注册上来
                        System.out.println("连到错误版本实例（" + app.getVersion() + "），踢掉重试");
                        quitQuietly(app);
                    } catch (Exception brokenProxy) {
                        // 濒死代理：当作未就绪，下一轮重试
                    }
                } catch (Exception notYet) {
                    // GetActiveObject 未命中：目标实例尚未注册 ROT
                }
                if (!process.isAlive()) {
                    throw new IllegalStateException("acad.exe 进程已退出：" + exe);
                }
                Internal.sleep(1000);
            }
            process.destroy();
            throw new IllegalStateException("连接 " + version.getRelease() + " 超时（150 秒）");
        } catch (IOException e) {
            throw new IllegalStateException("启动 acad.exe 失败：" + exe, e);
        }
    }

    private static void closeQuietly(Document doc) {
        try {
            doc.close(false, null);
        } catch (Exception e) {
            System.out.println("关闭文档失败（忽略）：" + e);
        }
    }

    private static void quitQuietly(Application app) {
        // 先关掉所有文档（含 CAD 启动时自带的空白图），否则 QUIT 在隐藏实例上
        // 可能弹"保存修改"模态框，模态框不可见 → 进程卡成僵尸并占住 ROT 项
        try {
            var docs = app.getDocuments();
            int guard = 20;
            while (docs.count() > 0 && guard-- > 0) {
                int before = docs.count();
                try {
                    docs.item(0).close(false, null);
                } catch (Exception e) {
                    break;
                }
                if (docs.count() >= before) {
                    break;
                }
            }
        } catch (Exception ignore) {
            // 拿不到文档集合：直接尝试退出
        }
        try {
            app.quit();
        } catch (Exception e) {
            System.out.println("退出 AutoCAD 失败（忽略，进程可能残留）：" + e);
        }
    }
}

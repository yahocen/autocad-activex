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
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 多版本 AutoCAD 测试入口。
 * <p>
 * 版本选择：{@code -Dacad.versions=24.3,25}（ProgID 后缀或枚举名）；默认探测本机全部已装版本；
 * 无 AutoCAD 时 assumeTrue 跳过。{@code -Dacad.visible=true} 显示窗口；
 * {@code -Dacad.launch=exe} 强制直启 exe；{@code -Dacad.exe.<后缀>=路径} 指定 exe。
 * </p>
 * <p>
 * <b>安全约束（共享机器，用户可能开着自己的 CAD）</b>：本 harness 只创建并清理<b>自己启动</b>的
 * 实例——用 ProcessHandle 快照差集 / Process.pid() 跟踪_owned_ PID，绝不复用 GetActiveObject
 * 命中的外来运行实例，也绝不关闭/退出/强杀非自己启动的进程。清理仅针对 tracked PID。
 * </p>
 * <p>
 * 启动策略：ProgID CoCreate（AutoCAD 多实例，必为新进程）→ 核对 Version；版本不符（ProgID 被
 * 卸载残留重定向）或 CoCreate 失败（如 DOTNET_ROOT 致 2025/2026 弹 .NET 窗）时，直启该版本
 * acad.exe（清 DOTNET_ROOT）+ GetActiveObject 连接。被劫持 ProgID 跳过 CoCreate 以免崩溃留僵尸。
 * </p>
 */
public final class AcadTestHarness {

    private static final Path AUTODESK_BASE = Path.of("C:\\Program Files\\Autodesk");

    /** 本 harness 自己启动的 acad.exe PID（清理只针对这些，绝不触碰用户实例）。 */
    private static final Set<Long> OWN_PIDS = ConcurrentHashMap.newKeySet();

    /** CoCreate 失败过的 ProgID（同 JVM 内不再重试，直启 exe 提速）。 */
    private static final Set<String> COCREATE_FAILED = ConcurrentHashMap.newKeySet();

    private AcadTestHarness() {
    }

    public static List<AutoCadVersion> selectedVersions() {
        String prop = System.getProperty("acad.versions", "").trim();
        if (prop.isEmpty() || prop.equalsIgnoreCase("all")) {
            List<AutoCadVersion> installed = AutoCadVersion.detectInstalled();
            Assumptions.assumeTrue(!installed.isEmpty(),
                    "本机未探测到可启动的 AutoCAD，跳过多版本测试");
            return installed;
        }
        List<AutoCadVersion> selected = new ArrayList<>();
        for (String token : prop.split(",")) {
            String t = token.trim();
            if (!t.isEmpty()) {
                selected.add(parse(t));
            }
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

    public static boolean visible() {
        return Boolean.getBoolean("acad.visible");
    }

    /**
     * 启动指定版本 AutoCAD、新建文档、执行测试体，最后清理自己启动的实例。
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
            cleanup(app);
            if (session != null) {
                System.out.println("==== " + session.summary() + " ====");
            }
        }
    }

    /** 供直接持有 Application 的测试使用：退出并强杀自己启动的实例。 */
    public static void shutdown(Application app, AutoCadVersion version) {
        cleanup(app);
    }

    /**
     * 启动指定版本的新实例（绝不复用外来运行实例），并跟踪其 PID。
     */
    public static Application launch(AutoCadVersion version) {
        String suffix = version.getProgId().substring("AutoCAD.Application.".length());
        boolean hijacked = version.isProgIdHijacked();
        if (hijacked) {
            System.out.println("ProgID " + version.getProgId() + " 已被劫持，跳过 CoCreate 直启 exe");
        }
        // R25+（2025/2026）需 .NET 8 桌面运行时；若 DOTNET_ROOT 指向的目录缺 .NET 8，
        // CoCreate 会弹 ".NET Installation Required" 模态框阻断 COM 初始化（卡约 120 秒后失败，
        // 还留下坏实例干扰后续测试）。预判跳过，直接走清掉 DOTNET_ROOT 的 exe 路径。
        boolean dotnetBlocked = needsNet8(version) && dotnetRootLacksNet8();
        if (dotnetBlocked) {
            System.out.println("DOTNET_ROOT 缺 .NET 8，" + version.getRelease() + " 跳过 CoCreate 直启 exe");
        }
        if (!hijacked && !dotnetBlocked && !"exe".equalsIgnoreCase(System.getProperty("acad.launch"))
                && !COCREATE_FAILED.contains(version.getProgId())) {
            Set<Long> before = snapshotAcadPids();
            try {
                Application app = Application.createNewAcad(version, visible());
                trackNewPids(before); // CoCreate 必为新进程，差集即我们启动的
                if (app.getVersion().startsWith(suffix)) {
                    return app;
                }
                // ProgID 被重定向到别版本：关掉这个我们刚启动的错误实例，再直启正确 exe
                System.out.printf("ProgID %s 启动的实例版本不符（%s），回退直启 exe%n",
                        version.getProgId(), app.getVersion());
                quitQuietly(app);
            } catch (AcadException e) {
                COCREATE_FAILED.add(version.getProgId());
                System.out.println("ProgID 启动失败，回退直启 exe：" + e.getMessage());
            }
        }
        return startExeAndConnect(version, resolveExe(version));
    }

    /**
     * 直启 exe（清 DOTNET_ROOT）+ GetActiveObject 连接并核对版本。跟踪进程 PID。
     * 连到错误版本时只重试不强杀（可能是用户实例）；超时则安全失败。
     */
    private static Application startExeAndConnect(AutoCadVersion version, Path exe) {
        System.out.println("直接启动 " + exe);
        String suffix = version.getProgId().substring("AutoCAD.Application.".length());
        try {
            ProcessBuilder pb = new ProcessBuilder(exe.toString());
            pb.environment().keySet().removeIf(k -> k.startsWith("DOTNET_ROOT"));
            Process process = pb.start();
            OWN_PIDS.add(process.pid());
            for (int i = 0; i < 150; i++) {
                try {
                    Application app = Application.connectToActiveAcad(version);
                    if (app.getVersion().startsWith(suffix)) {
                        app.setVisible(visible());
                        return app;
                    }
                    // 命中的是别版本（可能用户实例）：不强杀，等我们的实例注册上来
                } catch (Exception notYet) {
                    // 尚未注册 ROT
                }
                if (!process.isAlive()) {
                    throw new IllegalStateException("acad.exe 进程已退出：" + exe);
                }
                Internal.sleep(1000);
            }
            throw new IllegalStateException("连接 " + version.getRelease() + " 超时（150 秒）");
        } catch (IOException e) {
            throw new IllegalStateException("启动 acad.exe 失败：" + exe, e);
        }
    }

    /**
     * 清理：退出我们启动的实例（关其文档 + quit），再强杀仍存活的 tracked PID。
     * 只触碰 OWN_PIDS，绝不连接/关闭/强杀外来实例。
     */
    private static void cleanup(Application app) {
        quitQuietly(app);
        forceKillOwnPids();
    }

    /** 强杀本 harness 自己启动且仍存活的 acad.exe（按 tracked PID，非时间 pattern）。 */
    public static void forceKillOwnPids() {
        for (Long pid : OWN_PIDS) {
            ProcessHandle.of(pid).ifPresent(p -> {
                if (p.isAlive()) {
                    p.destroyForcibly();
                }
            });
        }
    }

    private static Set<Long> snapshotAcadPids() {
        return ProcessHandle.allProcesses()
                .filter(p -> p.info().command().map(c -> c.toLowerCase().contains("acad.exe")).orElse(false))
                .map(ProcessHandle::pid)
                .collect(Collectors.toSet());
    }

    private static void trackNewPids(Set<Long> before) {
        Set<Long> now = snapshotAcadPids();
        now.removeAll(before);
        OWN_PIDS.addAll(now);
    }

    /** R25+（2025 起）的 AutoCAD 依赖 .NET 8 桌面运行时。 */
    private static boolean needsNet8(AutoCadVersion version) {
        return version.ordinal() >= AutoCadVersion.AUTOCAD_2025.ordinal();
    }

    /** DOTNET_ROOT 指向的目录是否缺少 .NET 8 桌面运行时（缺则 CoCreate 必被 .NET 弹窗阻断）。 */
    private static boolean dotnetRootLacksNet8() {
        String root = System.getenv("DOTNET_ROOT");
        if (root == null || root.isBlank()) {
            return false; // 未设 DOTNET_ROOT，走标准注册表解析，通常正常
        }
        Path desktop = Path.of(root, "shared", "Microsoft.WindowsDesktop.App");
        try (var dirs = Files.list(desktop)) {
            return dirs.noneMatch(p -> p.getFileName().toString().startsWith("8."));
        } catch (IOException e) {
            return true; // 目录不存在/不可读：视为缺 .NET 8
        }
    }

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
            // 目录不存在等
        }
        throw new IllegalStateException("找不到 " + version.getRelease()
                + " 的 acad.exe，可用 -Dacad.exe." + suffix + "=<路径> 指定");
    }

    private static void closeQuietly(Document doc) {
        try {
            doc.close(false, null);
        } catch (Exception e) {
            System.out.println("关闭文档失败（忽略）：" + e);
        }
    }

    /** 关闭我们启动实例的文档并退出（该实例只含测试文档，安全）。 */
    private static void quitQuietly(Application app) {
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
            System.out.println("退出 AutoCAD 失败（忽略，由 tracked PID 兜底）：" + e);
        }
    }
}

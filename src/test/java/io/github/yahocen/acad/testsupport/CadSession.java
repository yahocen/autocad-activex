// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.testsupport;

import io.github.yahocen.acad.Application;
import io.github.yahocen.acad.common.Internal;
import io.github.yahocen.acad.constant.AutoCadVersion;
import io.github.yahocen.acad.document.Document;
import io.github.yahocen.acad.document.ModelSpace;
import io.github.yahocen.jacob.com.ComFailException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * 一次「AutoCAD 版本 × 文档」的测试会话。
 * <p>
 * 持有 CAD 连接与应用/文档/模型空间引用，把每个图元步骤的通过/失败收集到结果表，
 * 最后由 {@link #assertNoRequiredFailures()} 汇总断言——单个图元失败不会中断同版本
 * 其余图元的执行，保证一次运行拿到完整的兼容性矩阵。
 * </p>
 */
public final class CadSession {

    private final AutoCadVersion version;
    private final Application application;
    private final Document document;
    private final ModelSpace modelSpace;

    private final List<String> passed = new ArrayList<>();
    /** 必须支持的图元：失败即测试失败 */
    private final Map<String, String> failures = new LinkedHashMap<>();
    /** 版本敏感/依赖外部条件的图元：失败只记录，不判失败 */
    private final Map<String, String> warnings = new LinkedHashMap<>();

    /** 图元横向布局游标：每创建一个图元右移一段，保存的 DWG 里各图元不重叠 */
    private double cursorX = 0;

    public CadSession(AutoCadVersion version, Application application, Document document) {
        this.version = version;
        this.application = application;
        this.document = document;
        this.modelSpace = document.getModelSpace();
    }

    public AutoCadVersion version() {
        return version;
    }

    public Application application() {
        return application;
    }

    public Document document() {
        return document;
    }

    public ModelSpace modelSpace() {
        return modelSpace;
    }

    /** 取下一个图元的布局 X 坐标（每次右移 40） */
    public double nextX() {
        double x = cursorX;
        cursorX += 40;
        return x;
    }

    /**
     * 执行一个必须通过的图元步骤，失败记录到结果表。
     */
    public void step(String label, Runnable body) {
        run(label, body, failures);
    }

    /**
     * 执行一个允许失败的图元步骤（依赖外部文件、命令行交互或版本差异），
     * 失败只记录为 warning，不影响测试结论。
     */
    public void stepOptional(String label, Runnable body) {
        run(label, body, warnings);
    }

    private void run(String label, Runnable body, Map<String, String> bucket) {
        long start = System.currentTimeMillis();
        try {
            withBusyRetry(body);
            passed.add(label);
            System.out.printf("[%-14s] OK   %-28s %5d ms%n", version.getRelease(), label,
                    System.currentTimeMillis() - start);
        } catch (Throwable t) {
            bucket.put(label, t.toString());
            String tag = (bucket == warnings) ? "WARN " : "FAIL ";
            System.out.printf("[%-14s] %s%-28s %s%n", version.getRelease(), tag, label, t);
        }
    }

    /**
     * COM 忙重试：AutoCAD 启动初期消息泵可能来不及响应，抛出
     * "call was rejected by callee"（ComFailException），间隔重试即可恢复。
     */
    private static void withBusyRetry(Runnable body) {
        ComFailException last = null;
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                body.run();
                return;
            } catch (ComFailException e) {
                last = e;
                Internal.sleep(400L * attempt);
            }
        }
        throw last;
    }

    /**
     * 断言本版本没有必须失败的图元，有则用汇总信息使当前测试失败。
     */
    public void assertNoRequiredFailures() {
        if (failures.isEmpty()) {
            return;
        }
        fail(summary());
    }

    /**
     * 汇总本版本运行结果：通过数、失败明细、告警明细。
     */
    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("AutoCAD ").append(version.getRelease()).append(" (").append(version.getProgId()).append(')')
                .append("：通过 ").append(passed.size()).append(" 项");
        if (!failures.isEmpty()) {
            sb.append("，失败 ").append(failures.size()).append(" 项：");
            failures.forEach((k, v) -> sb.append("\n  - ").append(k).append(" => ").append(v));
        }
        if (!warnings.isEmpty()) {
            sb.append("，告警 ").append(warnings.size()).append(" 项：");
            warnings.forEach((k, v) -> sb.append("\n  ~ ").append(k).append(" => ").append(v));
        }
        return sb.toString();
    }
}

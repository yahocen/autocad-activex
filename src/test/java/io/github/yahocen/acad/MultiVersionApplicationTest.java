// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad;

import io.github.yahocen.acad.common.Internal;
import io.github.yahocen.acad.document.Document;
import io.github.yahocen.acad.document.ModelSpace;
import io.github.yahocen.acad.testsupport.AcadTestHarness;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * 多版本 AutoCAD 连接与生命周期测试。
 * <p>
 * 验证 {@link Application#createNewAcad(io.github.yahocen.acad.constant.AutoCadVersion, boolean)}
 * 与 {@link Application#connectToActiveAcad(io.github.yahocen.acad.constant.AutoCadVersion)}
 * 在本机每个已安装版本上的基本行为：启动、版本号核对、新建/关闭文档、连接运行实例、退出。
 * 启动走 {@link AcadTestHarness#launch} 的智能回退（ProgID 失效/被重定向时直接启动 exe）。
 * </p>
 */
class MultiVersionApplicationTest {

    /**
     * 每版本：新建实例 → 核对 Version 属性 → 新建文档 → 添加图元 → 关闭 → 退出。
     */
    @TestFactory
    List<DynamicTest> applicationLifecyclePerVersion() {
        return AcadTestHarness.selectedVersions().stream()
                .map(v -> DynamicTest.dynamicTest(
                        "应用生命周期 " + v.getRelease() + " (" + v.getProgId() + ")",
                        () -> AcadTestHarness.withNewCad(v, s -> {
                            // Version 属性应与 ProgID 版本号一致（如 "24.3s (LMS Tech)" 以 "24.3" 开头）
                            String suffix = v.getProgId().substring("AutoCAD.Application.".length());
                            assertTrue(s.application().getVersion().startsWith(suffix),
                                    "Version 属性 " + s.application().getVersion() + " 与 ProgID " + v.getProgId() + " 不符");
                            s.step("AddLine", () -> assertNotNull(
                                    s.modelSpace().addLine(
                                            io.github.yahocen.acad.document.base.Point.of(0, 0),
                                            io.github.yahocen.acad.document.base.Point.of(100, 0))));
                            s.assertNoRequiredFailures();
                        })))
                .toList();
    }

    /**
     * 每版本：新建实例后用 connectToActiveAcad(version) 重连运行实例，核对版本一致。
     * <p>
     * 新实例在 ROT 注册可能延迟，最多等待 15 秒重试。
     * </p>
     */
    @TestFactory
    List<DynamicTest> connectToRunningInstancePerVersion() {
        return AcadTestHarness.selectedVersions().stream()
                .map(v -> DynamicTest.dynamicTest(
                        "连接运行实例 " + v.getRelease() + " (" + v.getProgId() + ")",
                        () -> {
                            Application app = AcadTestHarness.launch(v);
                            try {
                                String suffix = v.getProgId().substring("AutoCAD.Application.".length());
                                Application connected = null;
                                RuntimeException last = null;
                                for (int i = 0; i < 15 && connected == null; i++) {
                                    try {
                                        connected = Application.connectToActiveAcad(v);
                                    } catch (RuntimeException e) {
                                        last = e;
                                        Internal.sleep(1000);
                                    }
                                }
                                if (connected == null) {
                                    fail("15 秒内未能连接到运行中的 " + v.getRelease() + " 实例：" + last);
                                    return;
                                }
                                assertTrue(connected.getVersion().startsWith(suffix),
                                        "连接后的 Version " + connected.getVersion() + " 与 " + v.getProgId() + " 不符");
                                // 连接实例上做一次实际操作：新建并关闭文档
                                Document doc = connected.getDocuments().add();
                                try {
                                    ModelSpace ms = doc.getModelSpace();
                                    assertEquals(0, ms.count());
                                } finally {
                                    doc.close(false, null);
                                }
                            } finally {
                                AcadTestHarness.shutdown(app, v);
                            }
                        }))
                .toList();
    }
}

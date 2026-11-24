// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad;

import io.github.yahocen.acad.constant.AutoCadVersion;
import io.github.yahocen.acad.document.Document;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.document.element.Line;
import io.github.yahocen.acad.event.ApplicationEventSink;
import io.github.yahocen.acad.event.DocumentEventSink;
import io.github.yahocen.acad.event.ObjectEventSink;
import io.github.yahocen.acad.testsupport.AcadTestHarness;
import io.github.yahocen.jacob.com.DispatchEvents;
import io.github.yahocen.jacob.com.Variant;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 多版本 AutoCAD 事件绑定回归测试。
 * <p>
 * 验证 Application / Document / Object 三类事件源都能成功建立连接点（绑定）并解绑，
 * 守护原生层 {@code GetEventIID} 按源对象自身接口匹配 coclass 的修复——历史上 Document/Object
 * 因 ProgID 恒指向 Application coclass 而报 {@code Can't FindConnectionPoint}。
 * </p>
 * <p>
 * 绑定/解绑是同步 COM 调用，在测试主线程（已由 jacob 初始化为 STA）上即可建立连接点，
 * 无需独立消息泵线程（回调投递才需要泵，属另一层面）。这样避免另起 STA 线程并 Release
 * 与主线程 COM 全局状态冲突。清理走 harness 的 tracked-PID 安全路径，只动自己启动的实例。
 * </p>
 */
class MultiVersionEventBindingTest {

    @TestFactory
    List<DynamicTest> eventBindingPerVersion() {
        return AcadTestHarness.selectedVersions().stream()
                .map(v -> DynamicTest.dynamicTest(
                        "事件绑定 " + v.getRelease() + " (" + v.getProgId() + ")",
                        () -> bindOnce(v)))
                .toList();
    }

    private static void bindOnce(AutoCadVersion version) {
        Application app = AcadTestHarness.launch(version);
        DispatchEvents ah = null, dh = null, oh = null;
        try {
            Document doc = app.getDocuments().add();

            ah = app.addEventListener(new ApplicationEventSink() {
                @Override
                public void BeginCommand(Variant[] a) {
                }
            });
            assertTrue(ah != null, "Application 事件绑定失败");

            dh = doc.addEventListener(new DocumentEventSink() {
                @Override
                public void BeginCommand(Variant[] a) {
                }
            });
            assertTrue(dh != null, "Document 事件绑定失败");

            Line line = doc.getModelSpace().addLine(Point.of(0, 0), Point.of(10, 0));
            oh = line.addEventListener(new ObjectEventSink() {
                @Override
                public void Modified(Variant[] a) {
                }
            });
            assertTrue(oh != null, "Object 事件绑定失败");
        } finally {
            try {
                if (ah != null) ah.safeRelease();
            } catch (Throwable ignore) {
            }
            try {
                if (dh != null) dh.safeRelease();
            } catch (Throwable ignore) {
            }
            try {
                if (oh != null) oh.safeRelease();
            } catch (Throwable ignore) {
            }
            AcadTestHarness.shutdown(app, version);
        }
    }
}

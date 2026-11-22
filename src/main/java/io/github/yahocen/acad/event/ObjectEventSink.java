// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.event;

import io.github.yahocen.jacob.com.Variant;

/**
 * ObjectEventSink - AutoCAD IAcadObjectEvents 事件接收器
 * <p>覆盖方法以响应对应事件。所有方法默认空实现。</p>
 */
public abstract class ObjectEventSink extends EventSink {

    /**
     * Modified 事件
     * 参数: Dispatch pObject
     */
    public void Modified(Variant[] args) {}

}
// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.common;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.DispatchEvents;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import io.github.yahocen.acad.event.EventSink;

import static io.github.yahocen.acad.constant.AutoCadVersion.AUTOCAD;

/**
 * COM对象基类
 * <p>
 * 封装AutoCAD ActiveX COM对象的基本操作。
 * </p>
 * 
 * @author Yahocen
 * @since 2.0
 */
@Getter
@Schema(
    title = "ComObject COM对象基类",
    description = "封装AutoCAD ActiveX COM对象的基本操作"
)
public abstract class ComObject {

    /**
     * -- GETTER --
     *  获取底层 COM Dispatch 对象。
     *  <p>
     *  供需要直接访问实体底层 Dispatch 的场景使用（如构造传给 AppendOuterLoop 的对象数组）。
     *  </p>
     *
     * @return 底层 Dispatch 对象
     */
    protected Dispatch dispatch;

    public ComObject(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    /**
     * 添加事件监听器，返回连接点句柄以便后续解绑。
     * <p>
     * 经版本无关 ProgID（{@code AutoCAD.Application}）定位类型库；原生层会按源对象自身
     * 的接口 GUID 匹配其 coclass 的默认事件接口，因此 Application / Document / Object
     * 各自的事件都能正确绑定（不依赖对象实现 IProvideClassInfo）。
     * </p>
     * <p>
     * 事件回调在 COM STA 线程上送达：须在专用 STA 线程（{@code io.github.yahocen.jacob.com.STA}
     * 子类，OnInit 里建对象并绑定、随后跑消息泵）上持有对象，否则事件不会触发。
     * 不再需要监听时调用返回句柄的 {@link DispatchEvents#safeRelease()} 解除连接点，
     * 避免监听器累积泄漏。
     * </p>
     *
     * @param acadEventSink 事件接收器（ApplicationEventSink / DocumentEventSink / ObjectEventSink，
     *                      可用匿名子类）
     * @param <T> 事件接收器类型
     * @return 连接点句柄，调用 {@code safeRelease()} 可解绑
     */
    public <T extends EventSink> DispatchEvents addEventListener(T acadEventSink) {
        return new DispatchEvents(dispatch, acadEventSink, AUTOCAD.getProgId());
    }

    /**
     * 释放底层 COM Dispatch 资源。
     * <p>
     * 建议在对象不再使用时调用，避免 COM 对象泄漏。
     * 调用后不应再使用此对象。
     * </p>
     */
    public void safeRelease() {
        if (dispatch != null) {
            dispatch.safeRelease();
            dispatch = null;
        }
    }

}

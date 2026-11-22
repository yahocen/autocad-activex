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
     * 添加事件监听器
     * 
     * @param acadEventSink 事件接收器
     * @param <T> 事件接收器类型
     */
    public <T extends EventSink> void addEventListener(T acadEventSink) {
        new DispatchEvents(dispatch, acadEventSink, AUTOCAD.getProgId());
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

package io.github.yahocen.acad.event;

import io.github.yahocen.jacob.com.Variant;

/**
 * ApplicationEventSink - AutoCAD DAcadApplicationEvents 事件接收器
 * <p>覆盖方法以响应对应事件。所有方法默认空实现。</p>
 */
public abstract class ApplicationEventSink extends EventSink {

    /**
     * SysVarChanged 事件
     * 参数: String SysvarName, Variant newVal
     */
    public void SysVarChanged(Variant[] args) {}

    /**
     * NewDrawing 事件
     */
    public void NewDrawing(Variant[] args) {}

    /**
     * AppActivate 事件
     */
    public void AppActivate(Variant[] args) {}

    /**
     * AppDeactivate 事件
     */
    public void AppDeactivate(Variant[] args) {}

    /**
     * WindowMovedOrResized 事件
     * 参数: int HWNDFrame, boolean bMoved
     */
    public void WindowMovedOrResized(Variant[] args) {}

    /**
     * BeginModal 事件
     */
    public void BeginModal(Variant[] args) {}

    /**
     * EndModal 事件
     */
    public void EndModal(Variant[] args) {}

    /**
     * BeginQuit 事件
     * 参数: Object Cancel
     */
    public void BeginQuit(Variant[] args) {}

    /**
     * BeginFileDrop 事件
     * 参数: String FileName, Object Cancel
     */
    public void BeginFileDrop(Variant[] args) {}

    /**
     * ARXLoaded 事件
     * 参数: String AppName
     */
    public void ARXLoaded(Variant[] args) {}

    /**
     * ARXUnloaded 事件
     * 参数: String AppName
     */
    public void ARXUnloaded(Variant[] args) {}

    /**
     * BeginPlot 事件
     * 参数: String DrawingName
     */
    public void BeginPlot(Variant[] args) {}

    /**
     * EndPlot 事件
     * 参数: String DrawingName
     */
    public void EndPlot(Variant[] args) {}

    /**
     * BeginSave 事件
     * 参数: String FileName
     */
    public void BeginSave(Variant[] args) {}

    /**
     * EndSave 事件
     * 参数: String FileName
     */
    public void EndSave(Variant[] args) {}

    /**
     * BeginOpen 事件
     * 参数: Object FileName
     */
    public void BeginOpen(Variant[] args) {}

    /**
     * EndOpen 事件
     * 参数: String FileName
     */
    public void EndOpen(Variant[] args) {}

    /**
     * BeginCommand 事件
     * 参数: String CommandName
     */
    public void BeginCommand(Variant[] args) {}

    /**
     * EndCommand 事件
     * 参数: String CommandName
     */
    public void EndCommand(Variant[] args) {}

    /**
     * BeginLisp 事件
     * 参数: String FirstLine
     */
    public void BeginLisp(Variant[] args) {}

    /**
     * EndLisp 事件
     */
    public void EndLisp(Variant[] args) {}

    /**
     * LispCancelled 事件
     */
    public void LispCancelled(Variant[] args) {}

    /**
     * WindowChanged 事件
     * 参数: int WindowState
     */
    public void WindowChanged(Variant[] args) {}

}
package io.github.yahocen.acad.event;

import io.github.yahocen.jacob.com.Variant;

/**
 * DocumentEventSink - AutoCAD DAcadDocumentEvents 事件接收器
 * <p>覆盖方法以响应对应事件。所有方法默认空实现。</p>
 */
public abstract class DocumentEventSink extends EventSink {

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
     * SelectionChanged 事件
     */
    public void SelectionChanged(Variant[] args) {}

    /**
     * Activate 事件
     */
    public void Activate(Variant[] args) {}

    /**
     * Deactivate 事件
     */
    public void Deactivate(Variant[] args) {}

    /**
     * BeginRightClick 事件
     * 参数: Variant PickPoint
     */
    public void BeginRightClick(Variant[] args) {}

    /**
     * BeginShortcutMenuDefault 事件
     * 参数: Object ShortcutMenu
     */
    public void BeginShortcutMenuDefault(Variant[] args) {}

    /**
     * BeginShortcutMenuEdit 事件
     * 参数: Object ShortcutMenu, Object SelectionSet
     */
    public void BeginShortcutMenuEdit(Variant[] args) {}

    /**
     * BeginShortcutMenuCommand 事件
     * 参数: Object ShortcutMenu, String Command
     */
    public void BeginShortcutMenuCommand(Variant[] args) {}

    /**
     * BeginShortcutMenuGrip 事件
     * 参数: Object ShortcutMenu
     */
    public void BeginShortcutMenuGrip(Variant[] args) {}

    /**
     * BeginShortcutMenuOsnap 事件
     * 参数: Object ShortcutMenu
     */
    public void BeginShortcutMenuOsnap(Variant[] args) {}

    /**
     * EndShortcutMenu 事件
     * 参数: Object ShortcutMenu
     */
    public void EndShortcutMenu(Variant[] args) {}

    /**
     * BeginDoubleClick 事件
     * 参数: Variant PickPoint
     */
    public void BeginDoubleClick(Variant[] args) {}

    /**
     * ObjectAdded 事件
     * 参数: Dispatch Object
     */
    public void ObjectAdded(Variant[] args) {}

    /**
     * ObjectErased 事件
     * 参数: long ObjectID
     */
    public void ObjectErased(Variant[] args) {}

    /**
     * ObjectModified 事件
     * 参数: Dispatch Object
     */
    public void ObjectModified(Variant[] args) {}

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
     * WindowMovedOrResized 事件
     * 参数: long HWNDFrame, boolean bMoved
     */
    public void WindowMovedOrResized(Variant[] args) {}

    /**
     * LayoutSwitched 事件
     * 参数: String LayoutName
     */
    public void LayoutSwitched(Variant[] args) {}

    /**
     * WindowChanged 事件
     * 参数: int WindowState
     */
    public void WindowChanged(Variant[] args) {}

    /**
     * BeginClose 事件
     */
    public void BeginClose(Variant[] args) {}

    /**
     * BeginDocClose 事件
     * 参数: Object Cancel
     */
    public void BeginDocClose(Variant[] args) {}

}
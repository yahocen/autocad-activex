package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPopupMenuItem — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PopupMenuItem extends ComObject {

    public PopupMenuItem(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getCaption() {
        return Disp.get(getClass(), dispatch, "Caption");
    }

    public Variant getCheck() {
        return Disp.get(getClass(), dispatch, "Check");
    }

    public PopupMenuItem setCheck(Variant check) {
        Disp.put(getClass(), dispatch, "Check", check);
        return this;
    }

    public Variant getEnable() {
        return Disp.get(getClass(), dispatch, "Enable");
    }

    public PopupMenuItem setEnable(Variant enable) {
        Disp.put(getClass(), dispatch, "Enable", enable);
        return this;
    }

    public Variant getEndSubMenuLevel() {
        return Disp.get(getClass(), dispatch, "EndSubMenuLevel");
    }

    public PopupMenuItem setEndSubMenuLevel(Variant endSubMenuLevel) {
        Disp.put(getClass(), dispatch, "EndSubMenuLevel", endSubMenuLevel);
        return this;
    }

    public Variant getHelpString() {
        return Disp.get(getClass(), dispatch, "HelpString");
    }

    public PopupMenuItem setHelpString(Variant helpString) {
        Disp.put(getClass(), dispatch, "HelpString", helpString);
        return this;
    }

    public Variant getIndex() {
        return Disp.get(getClass(), dispatch, "Index");
    }

    public Variant getLabel() {
        return Disp.get(getClass(), dispatch, "Label");
    }

    public PopupMenuItem setLabel(Variant label) {
        Disp.put(getClass(), dispatch, "Label", label);
        return this;
    }

    public Variant getMacro() {
        return Disp.get(getClass(), dispatch, "Macro");
    }

    public PopupMenuItem setMacro(Variant macro) {
        Disp.put(getClass(), dispatch, "Macro", macro);
        return this;
    }

    public int getParent() {
        return Disp.get(getClass(), dispatch, "Parent").getInt();
    }

    public int getSubMenu() {
        return Disp.get(getClass(), dispatch, "SubMenu").getInt();
    }

    public Variant getTagString() {
        return Disp.get(getClass(), dispatch, "TagString");
    }

    public PopupMenuItem setTagString(Variant tagString) {
        Disp.put(getClass(), dispatch, "TagString", tagString);
        return this;
    }

    public int getType() {
        return Disp.get(getClass(), dispatch, "Type").getInt();
    }

    public Variant delete() {
        Variant result = Disp.call(getClass(), dispatch, "Delete");
        return result;
    }

}

package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadMenuGroup — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class MenuGroup extends ComObject {

    public MenuGroup(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getMenuFileName() {
        return Disp.get(getClass(), dispatch, "MenuFileName");
    }

    public int getMenus() {
        return Disp.get(getClass(), dispatch, "Menus").getInt();
    }

    public Variant getName() {
        return Disp.get(getClass(), dispatch, "Name");
    }

    public int getParent() {
        return Disp.get(getClass(), dispatch, "Parent").getInt();
    }

    public int getToolbars() {
        return Disp.get(getClass(), dispatch, "Toolbars").getInt();
    }

    public int getType() {
        return Disp.get(getClass(), dispatch, "Type").getInt();
    }

    public Variant unload() {
        Variant result = Disp.call(getClass(), dispatch, "Unload");
        return result;
    }

    public Variant save(int menuFileType) {
        Variant result = Disp.call(getClass(), dispatch, "Save", menuFileType);
        return result;
    }

    public Variant saveAs(Variant menuFileName, int menuFileType) {
        Variant result = Disp.call(getClass(), dispatch, "SaveAs", menuFileName, menuFileType);
        return result;
    }

}

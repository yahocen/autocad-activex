// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadPopupMenus — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PopupMenus extends ComCollection<PopupMenu> {

    public PopupMenus(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected PopupMenu wrap(Dispatch dispatch) {
        return new PopupMenu(dispatch);
    }
    public MenuGroup getParent() {
        return new MenuGroup(getProperties("Parent").getDispatch());
    }

    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant menuName) {
        return Disp.call(getClass(), dispatch, "Add", menuName).getInt();
    }

    public Variant insertMenuInMenuBar(Variant menuName, Variant index) {
        Variant result = Disp.call(getClass(), dispatch, "InsertMenuInMenuBar", menuName, index);
        return result;
    }

    public Variant removeMenuFromMenuBar(Variant index) {
        Variant result = Disp.call(getClass(), dispatch, "RemoveMenuFromMenuBar", index);
        return result;
    }

}

// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.Application;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadMenuGroups — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class MenuGroups extends ComCollection<MenuGroup> {

    public MenuGroups(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected MenuGroup wrap(Dispatch dispatch) {
        return new MenuGroup(dispatch);
    }
    public Application getParent() {
        return new Application(getProperties("Parent").getDispatch());
    }

    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int load(Variant menuFileName, Variant baseMenu) {
        return Disp.call(getClass(), dispatch, "Load", menuFileName, baseMenu).getInt();
    }

}

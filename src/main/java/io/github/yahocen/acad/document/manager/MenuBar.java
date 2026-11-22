// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.Application;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadMenuBar — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class MenuBar extends ComCollection<Variant> {

    public MenuBar(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Variant wrap(Dispatch dispatch) {
        return new Variant(dispatch);
    }
    public Application getParent() {
        return new Application(getProperties("Parent").getDispatch());
    }

    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

}

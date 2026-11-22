// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadViewports — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Viewports extends ComCollection<Viewport> {

    public Viewports(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Viewport wrap(Dispatch dispatch) {
        return new Viewport(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant name) {
        return Disp.call(getClass(), dispatch, "Add", name).getInt();
    }

    public Variant deleteConfiguration(Variant name) {
        Variant result = Disp.call(getClass(), dispatch, "DeleteConfiguration", name);
        return result;
    }

}

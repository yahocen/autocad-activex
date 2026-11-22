// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadMaterials — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Materials extends ComCollection<Material> {

    public Materials(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Material wrap(Dispatch dispatch) {
        return new Material(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant name) {
        return Disp.call(getClass(), dispatch, "Add", name).getInt();
    }

}

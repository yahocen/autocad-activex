// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadDimStyle — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class DimStyle extends AcadObject {

    public DimStyle(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getName() {
        return getProperties("Name");
    }

    public DimStyle setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant copyFrom(Variant styleSource) {
        Variant result = Disp.call(getClass(), dispatch, "CopyFrom", styleSource);
        return result;
    }

}

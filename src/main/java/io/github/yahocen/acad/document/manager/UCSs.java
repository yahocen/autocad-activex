// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadUCSs — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class UCSs extends ComCollection<UCS> {

    public UCSs(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected UCS wrap(Dispatch dispatch) {
        return new UCS(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant origin, Variant xAxisPoint, Variant yAxisPoint, Variant name) {
        return Disp.call(getClass(), dispatch, "Add", origin, xAxisPoint, yAxisPoint, name).getInt();
    }

}

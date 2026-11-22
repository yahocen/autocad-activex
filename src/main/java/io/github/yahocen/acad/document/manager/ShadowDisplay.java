// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadShadowDisplay — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class ShadowDisplay extends ComObject {

    public ShadowDisplay(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getEnableShadowDisplay() {
        return Disp.get(getClass(), dispatch, "EnableShadowDisplay");
    }

    public int getShadowDisplay() {
        return Disp.get(getClass(), dispatch, "ShadowDisplay").getInt();
    }

    public ShadowDisplay setShadowDisplay(int shadowDisplay) {
        Disp.put(getClass(), dispatch, "ShadowDisplay", shadowDisplay);
        return this;
    }

}

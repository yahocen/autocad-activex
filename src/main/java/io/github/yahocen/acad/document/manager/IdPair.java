// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadIdPair — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class IdPair extends ComObject {

    public IdPair(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getApplication() {
        return Disp.get(getClass(), dispatch, "Application");
    }

    public Variant getIsCloned() {
        return Disp.get(getClass(), dispatch, "IsCloned");
    }

    public Variant getIsOwnerXlated() {
        return Disp.get(getClass(), dispatch, "IsOwnerXlated");
    }

    public Variant getIsPrimary() {
        return Disp.get(getClass(), dispatch, "IsPrimary");
    }

    public int getValue() {
        return Disp.get(getClass(), dispatch, "Value").getInt();
    }

    public int getkey() {
        return Disp.get(getClass(), dispatch, "key").getInt();
    }

}

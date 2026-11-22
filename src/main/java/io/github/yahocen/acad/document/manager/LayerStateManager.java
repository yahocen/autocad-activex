// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadLayerStateManager — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class LayerStateManager extends ComObject {

    public LayerStateManager(Dispatch dispatch) {
        super(dispatch);
    }
    public int getMask() {
        return Disp.get(getClass(), dispatch, "Mask").getInt();
    }

    public LayerStateManager setMask(int mask) {
        Disp.put(getClass(), dispatch, "Mask", mask);
        return this;
    }

    public Variant setDatabase(Variant iHostDb) {
        Variant result = Disp.call(getClass(), dispatch, "SetDatabase", iHostDb);
        return result;
    }

    public Variant save(Variant bsName, int eMask) {
        Variant result = Disp.call(getClass(), dispatch, "Save", bsName, eMask);
        return result;
    }

    public Variant restore(Variant bsName) {
        Variant result = Disp.call(getClass(), dispatch, "Restore", bsName);
        return result;
    }

    public Variant delete(Variant bsName) {
        Variant result = Disp.call(getClass(), dispatch, "Delete", bsName);
        return result;
    }

    public Variant rename(Variant bsName, Variant bsNewName) {
        Variant result = Disp.call(getClass(), dispatch, "Rename", bsName, bsNewName);
        return result;
    }

    public Variant import_(Variant bsFilename) {
        Variant result = Disp.call(getClass(), dispatch, "Import", bsFilename);
        return result;
    }

    public Variant export(Variant bsName, Variant bsFilename) {
        Variant result = Disp.call(getClass(), dispatch, "Export", bsName, bsFilename);
        return result;
    }

}

// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadDynamicBlockReferenceProperty — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class DynamicBlockReferenceProperty extends ComObject {

    public DynamicBlockReferenceProperty(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getAllowedValues() {
        return Disp.get(getClass(), dispatch, "AllowedValues");
    }

    public Variant getDescription() {
        return Disp.get(getClass(), dispatch, "Description");
    }

    public Variant getPropertyName() {
        return Disp.get(getClass(), dispatch, "PropertyName");
    }

    public Variant getReadOnly() {
        return Disp.get(getClass(), dispatch, "ReadOnly");
    }

    public int getUnitsType() {
        return Disp.get(getClass(), dispatch, "UnitsType").getInt();
    }

    public Variant getValue() {
        return Disp.get(getClass(), dispatch, "Value");
    }

    public DynamicBlockReferenceProperty setValue(Variant value) {
        Disp.put(getClass(), dispatch, "Value", value);
        return this;
    }

    public Variant getshow() {
        return Disp.get(getClass(), dispatch, "show");
    }

}

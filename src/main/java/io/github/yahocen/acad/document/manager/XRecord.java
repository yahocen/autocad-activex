// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadXRecord — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class XRecord extends AcadObject {

    public XRecord(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getName() {
        return getProperties("Name");
    }

    public XRecord setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getTranslateIDs() {
        return getProperties("TranslateIDs");
    }

    public XRecord setTranslateIDs(Variant translateIDs) {
        setProperties("TranslateIDs", translateIDs);
        return this;
    }

    public Variant getXRecordData(Variant xRecordDataType, Variant xRecordDataValue) {
        Variant result = Disp.call(getClass(), dispatch, "GetXRecordData", xRecordDataType, xRecordDataValue);
        return result;
    }

    public Variant setXRecordData(Variant xRecordDataType, Variant xRecordDataValue) {
        Variant result = Disp.call(getClass(), dispatch, "SetXRecordData", xRecordDataType, xRecordDataValue);
        return result;
    }

}

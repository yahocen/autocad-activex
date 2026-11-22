// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadUCS — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class UCS extends AcadObject {

    public UCS(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getName() {
        return getProperties("Name");
    }

    public UCS setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getOrigin() {
        return getProperties("Origin");
    }

    public UCS setOrigin(Variant origin) {
        setProperties("Origin", origin);
        return this;
    }

    public Variant getXVector() {
        return getProperties("XVector");
    }

    public UCS setXVector(Variant xVector) {
        setProperties("XVector", xVector);
        return this;
    }

    public Variant getYVector() {
        return getProperties("YVector");
    }

    public UCS setYVector(Variant yVector) {
        setProperties("YVector", yVector);
        return this;
    }

    public Variant getUCSMatrix() {
        Variant result = Disp.call(getClass(), dispatch, "GetUCSMatrix");
        return result;
    }

}

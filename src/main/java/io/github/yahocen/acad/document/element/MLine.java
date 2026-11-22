// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * MLine - AutoCAD MLine 实体
 */
@AcadEntityName("AcDbMline")
public class MLine extends AcadEntity {

    public MLine(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getCoordinates() {
        return getProperties("Coordinates");
    }

    public MLine setCoordinates(Variant coordinates) {
        setProperties("Coordinates", coordinates);
        return this;
    }

    public int getJustification() {
        return getProperties("Justification").getInt();
    }

    public MLine setJustification(int justification) {
        setProperties("Justification", justification);
        return this;
    }

    public double getMLineScale() {
        return getProperties("MLineScale").getDouble();
    }

    public MLine setMLineScale(double mLineScale) {
        setProperties("MLineScale", mLineScale);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

}
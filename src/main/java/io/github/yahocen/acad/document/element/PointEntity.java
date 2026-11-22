// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * PointEntity - AutoCAD PointEntity 实体
 */
@AcadEntityName("AcDbPoint")
public class PointEntity extends AcadEntity {

    public PointEntity(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getCoordinates() {
        return getProperties("Coordinates");
    }

    public PointEntity setCoordinates(Variant coordinates) {
        setProperties("Coordinates", coordinates);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public PointEntity setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public PointEntity setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

}
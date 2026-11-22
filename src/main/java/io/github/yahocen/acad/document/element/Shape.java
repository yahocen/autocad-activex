// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Shape - AutoCAD Shape 实体
 */
@AcadEntityName("AcDbShape")
public class Shape extends AcadEntity {

    public Shape(Dispatch dispatch) {
        super(dispatch);
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Shape setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public Shape setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public String getName() {
        return getProperties("Name").getString();
    }

    public Shape setName(String name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Shape setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getObliqueAngle() {
        return getProperties("ObliqueAngle").getDouble();
    }

    public Shape setObliqueAngle(double obliqueAngle) {
        setProperties("ObliqueAngle", obliqueAngle);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public Shape setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public Shape setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public Shape setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

}
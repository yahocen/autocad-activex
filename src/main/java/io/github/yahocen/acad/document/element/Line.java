// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Line - AutoCAD Line 实体
 */
@AcadEntityName("AcDbLine")
public class Line extends AcadEntity {

    public Line(Dispatch dispatch) {
        super(dispatch);
    }

    public double getAngle() {
        return getProperties("Angle").getDouble();
    }

    public Variant getDelta() {
        return getProperties("Delta");
    }

    public Point getEndPoint() {
        return Point.of(getProperties("EndPoint").toSafeArray());
    }

    public Line setEndPoint(Point endPoint) {
        setProperties("EndPoint", endPoint.toVariant());
        return this;
    }

    public double getLength() {
        return getProperties("Length").getDouble();
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Line setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public Point getStartPoint() {
        return Point.of(getProperties("StartPoint").toSafeArray());
    }

    public Line setStartPoint(Point startPoint) {
        setProperties("StartPoint", startPoint.toVariant());
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public Line setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

    public Variant offset(Variant distance) {
        return call("Offset", distance);
    }

}
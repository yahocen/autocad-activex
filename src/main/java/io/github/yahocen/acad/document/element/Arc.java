package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Arc - AutoCAD Arc 实体
 */
@AcadEntityName("AcDbArc")
public class Arc extends AcadEntity {

    public Arc(Dispatch dispatch) {
        super(dispatch);
    }

    public double getArcLength() {
        return getProperties("ArcLength").getDouble();
    }

    public double getArea() {
        return getProperties("Area").getDouble();
    }

    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    public Arc setCenter(Point center) {
        setProperties("Center", center.toVariant());
        return this;
    }

    public double getEndAngle() {
        return getProperties("EndAngle").getDouble();
    }

    public Arc setEndAngle(double endAngle) {
        setProperties("EndAngle", endAngle);
        return this;
    }

    public Point getEndPoint() {
        return Point.of(getProperties("EndPoint").toSafeArray());
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Arc setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getRadius() {
        return getProperties("Radius").getDouble();
    }

    public Arc setRadius(double radius) {
        setProperties("Radius", radius);
        return this;
    }

    public double getStartAngle() {
        return getProperties("StartAngle").getDouble();
    }

    public Arc setStartAngle(double startAngle) {
        setProperties("StartAngle", startAngle);
        return this;
    }

    public Point getStartPoint() {
        return Point.of(getProperties("StartPoint").toSafeArray());
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public Arc setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

    public double getTotalAngle() {
        return getProperties("TotalAngle").getDouble();
    }

    public Variant offset(Variant distance) {
        return call("Offset", distance);
    }

}
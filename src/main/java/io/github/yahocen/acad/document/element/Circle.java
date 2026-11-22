package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Circle - AutoCAD Circle 实体
 */
@AcadEntityName("AcDbCircle")
public class Circle extends AcadEntity {

    public Circle(Dispatch dispatch) {
        super(dispatch);
    }

    public double getArea() {
        return getProperties("Area").getDouble();
    }

    public Circle setArea(double area) {
        setProperties("Area", area);
        return this;
    }

    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    public Circle setCenter(Point center) {
        setProperties("Center", center.toVariant());
        return this;
    }

    public Variant getCircumference() {
        return getProperties("Circumference");
    }

    public Circle setCircumference(Variant circumference) {
        setProperties("Circumference", circumference);
        return this;
    }

    public Variant getDiameter() {
        return getProperties("Diameter");
    }

    public Circle setDiameter(Variant diameter) {
        setProperties("Diameter", diameter);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Circle setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getRadius() {
        return getProperties("Radius").getDouble();
    }

    public Circle setRadius(double radius) {
        setProperties("Radius", radius);
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public Circle setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

    public Variant offset(Variant distance) {
        return call("Offset", distance);
    }

}
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Ellipse - AutoCAD Ellipse 实体
 */
@AcadEntityName("AcDbEllipse")
public class Ellipse extends AcadEntity {

    public Ellipse(Dispatch dispatch) {
        super(dispatch);
    }

    public double getArea() {
        return getProperties("Area").getDouble();
    }

    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    public Ellipse setCenter(Point center) {
        setProperties("Center", center.toVariant());
        return this;
    }

    public double getEndAngle() {
        return getProperties("EndAngle").getDouble();
    }

    public Ellipse setEndAngle(double endAngle) {
        setProperties("EndAngle", endAngle);
        return this;
    }

    public Variant getEndParameter() {
        return getProperties("EndParameter");
    }

    public Ellipse setEndParameter(Variant endParameter) {
        setProperties("EndParameter", endParameter);
        return this;
    }

    public Point getEndPoint() {
        return Point.of(getProperties("EndPoint").toSafeArray());
    }

    public Variant getMajorAxis() {
        return getProperties("MajorAxis");
    }

    public Ellipse setMajorAxis(Variant majorAxis) {
        setProperties("MajorAxis", majorAxis);
        return this;
    }

    public double getMajorRadius() {
        return getProperties("MajorRadius").getDouble();
    }

    public Ellipse setMajorRadius(double majorRadius) {
        setProperties("MajorRadius", majorRadius);
        return this;
    }

    public Variant getMinorAxis() {
        return getProperties("MinorAxis");
    }

    public double getMinorRadius() {
        return getProperties("MinorRadius").getDouble();
    }

    public Ellipse setMinorRadius(double minorRadius) {
        setProperties("MinorRadius", minorRadius);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Ellipse setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getRadiusRatio() {
        return getProperties("RadiusRatio").getDouble();
    }

    public Ellipse setRadiusRatio(double radiusRatio) {
        setProperties("RadiusRatio", radiusRatio);
        return this;
    }

    public double getStartAngle() {
        return getProperties("StartAngle").getDouble();
    }

    public Ellipse setStartAngle(double startAngle) {
        setProperties("StartAngle", startAngle);
        return this;
    }

    public Variant getStartParameter() {
        return getProperties("StartParameter");
    }

    public Ellipse setStartParameter(Variant startParameter) {
        setProperties("StartParameter", startParameter);
        return this;
    }

    public Point getStartPoint() {
        return Point.of(getProperties("StartPoint").toSafeArray());
    }

    public Variant offset(Variant distance) {
        return call("Offset", distance);
    }

}
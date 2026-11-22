package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * XLine - AutoCAD XLine 实体
 */
@AcadEntityName("AcDbXline")
public class XLine extends AcadEntity {

    public XLine(Dispatch dispatch) {
        super(dispatch);
    }

    public Point getBasePoint() {
        return Point.of(getProperties("BasePoint").toSafeArray());
    }

    public XLine setBasePoint(Point basePoint) {
        setProperties("BasePoint", basePoint.toVariant());
        return this;
    }

    public Variant getDirectionVector() {
        return getProperties("DirectionVector");
    }

    public XLine setDirectionVector(Variant directionVector) {
        setProperties("DirectionVector", directionVector);
        return this;
    }

    public Point getSecondPoint() {
        return Point.of(getProperties("SecondPoint").toSafeArray());
    }

    public XLine setSecondPoint(Point secondPoint) {
        setProperties("SecondPoint", secondPoint.toVariant());
        return this;
    }

    public Variant offset(Variant distance) {
        return call("Offset", distance);
    }

}
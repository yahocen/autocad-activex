package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Ray - AutoCAD Ray 实体
 */
@AcadEntityName("AcDbRay")
public class Ray extends AcadEntity {

    public Ray(Dispatch dispatch) {
        super(dispatch);
    }

    public Point getBasePoint() {
        return Point.of(getProperties("BasePoint").toSafeArray());
    }

    public Ray setBasePoint(Point basePoint) {
        setProperties("BasePoint", basePoint.toVariant());
        return this;
    }

    public Variant getDirectionVector() {
        return getProperties("DirectionVector");
    }

    public Ray setDirectionVector(Variant directionVector) {
        setProperties("DirectionVector", directionVector);
        return this;
    }

    public Point getSecondPoint() {
        return Point.of(getProperties("SecondPoint").toSafeArray());
    }

    public Ray setSecondPoint(Point secondPoint) {
        setProperties("SecondPoint", secondPoint.toVariant());
        return this;
    }

}
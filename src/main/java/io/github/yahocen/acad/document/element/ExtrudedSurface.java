package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Surface;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * ExtrudedSurface - AutoCAD ExtrudedSurface 实体
 */
@AcadEntityName("AcDbExtrudedSurface")
public class ExtrudedSurface extends Surface {

    public ExtrudedSurface(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getDirection() {
        return getProperties("Direction");
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public ExtrudedSurface setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public double getTaperAngle() {
        return getProperties("TaperAngle").getDouble();
    }

    public ExtrudedSurface setTaperAngle(double taperAngle) {
        setProperties("TaperAngle", taperAngle);
        return this;
    }

}
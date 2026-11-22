package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Surface;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * SweptSurface - AutoCAD SweptSurface 实体
 */
@AcadEntityName("AcDbSweptSurface")
public class SweptSurface extends Surface {

    public SweptSurface(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getBank() {
        return getProperties("Bank");
    }

    public SweptSurface setBank(Variant bank) {
        setProperties("Bank", bank);
        return this;
    }

    public double getLength() {
        return getProperties("Length").getDouble();
    }

    public double getProfileRotation() {
        return getProperties("ProfileRotation").getDouble();
    }

    public SweptSurface setProfileRotation(double profileRotation) {
        setProperties("ProfileRotation", profileRotation);
        return this;
    }

    public double getTwist() {
        return getProperties("Twist").getDouble();
    }

    public SweptSurface setTwist(double twist) {
        setProperties("Twist", twist);
        return this;
    }

    public double getscale() {
        return getProperties("scale").getDouble();
    }

    public SweptSurface setscale(double scale) {
        setProperties("scale", scale);
        return this;
    }

}
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Wipeout - AutoCAD Wipeout 实体
 */
@AcadEntityName("AcDbWipeout")
public class Wipeout extends AcadEntity {

    public Wipeout(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getBrightness() {
        return getProperties("Brightness");
    }

    public Wipeout setBrightness(Variant brightness) {
        setProperties("Brightness", brightness);
        return this;
    }

    public Variant getClippingEnabled() {
        return getProperties("ClippingEnabled");
    }

    public Wipeout setClippingEnabled(Variant clippingEnabled) {
        setProperties("ClippingEnabled", clippingEnabled);
        return this;
    }

    public Variant getContrast() {
        return getProperties("Contrast");
    }

    public Wipeout setContrast(Variant contrast) {
        setProperties("Contrast", contrast);
        return this;
    }

    public Variant getFade() {
        return getProperties("Fade");
    }

    public Wipeout setFade(Variant fade) {
        setProperties("Fade", fade);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Variant getImageFile() {
        return getProperties("ImageFile");
    }

    public Wipeout setImageFile(Variant imageFile) {
        setProperties("ImageFile", imageFile);
        return this;
    }

    public double getImageHeight() {
        return getProperties("ImageHeight").getDouble();
    }

    public Wipeout setImageHeight(double imageHeight) {
        setProperties("ImageHeight", imageHeight);
        return this;
    }

    public Variant getImageVisibility() {
        return getProperties("ImageVisibility");
    }

    public Wipeout setImageVisibility(Variant imageVisibility) {
        setProperties("ImageVisibility", imageVisibility);
        return this;
    }

    public double getImageWidth() {
        return getProperties("ImageWidth").getDouble();
    }

    public Wipeout setImageWidth(double imageWidth) {
        setProperties("ImageWidth", imageWidth);
        return this;
    }

    public String getName() {
        return getProperties("Name").getString();
    }

    public Wipeout setName(String name) {
        setProperties("Name", name);
        return this;
    }

    public Point getOrigin() {
        return Point.of(getProperties("Origin").toSafeArray());
    }

    public Wipeout setOrigin(Point origin) {
        setProperties("Origin", origin.toVariant());
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public Wipeout setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public Wipeout setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public Variant getShowRotation() {
        return getProperties("ShowRotation");
    }

    public Wipeout setShowRotation(Variant showRotation) {
        setProperties("ShowRotation", showRotation);
        return this;
    }

    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    public boolean gettransparency() {
        return getProperties("transparency").getBoolean();
    }

    public Wipeout settransparency(boolean transparency) {
        setProperties("transparency", transparency);
        return this;
    }

    public Variant clipBoundary(Variant boundry) {
        return call("ClipBoundary", boundry);
    }

}
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * GeomapImage - AutoCAD GeomapImage 实体
 */
@AcadEntityName("AcDbGeoMapImage")
public class GeomapImage extends AcadEntity {

    public GeomapImage(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getBrightness() {
        return getProperties("Brightness");
    }

    public GeomapImage setBrightness(Variant brightness) {
        setProperties("Brightness", brightness);
        return this;
    }

    public Variant getClippingEnabled() {
        return getProperties("ClippingEnabled");
    }

    public GeomapImage setClippingEnabled(Variant clippingEnabled) {
        setProperties("ClippingEnabled", clippingEnabled);
        return this;
    }

    public Variant getContrast() {
        return getProperties("Contrast");
    }

    public GeomapImage setContrast(Variant contrast) {
        setProperties("Contrast", contrast);
        return this;
    }

    public Variant getFade() {
        return getProperties("Fade");
    }

    public GeomapImage setFade(Variant fade) {
        setProperties("Fade", fade);
        return this;
    }

    public Variant getGeoImageBrightness() {
        return getProperties("GeoImageBrightness");
    }

    public GeomapImage setGeoImageBrightness(Variant geoImageBrightness) {
        setProperties("GeoImageBrightness", geoImageBrightness);
        return this;
    }

    public Variant getGeoImageContrast() {
        return getProperties("GeoImageContrast");
    }

    public GeomapImage setGeoImageContrast(Variant geoImageContrast) {
        setProperties("GeoImageContrast", geoImageContrast);
        return this;
    }

    public Variant getGeoImageFade() {
        return getProperties("GeoImageFade");
    }

    public GeomapImage setGeoImageFade(Variant geoImageFade) {
        setProperties("GeoImageFade", geoImageFade);
        return this;
    }

    public double getGeoImageHeight() {
        return getProperties("GeoImageHeight").getDouble();
    }

    public Variant getGeoImagePosition() {
        return getProperties("GeoImagePosition");
    }

    public double getGeoImageWidth() {
        return getProperties("GeoImageWidth").getDouble();
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Variant getImageFile() {
        return getProperties("ImageFile");
    }

    public GeomapImage setImageFile(Variant imageFile) {
        setProperties("ImageFile", imageFile);
        return this;
    }

    public double getImageHeight() {
        return getProperties("ImageHeight").getDouble();
    }

    public GeomapImage setImageHeight(double imageHeight) {
        setProperties("ImageHeight", imageHeight);
        return this;
    }

    public Variant getImageVisibility() {
        return getProperties("ImageVisibility");
    }

    public GeomapImage setImageVisibility(Variant imageVisibility) {
        setProperties("ImageVisibility", imageVisibility);
        return this;
    }

    public double getImageWidth() {
        return getProperties("ImageWidth").getDouble();
    }

    public GeomapImage setImageWidth(double imageWidth) {
        setProperties("ImageWidth", imageWidth);
        return this;
    }

    public String getName() {
        return getProperties("Name").getString();
    }

    public GeomapImage setName(String name) {
        setProperties("Name", name);
        return this;
    }

    public Point getOrigin() {
        return Point.of(getProperties("Origin").toSafeArray());
    }

    public GeomapImage setOrigin(Point origin) {
        setProperties("Origin", origin.toVariant());
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public GeomapImage setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public GeomapImage setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public Variant getShowRotation() {
        return getProperties("ShowRotation");
    }

    public GeomapImage setShowRotation(Variant showRotation) {
        setProperties("ShowRotation", showRotation);
        return this;
    }

    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    public boolean gettransparency() {
        return getProperties("transparency").getBoolean();
    }

    public GeomapImage settransparency(boolean transparency) {
        setProperties("transparency", transparency);
        return this;
    }

    public Variant clipBoundary(Variant boundry) {
        return call("ClipBoundary", boundry);
    }

}
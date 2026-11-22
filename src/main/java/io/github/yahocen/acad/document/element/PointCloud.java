package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * PointCloud - AutoCAD PointCloud 实体
 */
@AcadEntityName("AcDbPointCloud")
public class PointCloud extends AcadEntity {

    public PointCloud(Dispatch dispatch) {
        super(dispatch);
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public PointCloud setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public PointCloud setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public int getIntensityColorScheme() {
        return getProperties("IntensityColorScheme").getInt();
    }

    public PointCloud setIntensityColorScheme(int intensityColorScheme) {
        setProperties("IntensityColorScheme", intensityColorScheme);
        return this;
    }

    public double getLength() {
        return getProperties("Length").getDouble();
    }

    public PointCloud setLength(double length) {
        setProperties("Length", length);
        return this;
    }

    public Variant getLocked() {
        return getProperties("Locked");
    }

    public PointCloud setLocked(Variant locked) {
        setProperties("Locked", locked);
        return this;
    }

    public String getName() {
        return getProperties("Name").getString();
    }

    public Variant getPath() {
        return getProperties("Path");
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public PointCloud setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public Variant getShowClipped() {
        return getProperties("ShowClipped");
    }

    public PointCloud setShowClipped(Variant showClipped) {
        setProperties("ShowClipped", showClipped);
        return this;
    }

    public Variant getShowIntensity() {
        return getProperties("ShowIntensity");
    }

    public PointCloud setShowIntensity(Variant showIntensity) {
        setProperties("ShowIntensity", showIntensity);
        return this;
    }

    public int getStylization() {
        return getProperties("Stylization").getInt();
    }

    public PointCloud setStylization(int stylization) {
        setProperties("Stylization", stylization);
        return this;
    }

    public Variant getUnit() {
        return getProperties("Unit");
    }

    public double getUnitFactor() {
        return getProperties("UnitFactor").getDouble();
    }

    public int getUseEntityColor() {
        return getProperties("UseEntityColor").getInt();
    }

    public PointCloud setUseEntityColor(int useEntityColor) {
        setProperties("UseEntityColor", useEntityColor);
        return this;
    }

    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    public PointCloud setWidth(double width) {
        setProperties("Width", width);
        return this;
    }

    public double getscale() {
        return getProperties("scale").getDouble();
    }

    public PointCloud setscale(double scale) {
        setProperties("scale", scale);
        return this;
    }

}
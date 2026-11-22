package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Ole - AutoCAD Ole 实体
 */
@AcadEntityName("AcDbOle2Frame")
public class Ole extends AcadEntity {

    public Ole(Dispatch dispatch) {
        super(dispatch);
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Ole setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public Ole setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public Variant getLockAspectRatio() {
        return getProperties("LockAspectRatio");
    }

    public Ole setLockAspectRatio(Variant lockAspectRatio) {
        setProperties("LockAspectRatio", lockAspectRatio);
        return this;
    }

    public int getOleItemType() {
        return getProperties("OleItemType").getInt();
    }

    public Ole setOleItemType(int oleItemType) {
        setProperties("OleItemType", oleItemType);
        return this;
    }

    public int getOlePlotQuality() {
        return getProperties("OlePlotQuality").getInt();
    }

    public Ole setOlePlotQuality(int olePlotQuality) {
        setProperties("OlePlotQuality", olePlotQuality);
        return this;
    }

    public Variant getOleSourceApp() {
        return getProperties("OleSourceApp");
    }

    public Ole setOleSourceApp(Variant oleSourceApp) {
        setProperties("OleSourceApp", oleSourceApp);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public Ole setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleHeight() {
        return getProperties("ScaleHeight").getDouble();
    }

    public Ole setScaleHeight(double scaleHeight) {
        setProperties("ScaleHeight", scaleHeight);
        return this;
    }

    public double getScaleWidth() {
        return getProperties("ScaleWidth").getDouble();
    }

    public Ole setScaleWidth(double scaleWidth) {
        setProperties("ScaleWidth", scaleWidth);
        return this;
    }

    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    public Ole setWidth(double width) {
        setProperties("Width", width);
        return this;
    }

}
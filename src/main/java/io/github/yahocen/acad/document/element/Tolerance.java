package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Tolerance - AutoCAD Tolerance 实体
 */
@AcadEntityName("AcDbFcf")
public class Tolerance extends AcadEntity {

    public Tolerance(Dispatch dispatch) {
        super(dispatch);
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public Tolerance setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public Variant getDirectionVector() {
        return getProperties("DirectionVector");
    }

    public Tolerance setDirectionVector(Variant directionVector) {
        setProperties("DirectionVector", directionVector);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public Tolerance setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Tolerance setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public Tolerance setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public Tolerance setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public int getTextColor() {
        return getProperties("TextColor").getInt();
    }

    public Tolerance setTextColor(int textColor) {
        setProperties("TextColor", textColor);
        return this;
    }

    public double getTextHeight() {
        return getProperties("TextHeight").getDouble();
    }

    public Tolerance setTextHeight(double textHeight) {
        setProperties("TextHeight", textHeight);
        return this;
    }

    public String getTextString() {
        return getProperties("TextString").getString();
    }

    public Tolerance setTextString(String textString) {
        setProperties("TextString", textString);
        return this;
    }

    public Variant getTextStyle() {
        return getProperties("TextStyle");
    }

    public Tolerance setTextStyle(Variant textStyle) {
        setProperties("TextStyle", textStyle);
        return this;
    }

}
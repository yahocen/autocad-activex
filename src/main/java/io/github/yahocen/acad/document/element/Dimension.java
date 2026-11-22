// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Dimension - AutoCAD Dimension 实体
 */
@AcadEntityName("AcDbDimension")
public class Dimension extends AcadEntity {

    public Dimension(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getDecimalSeparator() {
        return getProperties("DecimalSeparator");
    }

    public Dimension setDecimalSeparator(Variant decimalSeparator) {
        setProperties("DecimalSeparator", decimalSeparator);
        return this;
    }

    public Variant getDimTxtDirection() {
        return getProperties("DimTxtDirection");
    }

    public Dimension setDimTxtDirection(Variant dimTxtDirection) {
        setProperties("DimTxtDirection", dimTxtDirection);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Dimension setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public Dimension setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public Dimension setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public Dimension setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public Variant getSuppressLeadingZeros() {
        return getProperties("SuppressLeadingZeros");
    }

    public Dimension setSuppressLeadingZeros(Variant suppressLeadingZeros) {
        setProperties("SuppressLeadingZeros", suppressLeadingZeros);
        return this;
    }

    public Variant getSuppressTrailingZeros() {
        return getProperties("SuppressTrailingZeros");
    }

    public Dimension setSuppressTrailingZeros(Variant suppressTrailingZeros) {
        setProperties("SuppressTrailingZeros", suppressTrailingZeros);
        return this;
    }

    public int getTextColor() {
        return getProperties("TextColor").getInt();
    }

    public Dimension setTextColor(int textColor) {
        setProperties("TextColor", textColor);
        return this;
    }

    public Variant getTextFill() {
        return getProperties("TextFill");
    }

    public Dimension setTextFill(Variant textFill) {
        setProperties("TextFill", textFill);
        return this;
    }

    public int getTextFillColor() {
        return getProperties("TextFillColor").getInt();
    }

    public Dimension setTextFillColor(int textFillColor) {
        setProperties("TextFillColor", textFillColor);
        return this;
    }

    public Variant getTextGap() {
        return getProperties("TextGap");
    }

    public Dimension setTextGap(Variant textGap) {
        setProperties("TextGap", textGap);
        return this;
    }

    public double getTextHeight() {
        return getProperties("TextHeight").getDouble();
    }

    public Dimension setTextHeight(double textHeight) {
        setProperties("TextHeight", textHeight);
        return this;
    }

    public int getTextMovement() {
        return getProperties("TextMovement").getInt();
    }

    public Dimension setTextMovement(int textMovement) {
        setProperties("TextMovement", textMovement);
        return this;
    }

    public Variant getTextOverride() {
        return getProperties("TextOverride");
    }

    public Dimension setTextOverride(Variant textOverride) {
        setProperties("TextOverride", textOverride);
        return this;
    }

    public Variant getTextPosition() {
        return getProperties("TextPosition");
    }

    public Dimension setTextPosition(Variant textPosition) {
        setProperties("TextPosition", textPosition);
        return this;
    }

    public Variant getTextPrefix() {
        return getProperties("TextPrefix");
    }

    public Dimension setTextPrefix(Variant textPrefix) {
        setProperties("TextPrefix", textPrefix);
        return this;
    }

    public double getTextRotation() {
        return getProperties("TextRotation").getDouble();
    }

    public Dimension setTextRotation(double textRotation) {
        setProperties("TextRotation", textRotation);
        return this;
    }

    public Variant getTextStyle() {
        return getProperties("TextStyle");
    }

    public Dimension setTextStyle(Variant textStyle) {
        setProperties("TextStyle", textStyle);
        return this;
    }

    public Variant getTextSuffix() {
        return getProperties("TextSuffix");
    }

    public Dimension setTextSuffix(Variant textSuffix) {
        setProperties("TextSuffix", textSuffix);
        return this;
    }

    public int getToleranceDisplay() {
        return getProperties("ToleranceDisplay").getInt();
    }

    public Dimension setToleranceDisplay(int toleranceDisplay) {
        setProperties("ToleranceDisplay", toleranceDisplay);
        return this;
    }

    public double getToleranceHeightScale() {
        return getProperties("ToleranceHeightScale").getDouble();
    }

    public Dimension setToleranceHeightScale(double toleranceHeightScale) {
        setProperties("ToleranceHeightScale", toleranceHeightScale);
        return this;
    }

    public int getToleranceJustification() {
        return getProperties("ToleranceJustification").getInt();
    }

    public Dimension setToleranceJustification(int toleranceJustification) {
        setProperties("ToleranceJustification", toleranceJustification);
        return this;
    }

    public Variant getToleranceLowerLimit() {
        return getProperties("ToleranceLowerLimit");
    }

    public Dimension setToleranceLowerLimit(Variant toleranceLowerLimit) {
        setProperties("ToleranceLowerLimit", toleranceLowerLimit);
        return this;
    }

    public int getTolerancePrecision() {
        return getProperties("TolerancePrecision").getInt();
    }

    public Dimension setTolerancePrecision(int tolerancePrecision) {
        setProperties("TolerancePrecision", tolerancePrecision);
        return this;
    }

    public Variant getToleranceSuppressLeadingZeros() {
        return getProperties("ToleranceSuppressLeadingZeros");
    }

    public Dimension setToleranceSuppressLeadingZeros(Variant toleranceSuppressLeadingZeros) {
        setProperties("ToleranceSuppressLeadingZeros", toleranceSuppressLeadingZeros);
        return this;
    }

    public Variant getToleranceSuppressTrailingZeros() {
        return getProperties("ToleranceSuppressTrailingZeros");
    }

    public Dimension setToleranceSuppressTrailingZeros(Variant toleranceSuppressTrailingZeros) {
        setProperties("ToleranceSuppressTrailingZeros", toleranceSuppressTrailingZeros);
        return this;
    }

    public Variant getToleranceUpperLimit() {
        return getProperties("ToleranceUpperLimit");
    }

    public Dimension setToleranceUpperLimit(Variant toleranceUpperLimit) {
        setProperties("ToleranceUpperLimit", toleranceUpperLimit);
        return this;
    }

    public int getVerticalTextPosition() {
        return getProperties("VerticalTextPosition").getInt();
    }

    public Dimension setVerticalTextPosition(int verticalTextPosition) {
        setProperties("VerticalTextPosition", verticalTextPosition);
        return this;
    }

}
// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * DimAngular - AutoCAD DimAngular 实体
 */
@AcadEntityName("AcDb2LineAngularDimension")
public class DimAngular extends Dimension {

    public DimAngular(Dispatch dispatch) {
        super(dispatch);
    }

    public int getAngleFormat() {
        return getProperties("AngleFormat").getInt();
    }

    public DimAngular setAngleFormat(int angleFormat) {
        setProperties("AngleFormat", angleFormat);
        return this;
    }

    public Variant getArrowhead1Block() {
        return getProperties("Arrowhead1Block");
    }

    public DimAngular setArrowhead1Block(Variant arrowhead1Block) {
        setProperties("Arrowhead1Block", arrowhead1Block);
        return this;
    }

    public int getArrowhead1Type() {
        return getProperties("Arrowhead1Type").getInt();
    }

    public DimAngular setArrowhead1Type(int arrowhead1Type) {
        setProperties("Arrowhead1Type", arrowhead1Type);
        return this;
    }

    public Variant getArrowhead2Block() {
        return getProperties("Arrowhead2Block");
    }

    public DimAngular setArrowhead2Block(Variant arrowhead2Block) {
        setProperties("Arrowhead2Block", arrowhead2Block);
        return this;
    }

    public int getArrowhead2Type() {
        return getProperties("Arrowhead2Type").getInt();
    }

    public DimAngular setArrowhead2Type(int arrowhead2Type) {
        setProperties("Arrowhead2Type", arrowhead2Type);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public DimAngular setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public Variant getDimConstrDesc() {
        return getProperties("DimConstrDesc");
    }

    public DimAngular setDimConstrDesc(Variant dimConstrDesc) {
        setProperties("DimConstrDesc", dimConstrDesc);
        return this;
    }

    public Variant getDimConstrExpression() {
        return getProperties("DimConstrExpression");
    }

    public DimAngular setDimConstrExpression(Variant dimConstrExpression) {
        setProperties("DimConstrExpression", dimConstrExpression);
        return this;
    }

    public Variant getDimConstrForm() {
        return getProperties("DimConstrForm");
    }

    public DimAngular setDimConstrForm(Variant dimConstrForm) {
        setProperties("DimConstrForm", dimConstrForm);
        return this;
    }

    public String getDimConstrName() {
        return getProperties("DimConstrName").getString();
    }

    public DimAngular setDimConstrName(String dimConstrName) {
        setProperties("DimConstrName", dimConstrName);
        return this;
    }

    public Variant getDimConstrReference() {
        return getProperties("DimConstrReference");
    }

    public DimAngular setDimConstrReference(Variant dimConstrReference) {
        setProperties("DimConstrReference", dimConstrReference);
        return this;
    }

    public Variant getDimConstrValue() {
        return getProperties("DimConstrValue");
    }

    public DimAngular setDimConstrValue(Variant dimConstrValue) {
        setProperties("DimConstrValue", dimConstrValue);
        return this;
    }

    public Variant getDimLine1Suppress() {
        return getProperties("DimLine1Suppress");
    }

    public DimAngular setDimLine1Suppress(Variant dimLine1Suppress) {
        setProperties("DimLine1Suppress", dimLine1Suppress);
        return this;
    }

    public Variant getDimLine2Suppress() {
        return getProperties("DimLine2Suppress");
    }

    public DimAngular setDimLine2Suppress(Variant dimLine2Suppress) {
        setProperties("DimLine2Suppress", dimLine2Suppress);
        return this;
    }

    public Variant getDimLineInside() {
        return getProperties("DimLineInside");
    }

    public DimAngular setDimLineInside(Variant dimLineInside) {
        setProperties("DimLineInside", dimLineInside);
        return this;
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public DimAngular setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public int getDimensionLineWeight() {
        return getProperties("DimensionLineWeight").getInt();
    }

    public DimAngular setDimensionLineWeight(int dimensionLineWeight) {
        setProperties("DimensionLineWeight", dimensionLineWeight);
        return this;
    }

    public Variant getDimensionLinetype() {
        return getProperties("DimensionLinetype");
    }

    public DimAngular setDimensionLinetype(Variant dimensionLinetype) {
        setProperties("DimensionLinetype", dimensionLinetype);
        return this;
    }

    public Point getExtLine1EndPoint() {
        return Point.of(getProperties("ExtLine1EndPoint").toSafeArray());
    }

    public DimAngular setExtLine1EndPoint(Point extLine1EndPoint) {
        setProperties("ExtLine1EndPoint", extLine1EndPoint.toVariant());
        return this;
    }

    public Variant getExtLine1Linetype() {
        return getProperties("ExtLine1Linetype");
    }

    public DimAngular setExtLine1Linetype(Variant extLine1Linetype) {
        setProperties("ExtLine1Linetype", extLine1Linetype);
        return this;
    }

    public Point getExtLine1StartPoint() {
        return Point.of(getProperties("ExtLine1StartPoint").toSafeArray());
    }

    public DimAngular setExtLine1StartPoint(Point extLine1StartPoint) {
        setProperties("ExtLine1StartPoint", extLine1StartPoint.toVariant());
        return this;
    }

    public Variant getExtLine1Suppress() {
        return getProperties("ExtLine1Suppress");
    }

    public DimAngular setExtLine1Suppress(Variant extLine1Suppress) {
        setProperties("ExtLine1Suppress", extLine1Suppress);
        return this;
    }

    public Point getExtLine2EndPoint() {
        return Point.of(getProperties("ExtLine2EndPoint").toSafeArray());
    }

    public DimAngular setExtLine2EndPoint(Point extLine2EndPoint) {
        setProperties("ExtLine2EndPoint", extLine2EndPoint.toVariant());
        return this;
    }

    public Variant getExtLine2Linetype() {
        return getProperties("ExtLine2Linetype");
    }

    public DimAngular setExtLine2Linetype(Variant extLine2Linetype) {
        setProperties("ExtLine2Linetype", extLine2Linetype);
        return this;
    }

    public Point getExtLine2StartPoint() {
        return Point.of(getProperties("ExtLine2StartPoint").toSafeArray());
    }

    public DimAngular setExtLine2StartPoint(Point extLine2StartPoint) {
        setProperties("ExtLine2StartPoint", extLine2StartPoint.toVariant());
        return this;
    }

    public Variant getExtLine2Suppress() {
        return getProperties("ExtLine2Suppress");
    }

    public DimAngular setExtLine2Suppress(Variant extLine2Suppress) {
        setProperties("ExtLine2Suppress", extLine2Suppress);
        return this;
    }

    public Variant getExtLineFixedLen() {
        return getProperties("ExtLineFixedLen");
    }

    public DimAngular setExtLineFixedLen(Variant extLineFixedLen) {
        setProperties("ExtLineFixedLen", extLineFixedLen);
        return this;
    }

    public Variant getExtLineFixedLenSuppress() {
        return getProperties("ExtLineFixedLenSuppress");
    }

    public DimAngular setExtLineFixedLenSuppress(Variant extLineFixedLenSuppress) {
        setProperties("ExtLineFixedLenSuppress", extLineFixedLenSuppress);
        return this;
    }

    public int getExtensionLineColor() {
        return getProperties("ExtensionLineColor").getInt();
    }

    public DimAngular setExtensionLineColor(int extensionLineColor) {
        setProperties("ExtensionLineColor", extensionLineColor);
        return this;
    }

    public Variant getExtensionLineExtend() {
        return getProperties("ExtensionLineExtend");
    }

    public DimAngular setExtensionLineExtend(Variant extensionLineExtend) {
        setProperties("ExtensionLineExtend", extensionLineExtend);
        return this;
    }

    public Variant getExtensionLineOffset() {
        return getProperties("ExtensionLineOffset");
    }

    public DimAngular setExtensionLineOffset(Variant extensionLineOffset) {
        setProperties("ExtensionLineOffset", extensionLineOffset);
        return this;
    }

    public int getExtensionLineWeight() {
        return getProperties("ExtensionLineWeight").getInt();
    }

    public DimAngular setExtensionLineWeight(int extensionLineWeight) {
        setProperties("ExtensionLineWeight", extensionLineWeight);
        return this;
    }

    public int getFit() {
        return getProperties("Fit").getInt();
    }

    public DimAngular setFit(int fit) {
        setProperties("Fit", fit);
        return this;
    }

    public Variant getForceLineInside() {
        return getProperties("ForceLineInside");
    }

    public DimAngular setForceLineInside(Variant forceLineInside) {
        setProperties("ForceLineInside", forceLineInside);
        return this;
    }

    public int getHorizontalTextPosition() {
        return getProperties("HorizontalTextPosition").getInt();
    }

    public DimAngular setHorizontalTextPosition(int horizontalTextPosition) {
        setProperties("HorizontalTextPosition", horizontalTextPosition);
        return this;
    }

    public double getMeasurement() {
        return getProperties("Measurement").getDouble();
    }

    public Variant getTextInside() {
        return getProperties("TextInside");
    }

    public DimAngular setTextInside(Variant textInside) {
        setProperties("TextInside", textInside);
        return this;
    }

    public Variant getTextInsideAlign() {
        return getProperties("TextInsideAlign");
    }

    public DimAngular setTextInsideAlign(Variant textInsideAlign) {
        setProperties("TextInsideAlign", textInsideAlign);
        return this;
    }

    public Variant getTextOutsideAlign() {
        return getProperties("TextOutsideAlign");
    }

    public DimAngular setTextOutsideAlign(Variant textOutsideAlign) {
        setProperties("TextOutsideAlign", textOutsideAlign);
        return this;
    }

    public int getTextPrecision() {
        return getProperties("TextPrecision").getInt();
    }

    public DimAngular setTextPrecision(int textPrecision) {
        setProperties("TextPrecision", textPrecision);
        return this;
    }

}
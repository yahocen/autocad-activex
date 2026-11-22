package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Dim3PointAngular - AutoCAD Dim3PointAngular 实体
 */
@AcadEntityName("AcDb3PointAngularDimension")
public class Dim3PointAngular extends Dimension {

    public Dim3PointAngular(Dispatch dispatch) {
        super(dispatch);
    }

    public int getAngleFormat() {
        return getProperties("AngleFormat").getInt();
    }

    public Dim3PointAngular setAngleFormat(int angleFormat) {
        setProperties("AngleFormat", angleFormat);
        return this;
    }

    public double getAngleVertex() {
        return getProperties("AngleVertex").getDouble();
    }

    public Dim3PointAngular setAngleVertex(double angleVertex) {
        setProperties("AngleVertex", angleVertex);
        return this;
    }

    public Variant getArrowhead1Block() {
        return getProperties("Arrowhead1Block");
    }

    public Dim3PointAngular setArrowhead1Block(Variant arrowhead1Block) {
        setProperties("Arrowhead1Block", arrowhead1Block);
        return this;
    }

    public int getArrowhead1Type() {
        return getProperties("Arrowhead1Type").getInt();
    }

    public Dim3PointAngular setArrowhead1Type(int arrowhead1Type) {
        setProperties("Arrowhead1Type", arrowhead1Type);
        return this;
    }

    public Variant getArrowhead2Block() {
        return getProperties("Arrowhead2Block");
    }

    public Dim3PointAngular setArrowhead2Block(Variant arrowhead2Block) {
        setProperties("Arrowhead2Block", arrowhead2Block);
        return this;
    }

    public int getArrowhead2Type() {
        return getProperties("Arrowhead2Type").getInt();
    }

    public Dim3PointAngular setArrowhead2Type(int arrowhead2Type) {
        setProperties("Arrowhead2Type", arrowhead2Type);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public Dim3PointAngular setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public Variant getDimConstrDesc() {
        return getProperties("DimConstrDesc");
    }

    public Dim3PointAngular setDimConstrDesc(Variant dimConstrDesc) {
        setProperties("DimConstrDesc", dimConstrDesc);
        return this;
    }

    public Variant getDimConstrExpression() {
        return getProperties("DimConstrExpression");
    }

    public Dim3PointAngular setDimConstrExpression(Variant dimConstrExpression) {
        setProperties("DimConstrExpression", dimConstrExpression);
        return this;
    }

    public Variant getDimConstrForm() {
        return getProperties("DimConstrForm");
    }

    public Dim3PointAngular setDimConstrForm(Variant dimConstrForm) {
        setProperties("DimConstrForm", dimConstrForm);
        return this;
    }

    public String getDimConstrName() {
        return getProperties("DimConstrName").getString();
    }

    public Dim3PointAngular setDimConstrName(String dimConstrName) {
        setProperties("DimConstrName", dimConstrName);
        return this;
    }

    public Variant getDimConstrReference() {
        return getProperties("DimConstrReference");
    }

    public Dim3PointAngular setDimConstrReference(Variant dimConstrReference) {
        setProperties("DimConstrReference", dimConstrReference);
        return this;
    }

    public Variant getDimConstrValue() {
        return getProperties("DimConstrValue");
    }

    public Dim3PointAngular setDimConstrValue(Variant dimConstrValue) {
        setProperties("DimConstrValue", dimConstrValue);
        return this;
    }

    public Variant getDimLine1Suppress() {
        return getProperties("DimLine1Suppress");
    }

    public Dim3PointAngular setDimLine1Suppress(Variant dimLine1Suppress) {
        setProperties("DimLine1Suppress", dimLine1Suppress);
        return this;
    }

    public Variant getDimLine2Suppress() {
        return getProperties("DimLine2Suppress");
    }

    public Dim3PointAngular setDimLine2Suppress(Variant dimLine2Suppress) {
        setProperties("DimLine2Suppress", dimLine2Suppress);
        return this;
    }

    public Variant getDimLineInside() {
        return getProperties("DimLineInside");
    }

    public Dim3PointAngular setDimLineInside(Variant dimLineInside) {
        setProperties("DimLineInside", dimLineInside);
        return this;
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public Dim3PointAngular setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public int getDimensionLineWeight() {
        return getProperties("DimensionLineWeight").getInt();
    }

    public Dim3PointAngular setDimensionLineWeight(int dimensionLineWeight) {
        setProperties("DimensionLineWeight", dimensionLineWeight);
        return this;
    }

    public Variant getDimensionLinetype() {
        return getProperties("DimensionLinetype");
    }

    public Dim3PointAngular setDimensionLinetype(Variant dimensionLinetype) {
        setProperties("DimensionLinetype", dimensionLinetype);
        return this;
    }

    public Point getExtLine1EndPoint() {
        return Point.of(getProperties("ExtLine1EndPoint").toSafeArray());
    }

    public Dim3PointAngular setExtLine1EndPoint(Point extLine1EndPoint) {
        setProperties("ExtLine1EndPoint", extLine1EndPoint.toVariant());
        return this;
    }

    public Variant getExtLine1Linetype() {
        return getProperties("ExtLine1Linetype");
    }

    public Dim3PointAngular setExtLine1Linetype(Variant extLine1Linetype) {
        setProperties("ExtLine1Linetype", extLine1Linetype);
        return this;
    }

    public Variant getExtLine1Suppress() {
        return getProperties("ExtLine1Suppress");
    }

    public Dim3PointAngular setExtLine1Suppress(Variant extLine1Suppress) {
        setProperties("ExtLine1Suppress", extLine1Suppress);
        return this;
    }

    public Point getExtLine2EndPoint() {
        return Point.of(getProperties("ExtLine2EndPoint").toSafeArray());
    }

    public Dim3PointAngular setExtLine2EndPoint(Point extLine2EndPoint) {
        setProperties("ExtLine2EndPoint", extLine2EndPoint.toVariant());
        return this;
    }

    public Variant getExtLine2Linetype() {
        return getProperties("ExtLine2Linetype");
    }

    public Dim3PointAngular setExtLine2Linetype(Variant extLine2Linetype) {
        setProperties("ExtLine2Linetype", extLine2Linetype);
        return this;
    }

    public Variant getExtLine2Suppress() {
        return getProperties("ExtLine2Suppress");
    }

    public Dim3PointAngular setExtLine2Suppress(Variant extLine2Suppress) {
        setProperties("ExtLine2Suppress", extLine2Suppress);
        return this;
    }

    public Variant getExtLineFixedLen() {
        return getProperties("ExtLineFixedLen");
    }

    public Dim3PointAngular setExtLineFixedLen(Variant extLineFixedLen) {
        setProperties("ExtLineFixedLen", extLineFixedLen);
        return this;
    }

    public Variant getExtLineFixedLenSuppress() {
        return getProperties("ExtLineFixedLenSuppress");
    }

    public Dim3PointAngular setExtLineFixedLenSuppress(Variant extLineFixedLenSuppress) {
        setProperties("ExtLineFixedLenSuppress", extLineFixedLenSuppress);
        return this;
    }

    public int getExtensionLineColor() {
        return getProperties("ExtensionLineColor").getInt();
    }

    public Dim3PointAngular setExtensionLineColor(int extensionLineColor) {
        setProperties("ExtensionLineColor", extensionLineColor);
        return this;
    }

    public Variant getExtensionLineExtend() {
        return getProperties("ExtensionLineExtend");
    }

    public Dim3PointAngular setExtensionLineExtend(Variant extensionLineExtend) {
        setProperties("ExtensionLineExtend", extensionLineExtend);
        return this;
    }

    public Variant getExtensionLineOffset() {
        return getProperties("ExtensionLineOffset");
    }

    public Dim3PointAngular setExtensionLineOffset(Variant extensionLineOffset) {
        setProperties("ExtensionLineOffset", extensionLineOffset);
        return this;
    }

    public int getExtensionLineWeight() {
        return getProperties("ExtensionLineWeight").getInt();
    }

    public Dim3PointAngular setExtensionLineWeight(int extensionLineWeight) {
        setProperties("ExtensionLineWeight", extensionLineWeight);
        return this;
    }

    public int getFit() {
        return getProperties("Fit").getInt();
    }

    public Dim3PointAngular setFit(int fit) {
        setProperties("Fit", fit);
        return this;
    }

    public Variant getForceLineInside() {
        return getProperties("ForceLineInside");
    }

    public Dim3PointAngular setForceLineInside(Variant forceLineInside) {
        setProperties("ForceLineInside", forceLineInside);
        return this;
    }

    public int getHorizontalTextPosition() {
        return getProperties("HorizontalTextPosition").getInt();
    }

    public Dim3PointAngular setHorizontalTextPosition(int horizontalTextPosition) {
        setProperties("HorizontalTextPosition", horizontalTextPosition);
        return this;
    }

    public double getMeasurement() {
        return getProperties("Measurement").getDouble();
    }

    public Variant getTextInside() {
        return getProperties("TextInside");
    }

    public Dim3PointAngular setTextInside(Variant textInside) {
        setProperties("TextInside", textInside);
        return this;
    }

    public Variant getTextInsideAlign() {
        return getProperties("TextInsideAlign");
    }

    public Dim3PointAngular setTextInsideAlign(Variant textInsideAlign) {
        setProperties("TextInsideAlign", textInsideAlign);
        return this;
    }

    public Variant getTextOutsideAlign() {
        return getProperties("TextOutsideAlign");
    }

    public Dim3PointAngular setTextOutsideAlign(Variant textOutsideAlign) {
        setProperties("TextOutsideAlign", textOutsideAlign);
        return this;
    }

    public int getTextPrecision() {
        return getProperties("TextPrecision").getInt();
    }

    public Dim3PointAngular setTextPrecision(int textPrecision) {
        setProperties("TextPrecision", textPrecision);
        return this;
    }

}
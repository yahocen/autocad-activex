package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * DimArcLength - AutoCAD DimArcLength 实体
 */
@AcadEntityName("AcDbArcDimension")
public class DimArcLength extends Dimension {

    public DimArcLength(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAltRoundDistance() {
        return getProperties("AltRoundDistance");
    }

    public DimArcLength setAltRoundDistance(Variant altRoundDistance) {
        setProperties("AltRoundDistance", altRoundDistance);
        return this;
    }

    public Variant getAltSubUnitsFactor() {
        return getProperties("AltSubUnitsFactor");
    }

    public DimArcLength setAltSubUnitsFactor(Variant altSubUnitsFactor) {
        setProperties("AltSubUnitsFactor", altSubUnitsFactor);
        return this;
    }

    public Variant getAltSubUnitsSuffix() {
        return getProperties("AltSubUnitsSuffix");
    }

    public DimArcLength setAltSubUnitsSuffix(Variant altSubUnitsSuffix) {
        setProperties("AltSubUnitsSuffix", altSubUnitsSuffix);
        return this;
    }

    public Variant getAltSuppressLeadingZeros() {
        return getProperties("AltSuppressLeadingZeros");
    }

    public DimArcLength setAltSuppressLeadingZeros(Variant altSuppressLeadingZeros) {
        setProperties("AltSuppressLeadingZeros", altSuppressLeadingZeros);
        return this;
    }

    public Variant getAltSuppressTrailingZeros() {
        return getProperties("AltSuppressTrailingZeros");
    }

    public DimArcLength setAltSuppressTrailingZeros(Variant altSuppressTrailingZeros) {
        setProperties("AltSuppressTrailingZeros", altSuppressTrailingZeros);
        return this;
    }

    public Variant getAltSuppressZeroFeet() {
        return getProperties("AltSuppressZeroFeet");
    }

    public DimArcLength setAltSuppressZeroFeet(Variant altSuppressZeroFeet) {
        setProperties("AltSuppressZeroFeet", altSuppressZeroFeet);
        return this;
    }

    public Variant getAltSuppressZeroInches() {
        return getProperties("AltSuppressZeroInches");
    }

    public DimArcLength setAltSuppressZeroInches(Variant altSuppressZeroInches) {
        setProperties("AltSuppressZeroInches", altSuppressZeroInches);
        return this;
    }

    public Variant getAltTextPrefix() {
        return getProperties("AltTextPrefix");
    }

    public DimArcLength setAltTextPrefix(Variant altTextPrefix) {
        setProperties("AltTextPrefix", altTextPrefix);
        return this;
    }

    public Variant getAltTextSuffix() {
        return getProperties("AltTextSuffix");
    }

    public DimArcLength setAltTextSuffix(Variant altTextSuffix) {
        setProperties("AltTextSuffix", altTextSuffix);
        return this;
    }

    public int getAltTolerancePrecision() {
        return getProperties("AltTolerancePrecision").getInt();
    }

    public DimArcLength setAltTolerancePrecision(int altTolerancePrecision) {
        setProperties("AltTolerancePrecision", altTolerancePrecision);
        return this;
    }

    public Variant getAltToleranceSuppressLeadingZeros() {
        return getProperties("AltToleranceSuppressLeadingZeros");
    }

    public DimArcLength setAltToleranceSuppressLeadingZeros(Variant altToleranceSuppressLeadingZeros) {
        setProperties("AltToleranceSuppressLeadingZeros", altToleranceSuppressLeadingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressTrailingZeros() {
        return getProperties("AltToleranceSuppressTrailingZeros");
    }

    public DimArcLength setAltToleranceSuppressTrailingZeros(Variant altToleranceSuppressTrailingZeros) {
        setProperties("AltToleranceSuppressTrailingZeros", altToleranceSuppressTrailingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressZeroFeet() {
        return getProperties("AltToleranceSuppressZeroFeet");
    }

    public DimArcLength setAltToleranceSuppressZeroFeet(Variant altToleranceSuppressZeroFeet) {
        setProperties("AltToleranceSuppressZeroFeet", altToleranceSuppressZeroFeet);
        return this;
    }

    public Variant getAltToleranceSuppressZeroInches() {
        return getProperties("AltToleranceSuppressZeroInches");
    }

    public DimArcLength setAltToleranceSuppressZeroInches(Variant altToleranceSuppressZeroInches) {
        setProperties("AltToleranceSuppressZeroInches", altToleranceSuppressZeroInches);
        return this;
    }

    public Variant getAltUnits() {
        return getProperties("AltUnits");
    }

    public DimArcLength setAltUnits(Variant altUnits) {
        setProperties("AltUnits", altUnits);
        return this;
    }

    public int getAltUnitsFormat() {
        return getProperties("AltUnitsFormat").getInt();
    }

    public DimArcLength setAltUnitsFormat(int altUnitsFormat) {
        setProperties("AltUnitsFormat", altUnitsFormat);
        return this;
    }

    public int getAltUnitsPrecision() {
        return getProperties("AltUnitsPrecision").getInt();
    }

    public DimArcLength setAltUnitsPrecision(int altUnitsPrecision) {
        setProperties("AltUnitsPrecision", altUnitsPrecision);
        return this;
    }

    public double getAltUnitsScale() {
        return getProperties("AltUnitsScale").getDouble();
    }

    public DimArcLength setAltUnitsScale(double altUnitsScale) {
        setProperties("AltUnitsScale", altUnitsScale);
        return this;
    }

    public Variant getArcEndParam() {
        return getProperties("ArcEndParam");
    }

    public DimArcLength setArcEndParam(Variant arcEndParam) {
        setProperties("ArcEndParam", arcEndParam);
        return this;
    }

    public Point getArcPoint() {
        return Point.of(getProperties("ArcPoint").toSafeArray());
    }

    public DimArcLength setArcPoint(Point arcPoint) {
        setProperties("ArcPoint", arcPoint.toVariant());
        return this;
    }

    public Variant getArcStartParam() {
        return getProperties("ArcStartParam");
    }

    public DimArcLength setArcStartParam(Variant arcStartParam) {
        setProperties("ArcStartParam", arcStartParam);
        return this;
    }

    public Variant getArrowhead1Block() {
        return getProperties("Arrowhead1Block");
    }

    public DimArcLength setArrowhead1Block(Variant arrowhead1Block) {
        setProperties("Arrowhead1Block", arrowhead1Block);
        return this;
    }

    public int getArrowhead1Type() {
        return getProperties("Arrowhead1Type").getInt();
    }

    public DimArcLength setArrowhead1Type(int arrowhead1Type) {
        setProperties("Arrowhead1Type", arrowhead1Type);
        return this;
    }

    public Variant getArrowhead2Block() {
        return getProperties("Arrowhead2Block");
    }

    public DimArcLength setArrowhead2Block(Variant arrowhead2Block) {
        setProperties("Arrowhead2Block", arrowhead2Block);
        return this;
    }

    public int getArrowhead2Type() {
        return getProperties("Arrowhead2Type").getInt();
    }

    public DimArcLength setArrowhead2Type(int arrowhead2Type) {
        setProperties("Arrowhead2Type", arrowhead2Type);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public DimArcLength setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public Point getCenterPoint() {
        return Point.of(getProperties("CenterPoint").toSafeArray());
    }

    public DimArcLength setCenterPoint(Point centerPoint) {
        setProperties("CenterPoint", centerPoint.toVariant());
        return this;
    }

    public Variant getDimLine1Suppress() {
        return getProperties("DimLine1Suppress");
    }

    public DimArcLength setDimLine1Suppress(Variant dimLine1Suppress) {
        setProperties("DimLine1Suppress", dimLine1Suppress);
        return this;
    }

    public Variant getDimLine2Suppress() {
        return getProperties("DimLine2Suppress");
    }

    public DimArcLength setDimLine2Suppress(Variant dimLine2Suppress) {
        setProperties("DimLine2Suppress", dimLine2Suppress);
        return this;
    }

    public Variant getDimLineInside() {
        return getProperties("DimLineInside");
    }

    public DimArcLength setDimLineInside(Variant dimLineInside) {
        setProperties("DimLineInside", dimLineInside);
        return this;
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public DimArcLength setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public Variant getDimensionLineExtend() {
        return getProperties("DimensionLineExtend");
    }

    public DimArcLength setDimensionLineExtend(Variant dimensionLineExtend) {
        setProperties("DimensionLineExtend", dimensionLineExtend);
        return this;
    }

    public int getDimensionLineWeight() {
        return getProperties("DimensionLineWeight").getInt();
    }

    public DimArcLength setDimensionLineWeight(int dimensionLineWeight) {
        setProperties("DimensionLineWeight", dimensionLineWeight);
        return this;
    }

    public Variant getDimensionLinetype() {
        return getProperties("DimensionLinetype");
    }

    public DimArcLength setDimensionLinetype(Variant dimensionLinetype) {
        setProperties("DimensionLinetype", dimensionLinetype);
        return this;
    }

    public Variant getExtLine1Linetype() {
        return getProperties("ExtLine1Linetype");
    }

    public DimArcLength setExtLine1Linetype(Variant extLine1Linetype) {
        setProperties("ExtLine1Linetype", extLine1Linetype);
        return this;
    }

    public Point getExtLine1Point() {
        return Point.of(getProperties("ExtLine1Point").toSafeArray());
    }

    public DimArcLength setExtLine1Point(Point extLine1Point) {
        setProperties("ExtLine1Point", extLine1Point.toVariant());
        return this;
    }

    public Variant getExtLine1Suppress() {
        return getProperties("ExtLine1Suppress");
    }

    public DimArcLength setExtLine1Suppress(Variant extLine1Suppress) {
        setProperties("ExtLine1Suppress", extLine1Suppress);
        return this;
    }

    public Variant getExtLine2Linetype() {
        return getProperties("ExtLine2Linetype");
    }

    public DimArcLength setExtLine2Linetype(Variant extLine2Linetype) {
        setProperties("ExtLine2Linetype", extLine2Linetype);
        return this;
    }

    public Point getExtLine2Point() {
        return Point.of(getProperties("ExtLine2Point").toSafeArray());
    }

    public DimArcLength setExtLine2Point(Point extLine2Point) {
        setProperties("ExtLine2Point", extLine2Point.toVariant());
        return this;
    }

    public Variant getExtLine2Suppress() {
        return getProperties("ExtLine2Suppress");
    }

    public DimArcLength setExtLine2Suppress(Variant extLine2Suppress) {
        setProperties("ExtLine2Suppress", extLine2Suppress);
        return this;
    }

    public Variant getExtLineFixedLen() {
        return getProperties("ExtLineFixedLen");
    }

    public DimArcLength setExtLineFixedLen(Variant extLineFixedLen) {
        setProperties("ExtLineFixedLen", extLineFixedLen);
        return this;
    }

    public Variant getExtLineFixedLenSuppress() {
        return getProperties("ExtLineFixedLenSuppress");
    }

    public DimArcLength setExtLineFixedLenSuppress(Variant extLineFixedLenSuppress) {
        setProperties("ExtLineFixedLenSuppress", extLineFixedLenSuppress);
        return this;
    }

    public int getExtensionLineColor() {
        return getProperties("ExtensionLineColor").getInt();
    }

    public DimArcLength setExtensionLineColor(int extensionLineColor) {
        setProperties("ExtensionLineColor", extensionLineColor);
        return this;
    }

    public Variant getExtensionLineExtend() {
        return getProperties("ExtensionLineExtend");
    }

    public DimArcLength setExtensionLineExtend(Variant extensionLineExtend) {
        setProperties("ExtensionLineExtend", extensionLineExtend);
        return this;
    }

    public Variant getExtensionLineOffset() {
        return getProperties("ExtensionLineOffset");
    }

    public DimArcLength setExtensionLineOffset(Variant extensionLineOffset) {
        setProperties("ExtensionLineOffset", extensionLineOffset);
        return this;
    }

    public int getExtensionLineWeight() {
        return getProperties("ExtensionLineWeight").getInt();
    }

    public DimArcLength setExtensionLineWeight(int extensionLineWeight) {
        setProperties("ExtensionLineWeight", extensionLineWeight);
        return this;
    }

    public int getFit() {
        return getProperties("Fit").getInt();
    }

    public DimArcLength setFit(int fit) {
        setProperties("Fit", fit);
        return this;
    }

    public Variant getForceLineInside() {
        return getProperties("ForceLineInside");
    }

    public DimArcLength setForceLineInside(Variant forceLineInside) {
        setProperties("ForceLineInside", forceLineInside);
        return this;
    }

    public int getFractionFormat() {
        return getProperties("FractionFormat").getInt();
    }

    public DimArcLength setFractionFormat(int fractionFormat) {
        setProperties("FractionFormat", fractionFormat);
        return this;
    }

    public Variant getHasLeader() {
        return getProperties("HasLeader");
    }

    public DimArcLength setHasLeader(Variant hasLeader) {
        setProperties("HasLeader", hasLeader);
        return this;
    }

    public int getHorizontalTextPosition() {
        return getProperties("HorizontalTextPosition").getInt();
    }

    public DimArcLength setHorizontalTextPosition(int horizontalTextPosition) {
        setProperties("HorizontalTextPosition", horizontalTextPosition);
        return this;
    }

    public Variant getIsPartial() {
        return getProperties("IsPartial");
    }

    public DimArcLength setIsPartial(Variant isPartial) {
        setProperties("IsPartial", isPartial);
        return this;
    }

    public Point getLeader1Point() {
        return Point.of(getProperties("Leader1Point").toSafeArray());
    }

    public DimArcLength setLeader1Point(Point leader1Point) {
        setProperties("Leader1Point", leader1Point.toVariant());
        return this;
    }

    public Point getLeader2Point() {
        return Point.of(getProperties("Leader2Point").toSafeArray());
    }

    public DimArcLength setLeader2Point(Point leader2Point) {
        setProperties("Leader2Point", leader2Point.toVariant());
        return this;
    }

    public double getLinearScaleFactor() {
        return getProperties("LinearScaleFactor").getDouble();
    }

    public DimArcLength setLinearScaleFactor(double linearScaleFactor) {
        setProperties("LinearScaleFactor", linearScaleFactor);
        return this;
    }

    public Variant getMeasurement() {
        return getProperties("Measurement");
    }

    public int getPrimaryUnitsPrecision() {
        return getProperties("PrimaryUnitsPrecision").getInt();
    }

    public DimArcLength setPrimaryUnitsPrecision(int primaryUnitsPrecision) {
        setProperties("PrimaryUnitsPrecision", primaryUnitsPrecision);
        return this;
    }

    public Variant getRoundDistance() {
        return getProperties("RoundDistance");
    }

    public DimArcLength setRoundDistance(Variant roundDistance) {
        setProperties("RoundDistance", roundDistance);
        return this;
    }

    public Variant getSubUnitsFactor() {
        return getProperties("SubUnitsFactor");
    }

    public DimArcLength setSubUnitsFactor(Variant subUnitsFactor) {
        setProperties("SubUnitsFactor", subUnitsFactor);
        return this;
    }

    public Variant getSubUnitsSuffix() {
        return getProperties("SubUnitsSuffix");
    }

    public DimArcLength setSubUnitsSuffix(Variant subUnitsSuffix) {
        setProperties("SubUnitsSuffix", subUnitsSuffix);
        return this;
    }

    public Variant getSuppressZeroFeet() {
        return getProperties("SuppressZeroFeet");
    }

    public DimArcLength setSuppressZeroFeet(Variant suppressZeroFeet) {
        setProperties("SuppressZeroFeet", suppressZeroFeet);
        return this;
    }

    public Variant getSuppressZeroInches() {
        return getProperties("SuppressZeroInches");
    }

    public DimArcLength setSuppressZeroInches(Variant suppressZeroInches) {
        setProperties("SuppressZeroInches", suppressZeroInches);
        return this;
    }

    public int getSymbolPosition() {
        return getProperties("SymbolPosition").getInt();
    }

    public DimArcLength setSymbolPosition(int symbolPosition) {
        setProperties("SymbolPosition", symbolPosition);
        return this;
    }

    public Variant getTextInside() {
        return getProperties("TextInside");
    }

    public DimArcLength setTextInside(Variant textInside) {
        setProperties("TextInside", textInside);
        return this;
    }

    public Variant getTextInsideAlign() {
        return getProperties("TextInsideAlign");
    }

    public DimArcLength setTextInsideAlign(Variant textInsideAlign) {
        setProperties("TextInsideAlign", textInsideAlign);
        return this;
    }

    public Variant getTextOutsideAlign() {
        return getProperties("TextOutsideAlign");
    }

    public DimArcLength setTextOutsideAlign(Variant textOutsideAlign) {
        setProperties("TextOutsideAlign", textOutsideAlign);
        return this;
    }

    public Variant getToleranceSuppressZeroFeet() {
        return getProperties("ToleranceSuppressZeroFeet");
    }

    public DimArcLength setToleranceSuppressZeroFeet(Variant toleranceSuppressZeroFeet) {
        setProperties("ToleranceSuppressZeroFeet", toleranceSuppressZeroFeet);
        return this;
    }

    public Variant getToleranceSuppressZeroInches() {
        return getProperties("ToleranceSuppressZeroInches");
    }

    public DimArcLength setToleranceSuppressZeroInches(Variant toleranceSuppressZeroInches) {
        setProperties("ToleranceSuppressZeroInches", toleranceSuppressZeroInches);
        return this;
    }

    public int getUnitsFormat() {
        return getProperties("UnitsFormat").getInt();
    }

    public DimArcLength setUnitsFormat(int unitsFormat) {
        setProperties("UnitsFormat", unitsFormat);
        return this;
    }

}
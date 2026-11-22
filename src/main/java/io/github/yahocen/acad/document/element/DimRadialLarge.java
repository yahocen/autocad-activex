package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * DimRadialLarge - AutoCAD DimRadialLarge 实体
 */
@AcadEntityName("AcDbRadialDimensionLarge")
public class DimRadialLarge extends Dimension {

    public DimRadialLarge(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAltRoundDistance() {
        return getProperties("AltRoundDistance");
    }

    public DimRadialLarge setAltRoundDistance(Variant altRoundDistance) {
        setProperties("AltRoundDistance", altRoundDistance);
        return this;
    }

    public Variant getAltSuppressLeadingZeros() {
        return getProperties("AltSuppressLeadingZeros");
    }

    public DimRadialLarge setAltSuppressLeadingZeros(Variant altSuppressLeadingZeros) {
        setProperties("AltSuppressLeadingZeros", altSuppressLeadingZeros);
        return this;
    }

    public Variant getAltSuppressTrailingZeros() {
        return getProperties("AltSuppressTrailingZeros");
    }

    public DimRadialLarge setAltSuppressTrailingZeros(Variant altSuppressTrailingZeros) {
        setProperties("AltSuppressTrailingZeros", altSuppressTrailingZeros);
        return this;
    }

    public Variant getAltSuppressZeroFeet() {
        return getProperties("AltSuppressZeroFeet");
    }

    public DimRadialLarge setAltSuppressZeroFeet(Variant altSuppressZeroFeet) {
        setProperties("AltSuppressZeroFeet", altSuppressZeroFeet);
        return this;
    }

    public Variant getAltSuppressZeroInches() {
        return getProperties("AltSuppressZeroInches");
    }

    public DimRadialLarge setAltSuppressZeroInches(Variant altSuppressZeroInches) {
        setProperties("AltSuppressZeroInches", altSuppressZeroInches);
        return this;
    }

    public Variant getAltTextPrefix() {
        return getProperties("AltTextPrefix");
    }

    public DimRadialLarge setAltTextPrefix(Variant altTextPrefix) {
        setProperties("AltTextPrefix", altTextPrefix);
        return this;
    }

    public Variant getAltTextSuffix() {
        return getProperties("AltTextSuffix");
    }

    public DimRadialLarge setAltTextSuffix(Variant altTextSuffix) {
        setProperties("AltTextSuffix", altTextSuffix);
        return this;
    }

    public int getAltTolerancePrecision() {
        return getProperties("AltTolerancePrecision").getInt();
    }

    public DimRadialLarge setAltTolerancePrecision(int altTolerancePrecision) {
        setProperties("AltTolerancePrecision", altTolerancePrecision);
        return this;
    }

    public Variant getAltToleranceSuppressLeadingZeros() {
        return getProperties("AltToleranceSuppressLeadingZeros");
    }

    public DimRadialLarge setAltToleranceSuppressLeadingZeros(Variant altToleranceSuppressLeadingZeros) {
        setProperties("AltToleranceSuppressLeadingZeros", altToleranceSuppressLeadingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressTrailingZeros() {
        return getProperties("AltToleranceSuppressTrailingZeros");
    }

    public DimRadialLarge setAltToleranceSuppressTrailingZeros(Variant altToleranceSuppressTrailingZeros) {
        setProperties("AltToleranceSuppressTrailingZeros", altToleranceSuppressTrailingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressZeroFeet() {
        return getProperties("AltToleranceSuppressZeroFeet");
    }

    public DimRadialLarge setAltToleranceSuppressZeroFeet(Variant altToleranceSuppressZeroFeet) {
        setProperties("AltToleranceSuppressZeroFeet", altToleranceSuppressZeroFeet);
        return this;
    }

    public Variant getAltToleranceSuppressZeroInches() {
        return getProperties("AltToleranceSuppressZeroInches");
    }

    public DimRadialLarge setAltToleranceSuppressZeroInches(Variant altToleranceSuppressZeroInches) {
        setProperties("AltToleranceSuppressZeroInches", altToleranceSuppressZeroInches);
        return this;
    }

    public Variant getAltUnits() {
        return getProperties("AltUnits");
    }

    public DimRadialLarge setAltUnits(Variant altUnits) {
        setProperties("AltUnits", altUnits);
        return this;
    }

    public int getAltUnitsFormat() {
        return getProperties("AltUnitsFormat").getInt();
    }

    public DimRadialLarge setAltUnitsFormat(int altUnitsFormat) {
        setProperties("AltUnitsFormat", altUnitsFormat);
        return this;
    }

    public int getAltUnitsPrecision() {
        return getProperties("AltUnitsPrecision").getInt();
    }

    public DimRadialLarge setAltUnitsPrecision(int altUnitsPrecision) {
        setProperties("AltUnitsPrecision", altUnitsPrecision);
        return this;
    }

    public double getAltUnitsScale() {
        return getProperties("AltUnitsScale").getDouble();
    }

    public DimRadialLarge setAltUnitsScale(double altUnitsScale) {
        setProperties("AltUnitsScale", altUnitsScale);
        return this;
    }

    public Variant getArrowheadBlock() {
        return getProperties("ArrowheadBlock");
    }

    public DimRadialLarge setArrowheadBlock(Variant arrowheadBlock) {
        setProperties("ArrowheadBlock", arrowheadBlock);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public DimRadialLarge setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public int getArrowheadType() {
        return getProperties("ArrowheadType").getInt();
    }

    public DimRadialLarge setArrowheadType(int arrowheadType) {
        setProperties("ArrowheadType", arrowheadType);
        return this;
    }

    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    public DimRadialLarge setCenter(Point center) {
        setProperties("Center", center.toVariant());
        return this;
    }

    public Variant getCenterMarkSize() {
        return getProperties("CenterMarkSize");
    }

    public DimRadialLarge setCenterMarkSize(Variant centerMarkSize) {
        setProperties("CenterMarkSize", centerMarkSize);
        return this;
    }

    public int getCenterType() {
        return getProperties("CenterType").getInt();
    }

    public DimRadialLarge setCenterType(int centerType) {
        setProperties("CenterType", centerType);
        return this;
    }

    public Point getChordPoint() {
        return Point.of(getProperties("ChordPoint").toSafeArray());
    }

    public DimRadialLarge setChordPoint(Point chordPoint) {
        setProperties("ChordPoint", chordPoint.toVariant());
        return this;
    }

    public Variant getDimLineSuppress() {
        return getProperties("DimLineSuppress");
    }

    public DimRadialLarge setDimLineSuppress(Variant dimLineSuppress) {
        setProperties("DimLineSuppress", dimLineSuppress);
        return this;
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public DimRadialLarge setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public int getDimensionLineWeight() {
        return getProperties("DimensionLineWeight").getInt();
    }

    public DimRadialLarge setDimensionLineWeight(int dimensionLineWeight) {
        setProperties("DimensionLineWeight", dimensionLineWeight);
        return this;
    }

    public Variant getDimensionLinetype() {
        return getProperties("DimensionLinetype");
    }

    public DimRadialLarge setDimensionLinetype(Variant dimensionLinetype) {
        setProperties("DimensionLinetype", dimensionLinetype);
        return this;
    }

    public int getFit() {
        return getProperties("Fit").getInt();
    }

    public DimRadialLarge setFit(int fit) {
        setProperties("Fit", fit);
        return this;
    }

    public Variant getForceLineInside() {
        return getProperties("ForceLineInside");
    }

    public DimRadialLarge setForceLineInside(Variant forceLineInside) {
        setProperties("ForceLineInside", forceLineInside);
        return this;
    }

    public int getFractionFormat() {
        return getProperties("FractionFormat").getInt();
    }

    public DimRadialLarge setFractionFormat(int fractionFormat) {
        setProperties("FractionFormat", fractionFormat);
        return this;
    }

    public double getJogAngle() {
        return getProperties("JogAngle").getDouble();
    }

    public DimRadialLarge setJogAngle(double jogAngle) {
        setProperties("JogAngle", jogAngle);
        return this;
    }

    public Variant getJogLocation() {
        return getProperties("JogLocation");
    }

    public DimRadialLarge setJogLocation(Variant jogLocation) {
        setProperties("JogLocation", jogLocation);
        return this;
    }

    public double getLinearScaleFactor() {
        return getProperties("LinearScaleFactor").getDouble();
    }

    public DimRadialLarge setLinearScaleFactor(double linearScaleFactor) {
        setProperties("LinearScaleFactor", linearScaleFactor);
        return this;
    }

    public Variant getMeasurement() {
        return getProperties("Measurement");
    }

    public Variant getOverrideCenter() {
        return getProperties("OverrideCenter");
    }

    public DimRadialLarge setOverrideCenter(Variant overrideCenter) {
        setProperties("OverrideCenter", overrideCenter);
        return this;
    }

    public int getPrimaryUnitsPrecision() {
        return getProperties("PrimaryUnitsPrecision").getInt();
    }

    public DimRadialLarge setPrimaryUnitsPrecision(int primaryUnitsPrecision) {
        setProperties("PrimaryUnitsPrecision", primaryUnitsPrecision);
        return this;
    }

    public Variant getRoundDistance() {
        return getProperties("RoundDistance");
    }

    public DimRadialLarge setRoundDistance(Variant roundDistance) {
        setProperties("RoundDistance", roundDistance);
        return this;
    }

    public Variant getSuppressZeroFeet() {
        return getProperties("SuppressZeroFeet");
    }

    public DimRadialLarge setSuppressZeroFeet(Variant suppressZeroFeet) {
        setProperties("SuppressZeroFeet", suppressZeroFeet);
        return this;
    }

    public Variant getSuppressZeroInches() {
        return getProperties("SuppressZeroInches");
    }

    public DimRadialLarge setSuppressZeroInches(Variant suppressZeroInches) {
        setProperties("SuppressZeroInches", suppressZeroInches);
        return this;
    }

    public Variant getTextInside() {
        return getProperties("TextInside");
    }

    public DimRadialLarge setTextInside(Variant textInside) {
        setProperties("TextInside", textInside);
        return this;
    }

    public Variant getTextInsideAlign() {
        return getProperties("TextInsideAlign");
    }

    public DimRadialLarge setTextInsideAlign(Variant textInsideAlign) {
        setProperties("TextInsideAlign", textInsideAlign);
        return this;
    }

    public Variant getTextOutsideAlign() {
        return getProperties("TextOutsideAlign");
    }

    public DimRadialLarge setTextOutsideAlign(Variant textOutsideAlign) {
        setProperties("TextOutsideAlign", textOutsideAlign);
        return this;
    }

    public Variant getToleranceSuppressZeroFeet() {
        return getProperties("ToleranceSuppressZeroFeet");
    }

    public DimRadialLarge setToleranceSuppressZeroFeet(Variant toleranceSuppressZeroFeet) {
        setProperties("ToleranceSuppressZeroFeet", toleranceSuppressZeroFeet);
        return this;
    }

    public Variant getToleranceSuppressZeroInches() {
        return getProperties("ToleranceSuppressZeroInches");
    }

    public DimRadialLarge setToleranceSuppressZeroInches(Variant toleranceSuppressZeroInches) {
        setProperties("ToleranceSuppressZeroInches", toleranceSuppressZeroInches);
        return this;
    }

    public int getUnitsFormat() {
        return getProperties("UnitsFormat").getInt();
    }

    public DimRadialLarge setUnitsFormat(int unitsFormat) {
        setProperties("UnitsFormat", unitsFormat);
        return this;
    }

}
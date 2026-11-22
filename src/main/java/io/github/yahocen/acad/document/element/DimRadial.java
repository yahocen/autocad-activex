package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * DimRadial - AutoCAD DimRadial 实体
 */
@AcadEntityName("AcDbRadialDimension")
public class DimRadial extends Dimension {

    public DimRadial(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAltRoundDistance() {
        return getProperties("AltRoundDistance");
    }

    public DimRadial setAltRoundDistance(Variant altRoundDistance) {
        setProperties("AltRoundDistance", altRoundDistance);
        return this;
    }

    public Variant getAltSuppressLeadingZeros() {
        return getProperties("AltSuppressLeadingZeros");
    }

    public DimRadial setAltSuppressLeadingZeros(Variant altSuppressLeadingZeros) {
        setProperties("AltSuppressLeadingZeros", altSuppressLeadingZeros);
        return this;
    }

    public Variant getAltSuppressTrailingZeros() {
        return getProperties("AltSuppressTrailingZeros");
    }

    public DimRadial setAltSuppressTrailingZeros(Variant altSuppressTrailingZeros) {
        setProperties("AltSuppressTrailingZeros", altSuppressTrailingZeros);
        return this;
    }

    public Variant getAltSuppressZeroFeet() {
        return getProperties("AltSuppressZeroFeet");
    }

    public DimRadial setAltSuppressZeroFeet(Variant altSuppressZeroFeet) {
        setProperties("AltSuppressZeroFeet", altSuppressZeroFeet);
        return this;
    }

    public Variant getAltSuppressZeroInches() {
        return getProperties("AltSuppressZeroInches");
    }

    public DimRadial setAltSuppressZeroInches(Variant altSuppressZeroInches) {
        setProperties("AltSuppressZeroInches", altSuppressZeroInches);
        return this;
    }

    public Variant getAltTextPrefix() {
        return getProperties("AltTextPrefix");
    }

    public DimRadial setAltTextPrefix(Variant altTextPrefix) {
        setProperties("AltTextPrefix", altTextPrefix);
        return this;
    }

    public Variant getAltTextSuffix() {
        return getProperties("AltTextSuffix");
    }

    public DimRadial setAltTextSuffix(Variant altTextSuffix) {
        setProperties("AltTextSuffix", altTextSuffix);
        return this;
    }

    public int getAltTolerancePrecision() {
        return getProperties("AltTolerancePrecision").getInt();
    }

    public DimRadial setAltTolerancePrecision(int altTolerancePrecision) {
        setProperties("AltTolerancePrecision", altTolerancePrecision);
        return this;
    }

    public Variant getAltToleranceSuppressLeadingZeros() {
        return getProperties("AltToleranceSuppressLeadingZeros");
    }

    public DimRadial setAltToleranceSuppressLeadingZeros(Variant altToleranceSuppressLeadingZeros) {
        setProperties("AltToleranceSuppressLeadingZeros", altToleranceSuppressLeadingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressTrailingZeros() {
        return getProperties("AltToleranceSuppressTrailingZeros");
    }

    public DimRadial setAltToleranceSuppressTrailingZeros(Variant altToleranceSuppressTrailingZeros) {
        setProperties("AltToleranceSuppressTrailingZeros", altToleranceSuppressTrailingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressZeroFeet() {
        return getProperties("AltToleranceSuppressZeroFeet");
    }

    public DimRadial setAltToleranceSuppressZeroFeet(Variant altToleranceSuppressZeroFeet) {
        setProperties("AltToleranceSuppressZeroFeet", altToleranceSuppressZeroFeet);
        return this;
    }

    public Variant getAltToleranceSuppressZeroInches() {
        return getProperties("AltToleranceSuppressZeroInches");
    }

    public DimRadial setAltToleranceSuppressZeroInches(Variant altToleranceSuppressZeroInches) {
        setProperties("AltToleranceSuppressZeroInches", altToleranceSuppressZeroInches);
        return this;
    }

    public Variant getAltUnits() {
        return getProperties("AltUnits");
    }

    public DimRadial setAltUnits(Variant altUnits) {
        setProperties("AltUnits", altUnits);
        return this;
    }

    public int getAltUnitsFormat() {
        return getProperties("AltUnitsFormat").getInt();
    }

    public DimRadial setAltUnitsFormat(int altUnitsFormat) {
        setProperties("AltUnitsFormat", altUnitsFormat);
        return this;
    }

    public int getAltUnitsPrecision() {
        return getProperties("AltUnitsPrecision").getInt();
    }

    public DimRadial setAltUnitsPrecision(int altUnitsPrecision) {
        setProperties("AltUnitsPrecision", altUnitsPrecision);
        return this;
    }

    public double getAltUnitsScale() {
        return getProperties("AltUnitsScale").getDouble();
    }

    public DimRadial setAltUnitsScale(double altUnitsScale) {
        setProperties("AltUnitsScale", altUnitsScale);
        return this;
    }

    public Variant getArrowheadBlock() {
        return getProperties("ArrowheadBlock");
    }

    public DimRadial setArrowheadBlock(Variant arrowheadBlock) {
        setProperties("ArrowheadBlock", arrowheadBlock);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public DimRadial setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public int getArrowheadType() {
        return getProperties("ArrowheadType").getInt();
    }

    public DimRadial setArrowheadType(int arrowheadType) {
        setProperties("ArrowheadType", arrowheadType);
        return this;
    }

    public Variant getCenterMarkSize() {
        return getProperties("CenterMarkSize");
    }

    public DimRadial setCenterMarkSize(Variant centerMarkSize) {
        setProperties("CenterMarkSize", centerMarkSize);
        return this;
    }

    public int getCenterType() {
        return getProperties("CenterType").getInt();
    }

    public DimRadial setCenterType(int centerType) {
        setProperties("CenterType", centerType);
        return this;
    }

    public Variant getDimConstrDesc() {
        return getProperties("DimConstrDesc");
    }

    public DimRadial setDimConstrDesc(Variant dimConstrDesc) {
        setProperties("DimConstrDesc", dimConstrDesc);
        return this;
    }

    public Variant getDimConstrExpression() {
        return getProperties("DimConstrExpression");
    }

    public DimRadial setDimConstrExpression(Variant dimConstrExpression) {
        setProperties("DimConstrExpression", dimConstrExpression);
        return this;
    }

    public Variant getDimConstrForm() {
        return getProperties("DimConstrForm");
    }

    public DimRadial setDimConstrForm(Variant dimConstrForm) {
        setProperties("DimConstrForm", dimConstrForm);
        return this;
    }

    public String getDimConstrName() {
        return getProperties("DimConstrName").getString();
    }

    public DimRadial setDimConstrName(String dimConstrName) {
        setProperties("DimConstrName", dimConstrName);
        return this;
    }

    public Variant getDimConstrReference() {
        return getProperties("DimConstrReference");
    }

    public DimRadial setDimConstrReference(Variant dimConstrReference) {
        setProperties("DimConstrReference", dimConstrReference);
        return this;
    }

    public Variant getDimConstrValue() {
        return getProperties("DimConstrValue");
    }

    public DimRadial setDimConstrValue(Variant dimConstrValue) {
        setProperties("DimConstrValue", dimConstrValue);
        return this;
    }

    public Variant getDimLineSuppress() {
        return getProperties("DimLineSuppress");
    }

    public DimRadial setDimLineSuppress(Variant dimLineSuppress) {
        setProperties("DimLineSuppress", dimLineSuppress);
        return this;
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public DimRadial setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public int getDimensionLineWeight() {
        return getProperties("DimensionLineWeight").getInt();
    }

    public DimRadial setDimensionLineWeight(int dimensionLineWeight) {
        setProperties("DimensionLineWeight", dimensionLineWeight);
        return this;
    }

    public Variant getDimensionLinetype() {
        return getProperties("DimensionLinetype");
    }

    public DimRadial setDimensionLinetype(Variant dimensionLinetype) {
        setProperties("DimensionLinetype", dimensionLinetype);
        return this;
    }

    public int getFit() {
        return getProperties("Fit").getInt();
    }

    public DimRadial setFit(int fit) {
        setProperties("Fit", fit);
        return this;
    }

    public Variant getForceLineInside() {
        return getProperties("ForceLineInside");
    }

    public DimRadial setForceLineInside(Variant forceLineInside) {
        setProperties("ForceLineInside", forceLineInside);
        return this;
    }

    public int getFractionFormat() {
        return getProperties("FractionFormat").getInt();
    }

    public DimRadial setFractionFormat(int fractionFormat) {
        setProperties("FractionFormat", fractionFormat);
        return this;
    }

    public DimRadial setLeaderLength(double leaderLength) {
        setProperties("LeaderLength", leaderLength);
        return this;
    }

    public double getLinearScaleFactor() {
        return getProperties("LinearScaleFactor").getDouble();
    }

    public DimRadial setLinearScaleFactor(double linearScaleFactor) {
        setProperties("LinearScaleFactor", linearScaleFactor);
        return this;
    }

    public Variant getMeasurement() {
        return getProperties("Measurement");
    }

    public int getPrimaryUnitsPrecision() {
        return getProperties("PrimaryUnitsPrecision").getInt();
    }

    public DimRadial setPrimaryUnitsPrecision(int primaryUnitsPrecision) {
        setProperties("PrimaryUnitsPrecision", primaryUnitsPrecision);
        return this;
    }

    public Variant getRoundDistance() {
        return getProperties("RoundDistance");
    }

    public DimRadial setRoundDistance(Variant roundDistance) {
        setProperties("RoundDistance", roundDistance);
        return this;
    }

    public Variant getSuppressZeroFeet() {
        return getProperties("SuppressZeroFeet");
    }

    public DimRadial setSuppressZeroFeet(Variant suppressZeroFeet) {
        setProperties("SuppressZeroFeet", suppressZeroFeet);
        return this;
    }

    public Variant getSuppressZeroInches() {
        return getProperties("SuppressZeroInches");
    }

    public DimRadial setSuppressZeroInches(Variant suppressZeroInches) {
        setProperties("SuppressZeroInches", suppressZeroInches);
        return this;
    }

    public Variant getTextInside() {
        return getProperties("TextInside");
    }

    public DimRadial setTextInside(Variant textInside) {
        setProperties("TextInside", textInside);
        return this;
    }

    public Variant getTextInsideAlign() {
        return getProperties("TextInsideAlign");
    }

    public DimRadial setTextInsideAlign(Variant textInsideAlign) {
        setProperties("TextInsideAlign", textInsideAlign);
        return this;
    }

    public Variant getTextOutsideAlign() {
        return getProperties("TextOutsideAlign");
    }

    public DimRadial setTextOutsideAlign(Variant textOutsideAlign) {
        setProperties("TextOutsideAlign", textOutsideAlign);
        return this;
    }

    public Variant getToleranceSuppressZeroFeet() {
        return getProperties("ToleranceSuppressZeroFeet");
    }

    public DimRadial setToleranceSuppressZeroFeet(Variant toleranceSuppressZeroFeet) {
        setProperties("ToleranceSuppressZeroFeet", toleranceSuppressZeroFeet);
        return this;
    }

    public Variant getToleranceSuppressZeroInches() {
        return getProperties("ToleranceSuppressZeroInches");
    }

    public DimRadial setToleranceSuppressZeroInches(Variant toleranceSuppressZeroInches) {
        setProperties("ToleranceSuppressZeroInches", toleranceSuppressZeroInches);
        return this;
    }

    public int getUnitsFormat() {
        return getProperties("UnitsFormat").getInt();
    }

    public DimRadial setUnitsFormat(int unitsFormat) {
        setProperties("UnitsFormat", unitsFormat);
        return this;
    }

}
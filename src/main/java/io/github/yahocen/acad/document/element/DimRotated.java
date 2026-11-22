package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * DimRotated - AutoCAD DimRotated 实体
 */
@AcadEntityName("AcDbRotatedDimension")
public class DimRotated extends Dimension {

    public DimRotated(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAltRoundDistance() {
        return getProperties("AltRoundDistance");
    }

    public DimRotated setAltRoundDistance(Variant altRoundDistance) {
        setProperties("AltRoundDistance", altRoundDistance);
        return this;
    }

    public Variant getAltSubUnitsFactor() {
        return getProperties("AltSubUnitsFactor");
    }

    public DimRotated setAltSubUnitsFactor(Variant altSubUnitsFactor) {
        setProperties("AltSubUnitsFactor", altSubUnitsFactor);
        return this;
    }

    public Variant getAltSubUnitsSuffix() {
        return getProperties("AltSubUnitsSuffix");
    }

    public DimRotated setAltSubUnitsSuffix(Variant altSubUnitsSuffix) {
        setProperties("AltSubUnitsSuffix", altSubUnitsSuffix);
        return this;
    }

    public Variant getAltSuppressLeadingZeros() {
        return getProperties("AltSuppressLeadingZeros");
    }

    public DimRotated setAltSuppressLeadingZeros(Variant altSuppressLeadingZeros) {
        setProperties("AltSuppressLeadingZeros", altSuppressLeadingZeros);
        return this;
    }

    public Variant getAltSuppressTrailingZeros() {
        return getProperties("AltSuppressTrailingZeros");
    }

    public DimRotated setAltSuppressTrailingZeros(Variant altSuppressTrailingZeros) {
        setProperties("AltSuppressTrailingZeros", altSuppressTrailingZeros);
        return this;
    }

    public Variant getAltSuppressZeroFeet() {
        return getProperties("AltSuppressZeroFeet");
    }

    public DimRotated setAltSuppressZeroFeet(Variant altSuppressZeroFeet) {
        setProperties("AltSuppressZeroFeet", altSuppressZeroFeet);
        return this;
    }

    public Variant getAltSuppressZeroInches() {
        return getProperties("AltSuppressZeroInches");
    }

    public DimRotated setAltSuppressZeroInches(Variant altSuppressZeroInches) {
        setProperties("AltSuppressZeroInches", altSuppressZeroInches);
        return this;
    }

    public Variant getAltTextPrefix() {
        return getProperties("AltTextPrefix");
    }

    public DimRotated setAltTextPrefix(Variant altTextPrefix) {
        setProperties("AltTextPrefix", altTextPrefix);
        return this;
    }

    public Variant getAltTextSuffix() {
        return getProperties("AltTextSuffix");
    }

    public DimRotated setAltTextSuffix(Variant altTextSuffix) {
        setProperties("AltTextSuffix", altTextSuffix);
        return this;
    }

    public int getAltTolerancePrecision() {
        return getProperties("AltTolerancePrecision").getInt();
    }

    public DimRotated setAltTolerancePrecision(int altTolerancePrecision) {
        setProperties("AltTolerancePrecision", altTolerancePrecision);
        return this;
    }

    public Variant getAltToleranceSuppressLeadingZeros() {
        return getProperties("AltToleranceSuppressLeadingZeros");
    }

    public DimRotated setAltToleranceSuppressLeadingZeros(Variant altToleranceSuppressLeadingZeros) {
        setProperties("AltToleranceSuppressLeadingZeros", altToleranceSuppressLeadingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressTrailingZeros() {
        return getProperties("AltToleranceSuppressTrailingZeros");
    }

    public DimRotated setAltToleranceSuppressTrailingZeros(Variant altToleranceSuppressTrailingZeros) {
        setProperties("AltToleranceSuppressTrailingZeros", altToleranceSuppressTrailingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressZeroFeet() {
        return getProperties("AltToleranceSuppressZeroFeet");
    }

    public DimRotated setAltToleranceSuppressZeroFeet(Variant altToleranceSuppressZeroFeet) {
        setProperties("AltToleranceSuppressZeroFeet", altToleranceSuppressZeroFeet);
        return this;
    }

    public Variant getAltToleranceSuppressZeroInches() {
        return getProperties("AltToleranceSuppressZeroInches");
    }

    public DimRotated setAltToleranceSuppressZeroInches(Variant altToleranceSuppressZeroInches) {
        setProperties("AltToleranceSuppressZeroInches", altToleranceSuppressZeroInches);
        return this;
    }

    public Variant getAltUnits() {
        return getProperties("AltUnits");
    }

    public DimRotated setAltUnits(Variant altUnits) {
        setProperties("AltUnits", altUnits);
        return this;
    }

    public int getAltUnitsFormat() {
        return getProperties("AltUnitsFormat").getInt();
    }

    public DimRotated setAltUnitsFormat(int altUnitsFormat) {
        setProperties("AltUnitsFormat", altUnitsFormat);
        return this;
    }

    public int getAltUnitsPrecision() {
        return getProperties("AltUnitsPrecision").getInt();
    }

    public DimRotated setAltUnitsPrecision(int altUnitsPrecision) {
        setProperties("AltUnitsPrecision", altUnitsPrecision);
        return this;
    }

    public double getAltUnitsScale() {
        return getProperties("AltUnitsScale").getDouble();
    }

    public DimRotated setAltUnitsScale(double altUnitsScale) {
        setProperties("AltUnitsScale", altUnitsScale);
        return this;
    }

    public Variant getArrowhead1Block() {
        return getProperties("Arrowhead1Block");
    }

    public DimRotated setArrowhead1Block(Variant arrowhead1Block) {
        setProperties("Arrowhead1Block", arrowhead1Block);
        return this;
    }

    public int getArrowhead1Type() {
        return getProperties("Arrowhead1Type").getInt();
    }

    public DimRotated setArrowhead1Type(int arrowhead1Type) {
        setProperties("Arrowhead1Type", arrowhead1Type);
        return this;
    }

    public Variant getArrowhead2Block() {
        return getProperties("Arrowhead2Block");
    }

    public DimRotated setArrowhead2Block(Variant arrowhead2Block) {
        setProperties("Arrowhead2Block", arrowhead2Block);
        return this;
    }

    public int getArrowhead2Type() {
        return getProperties("Arrowhead2Type").getInt();
    }

    public DimRotated setArrowhead2Type(int arrowhead2Type) {
        setProperties("Arrowhead2Type", arrowhead2Type);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public DimRotated setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public Variant getDimConstrDesc() {
        return getProperties("DimConstrDesc");
    }

    public DimRotated setDimConstrDesc(Variant dimConstrDesc) {
        setProperties("DimConstrDesc", dimConstrDesc);
        return this;
    }

    public Variant getDimConstrExpression() {
        return getProperties("DimConstrExpression");
    }

    public DimRotated setDimConstrExpression(Variant dimConstrExpression) {
        setProperties("DimConstrExpression", dimConstrExpression);
        return this;
    }

    public Variant getDimConstrForm() {
        return getProperties("DimConstrForm");
    }

    public DimRotated setDimConstrForm(Variant dimConstrForm) {
        setProperties("DimConstrForm", dimConstrForm);
        return this;
    }

    public String getDimConstrName() {
        return getProperties("DimConstrName").getString();
    }

    public DimRotated setDimConstrName(String dimConstrName) {
        setProperties("DimConstrName", dimConstrName);
        return this;
    }

    public Variant getDimConstrReference() {
        return getProperties("DimConstrReference");
    }

    public DimRotated setDimConstrReference(Variant dimConstrReference) {
        setProperties("DimConstrReference", dimConstrReference);
        return this;
    }

    public Variant getDimConstrValue() {
        return getProperties("DimConstrValue");
    }

    public DimRotated setDimConstrValue(Variant dimConstrValue) {
        setProperties("DimConstrValue", dimConstrValue);
        return this;
    }

    public Variant getDimLine1Suppress() {
        return getProperties("DimLine1Suppress");
    }

    public DimRotated setDimLine1Suppress(Variant dimLine1Suppress) {
        setProperties("DimLine1Suppress", dimLine1Suppress);
        return this;
    }

    public Variant getDimLine2Suppress() {
        return getProperties("DimLine2Suppress");
    }

    public DimRotated setDimLine2Suppress(Variant dimLine2Suppress) {
        setProperties("DimLine2Suppress", dimLine2Suppress);
        return this;
    }

    public Variant getDimLineInside() {
        return getProperties("DimLineInside");
    }

    public DimRotated setDimLineInside(Variant dimLineInside) {
        setProperties("DimLineInside", dimLineInside);
        return this;
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public DimRotated setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public Variant getDimensionLineExtend() {
        return getProperties("DimensionLineExtend");
    }

    public DimRotated setDimensionLineExtend(Variant dimensionLineExtend) {
        setProperties("DimensionLineExtend", dimensionLineExtend);
        return this;
    }

    public int getDimensionLineWeight() {
        return getProperties("DimensionLineWeight").getInt();
    }

    public DimRotated setDimensionLineWeight(int dimensionLineWeight) {
        setProperties("DimensionLineWeight", dimensionLineWeight);
        return this;
    }

    public Variant getDimensionLinetype() {
        return getProperties("DimensionLinetype");
    }

    public DimRotated setDimensionLinetype(Variant dimensionLinetype) {
        setProperties("DimensionLinetype", dimensionLinetype);
        return this;
    }

    public Variant getExtLine1Linetype() {
        return getProperties("ExtLine1Linetype");
    }

    public DimRotated setExtLine1Linetype(Variant extLine1Linetype) {
        setProperties("ExtLine1Linetype", extLine1Linetype);
        return this;
    }

    public Variant getExtLine1Suppress() {
        return getProperties("ExtLine1Suppress");
    }

    public DimRotated setExtLine1Suppress(Variant extLine1Suppress) {
        setProperties("ExtLine1Suppress", extLine1Suppress);
        return this;
    }

    public Variant getExtLine2Linetype() {
        return getProperties("ExtLine2Linetype");
    }

    public DimRotated setExtLine2Linetype(Variant extLine2Linetype) {
        setProperties("ExtLine2Linetype", extLine2Linetype);
        return this;
    }

    public Variant getExtLine2Suppress() {
        return getProperties("ExtLine2Suppress");
    }

    public DimRotated setExtLine2Suppress(Variant extLine2Suppress) {
        setProperties("ExtLine2Suppress", extLine2Suppress);
        return this;
    }

    public Variant getExtLineFixedLen() {
        return getProperties("ExtLineFixedLen");
    }

    public DimRotated setExtLineFixedLen(Variant extLineFixedLen) {
        setProperties("ExtLineFixedLen", extLineFixedLen);
        return this;
    }

    public Variant getExtLineFixedLenSuppress() {
        return getProperties("ExtLineFixedLenSuppress");
    }

    public DimRotated setExtLineFixedLenSuppress(Variant extLineFixedLenSuppress) {
        setProperties("ExtLineFixedLenSuppress", extLineFixedLenSuppress);
        return this;
    }

    public int getExtensionLineColor() {
        return getProperties("ExtensionLineColor").getInt();
    }

    public DimRotated setExtensionLineColor(int extensionLineColor) {
        setProperties("ExtensionLineColor", extensionLineColor);
        return this;
    }

    public Variant getExtensionLineExtend() {
        return getProperties("ExtensionLineExtend");
    }

    public DimRotated setExtensionLineExtend(Variant extensionLineExtend) {
        setProperties("ExtensionLineExtend", extensionLineExtend);
        return this;
    }

    public Variant getExtensionLineOffset() {
        return getProperties("ExtensionLineOffset");
    }

    public DimRotated setExtensionLineOffset(Variant extensionLineOffset) {
        setProperties("ExtensionLineOffset", extensionLineOffset);
        return this;
    }

    public int getExtensionLineWeight() {
        return getProperties("ExtensionLineWeight").getInt();
    }

    public DimRotated setExtensionLineWeight(int extensionLineWeight) {
        setProperties("ExtensionLineWeight", extensionLineWeight);
        return this;
    }

    public int getFit() {
        return getProperties("Fit").getInt();
    }

    public DimRotated setFit(int fit) {
        setProperties("Fit", fit);
        return this;
    }

    public Variant getForceLineInside() {
        return getProperties("ForceLineInside");
    }

    public DimRotated setForceLineInside(Variant forceLineInside) {
        setProperties("ForceLineInside", forceLineInside);
        return this;
    }

    public int getFractionFormat() {
        return getProperties("FractionFormat").getInt();
    }

    public DimRotated setFractionFormat(int fractionFormat) {
        setProperties("FractionFormat", fractionFormat);
        return this;
    }

    public int getHorizontalTextPosition() {
        return getProperties("HorizontalTextPosition").getInt();
    }

    public DimRotated setHorizontalTextPosition(int horizontalTextPosition) {
        setProperties("HorizontalTextPosition", horizontalTextPosition);
        return this;
    }

    public double getLinearScaleFactor() {
        return getProperties("LinearScaleFactor").getDouble();
    }

    public DimRotated setLinearScaleFactor(double linearScaleFactor) {
        setProperties("LinearScaleFactor", linearScaleFactor);
        return this;
    }

    public Variant getMeasurement() {
        return getProperties("Measurement");
    }

    public int getPrimaryUnitsPrecision() {
        return getProperties("PrimaryUnitsPrecision").getInt();
    }

    public DimRotated setPrimaryUnitsPrecision(int primaryUnitsPrecision) {
        setProperties("PrimaryUnitsPrecision", primaryUnitsPrecision);
        return this;
    }

    public Variant getRoundDistance() {
        return getProperties("RoundDistance");
    }

    public DimRotated setRoundDistance(Variant roundDistance) {
        setProperties("RoundDistance", roundDistance);
        return this;
    }

    public Variant getSubUnitsFactor() {
        return getProperties("SubUnitsFactor");
    }

    public DimRotated setSubUnitsFactor(Variant subUnitsFactor) {
        setProperties("SubUnitsFactor", subUnitsFactor);
        return this;
    }

    public Variant getSubUnitsSuffix() {
        return getProperties("SubUnitsSuffix");
    }

    public DimRotated setSubUnitsSuffix(Variant subUnitsSuffix) {
        setProperties("SubUnitsSuffix", subUnitsSuffix);
        return this;
    }

    public Variant getSuppressZeroFeet() {
        return getProperties("SuppressZeroFeet");
    }

    public DimRotated setSuppressZeroFeet(Variant suppressZeroFeet) {
        setProperties("SuppressZeroFeet", suppressZeroFeet);
        return this;
    }

    public Variant getSuppressZeroInches() {
        return getProperties("SuppressZeroInches");
    }

    public DimRotated setSuppressZeroInches(Variant suppressZeroInches) {
        setProperties("SuppressZeroInches", suppressZeroInches);
        return this;
    }

    public Variant getTextInside() {
        return getProperties("TextInside");
    }

    public DimRotated setTextInside(Variant textInside) {
        setProperties("TextInside", textInside);
        return this;
    }

    public Variant getTextInsideAlign() {
        return getProperties("TextInsideAlign");
    }

    public DimRotated setTextInsideAlign(Variant textInsideAlign) {
        setProperties("TextInsideAlign", textInsideAlign);
        return this;
    }

    public Variant getTextOutsideAlign() {
        return getProperties("TextOutsideAlign");
    }

    public DimRotated setTextOutsideAlign(Variant textOutsideAlign) {
        setProperties("TextOutsideAlign", textOutsideAlign);
        return this;
    }

    public Variant getToleranceSuppressZeroFeet() {
        return getProperties("ToleranceSuppressZeroFeet");
    }

    public DimRotated setToleranceSuppressZeroFeet(Variant toleranceSuppressZeroFeet) {
        setProperties("ToleranceSuppressZeroFeet", toleranceSuppressZeroFeet);
        return this;
    }

    public Variant getToleranceSuppressZeroInches() {
        return getProperties("ToleranceSuppressZeroInches");
    }

    public DimRotated setToleranceSuppressZeroInches(Variant toleranceSuppressZeroInches) {
        setProperties("ToleranceSuppressZeroInches", toleranceSuppressZeroInches);
        return this;
    }

    public int getUnitsFormat() {
        return getProperties("UnitsFormat").getInt();
    }

    public DimRotated setUnitsFormat(int unitsFormat) {
        setProperties("UnitsFormat", unitsFormat);
        return this;
    }

}
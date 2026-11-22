package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * DimOrdinate - AutoCAD DimOrdinate 实体
 */
@AcadEntityName("AcDbOrdinateDimension")
public class DimOrdinate extends Dimension {

    public DimOrdinate(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAltRoundDistance() {
        return getProperties("AltRoundDistance");
    }

    public DimOrdinate setAltRoundDistance(Variant altRoundDistance) {
        setProperties("AltRoundDistance", altRoundDistance);
        return this;
    }

    public Variant getAltSubUnitsFactor() {
        return getProperties("AltSubUnitsFactor");
    }

    public DimOrdinate setAltSubUnitsFactor(Variant altSubUnitsFactor) {
        setProperties("AltSubUnitsFactor", altSubUnitsFactor);
        return this;
    }

    public Variant getAltSubUnitsSuffix() {
        return getProperties("AltSubUnitsSuffix");
    }

    public DimOrdinate setAltSubUnitsSuffix(Variant altSubUnitsSuffix) {
        setProperties("AltSubUnitsSuffix", altSubUnitsSuffix);
        return this;
    }

    public Variant getAltSuppressLeadingZeros() {
        return getProperties("AltSuppressLeadingZeros");
    }

    public DimOrdinate setAltSuppressLeadingZeros(Variant altSuppressLeadingZeros) {
        setProperties("AltSuppressLeadingZeros", altSuppressLeadingZeros);
        return this;
    }

    public Variant getAltSuppressTrailingZeros() {
        return getProperties("AltSuppressTrailingZeros");
    }

    public DimOrdinate setAltSuppressTrailingZeros(Variant altSuppressTrailingZeros) {
        setProperties("AltSuppressTrailingZeros", altSuppressTrailingZeros);
        return this;
    }

    public Variant getAltSuppressZeroFeet() {
        return getProperties("AltSuppressZeroFeet");
    }

    public DimOrdinate setAltSuppressZeroFeet(Variant altSuppressZeroFeet) {
        setProperties("AltSuppressZeroFeet", altSuppressZeroFeet);
        return this;
    }

    public Variant getAltSuppressZeroInches() {
        return getProperties("AltSuppressZeroInches");
    }

    public DimOrdinate setAltSuppressZeroInches(Variant altSuppressZeroInches) {
        setProperties("AltSuppressZeroInches", altSuppressZeroInches);
        return this;
    }

    public Variant getAltTextPrefix() {
        return getProperties("AltTextPrefix");
    }

    public DimOrdinate setAltTextPrefix(Variant altTextPrefix) {
        setProperties("AltTextPrefix", altTextPrefix);
        return this;
    }

    public Variant getAltTextSuffix() {
        return getProperties("AltTextSuffix");
    }

    public DimOrdinate setAltTextSuffix(Variant altTextSuffix) {
        setProperties("AltTextSuffix", altTextSuffix);
        return this;
    }

    public int getAltTolerancePrecision() {
        return getProperties("AltTolerancePrecision").getInt();
    }

    public DimOrdinate setAltTolerancePrecision(int altTolerancePrecision) {
        setProperties("AltTolerancePrecision", altTolerancePrecision);
        return this;
    }

    public Variant getAltToleranceSuppressLeadingZeros() {
        return getProperties("AltToleranceSuppressLeadingZeros");
    }

    public DimOrdinate setAltToleranceSuppressLeadingZeros(Variant altToleranceSuppressLeadingZeros) {
        setProperties("AltToleranceSuppressLeadingZeros", altToleranceSuppressLeadingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressTrailingZeros() {
        return getProperties("AltToleranceSuppressTrailingZeros");
    }

    public DimOrdinate setAltToleranceSuppressTrailingZeros(Variant altToleranceSuppressTrailingZeros) {
        setProperties("AltToleranceSuppressTrailingZeros", altToleranceSuppressTrailingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressZeroFeet() {
        return getProperties("AltToleranceSuppressZeroFeet");
    }

    public DimOrdinate setAltToleranceSuppressZeroFeet(Variant altToleranceSuppressZeroFeet) {
        setProperties("AltToleranceSuppressZeroFeet", altToleranceSuppressZeroFeet);
        return this;
    }

    public Variant getAltToleranceSuppressZeroInches() {
        return getProperties("AltToleranceSuppressZeroInches");
    }

    public DimOrdinate setAltToleranceSuppressZeroInches(Variant altToleranceSuppressZeroInches) {
        setProperties("AltToleranceSuppressZeroInches", altToleranceSuppressZeroInches);
        return this;
    }

    public Variant getAltUnits() {
        return getProperties("AltUnits");
    }

    public DimOrdinate setAltUnits(Variant altUnits) {
        setProperties("AltUnits", altUnits);
        return this;
    }

    public int getAltUnitsFormat() {
        return getProperties("AltUnitsFormat").getInt();
    }

    public DimOrdinate setAltUnitsFormat(int altUnitsFormat) {
        setProperties("AltUnitsFormat", altUnitsFormat);
        return this;
    }

    public int getAltUnitsPrecision() {
        return getProperties("AltUnitsPrecision").getInt();
    }

    public DimOrdinate setAltUnitsPrecision(int altUnitsPrecision) {
        setProperties("AltUnitsPrecision", altUnitsPrecision);
        return this;
    }

    public double getAltUnitsScale() {
        return getProperties("AltUnitsScale").getDouble();
    }

    public DimOrdinate setAltUnitsScale(double altUnitsScale) {
        setProperties("AltUnitsScale", altUnitsScale);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public DimOrdinate setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public Variant getExtLineFixedLen() {
        return getProperties("ExtLineFixedLen");
    }

    public DimOrdinate setExtLineFixedLen(Variant extLineFixedLen) {
        setProperties("ExtLineFixedLen", extLineFixedLen);
        return this;
    }

    public Variant getExtLineFixedLenSuppress() {
        return getProperties("ExtLineFixedLenSuppress");
    }

    public DimOrdinate setExtLineFixedLenSuppress(Variant extLineFixedLenSuppress) {
        setProperties("ExtLineFixedLenSuppress", extLineFixedLenSuppress);
        return this;
    }

    public int getExtensionLineColor() {
        return getProperties("ExtensionLineColor").getInt();
    }

    public DimOrdinate setExtensionLineColor(int extensionLineColor) {
        setProperties("ExtensionLineColor", extensionLineColor);
        return this;
    }

    public Variant getExtensionLineOffset() {
        return getProperties("ExtensionLineOffset");
    }

    public DimOrdinate setExtensionLineOffset(Variant extensionLineOffset) {
        setProperties("ExtensionLineOffset", extensionLineOffset);
        return this;
    }

    public int getExtensionLineWeight() {
        return getProperties("ExtensionLineWeight").getInt();
    }

    public DimOrdinate setExtensionLineWeight(int extensionLineWeight) {
        setProperties("ExtensionLineWeight", extensionLineWeight);
        return this;
    }

    public int getFractionFormat() {
        return getProperties("FractionFormat").getInt();
    }

    public DimOrdinate setFractionFormat(int fractionFormat) {
        setProperties("FractionFormat", fractionFormat);
        return this;
    }

    public double getLinearScaleFactor() {
        return getProperties("LinearScaleFactor").getDouble();
    }

    public DimOrdinate setLinearScaleFactor(double linearScaleFactor) {
        setProperties("LinearScaleFactor", linearScaleFactor);
        return this;
    }

    public Variant getMeasurement() {
        return getProperties("Measurement");
    }

    public int getPrimaryUnitsPrecision() {
        return getProperties("PrimaryUnitsPrecision").getInt();
    }

    public DimOrdinate setPrimaryUnitsPrecision(int primaryUnitsPrecision) {
        setProperties("PrimaryUnitsPrecision", primaryUnitsPrecision);
        return this;
    }

    public Variant getRoundDistance() {
        return getProperties("RoundDistance");
    }

    public DimOrdinate setRoundDistance(Variant roundDistance) {
        setProperties("RoundDistance", roundDistance);
        return this;
    }

    public Variant getSubUnitsFactor() {
        return getProperties("SubUnitsFactor");
    }

    public DimOrdinate setSubUnitsFactor(Variant subUnitsFactor) {
        setProperties("SubUnitsFactor", subUnitsFactor);
        return this;
    }

    public Variant getSubUnitsSuffix() {
        return getProperties("SubUnitsSuffix");
    }

    public DimOrdinate setSubUnitsSuffix(Variant subUnitsSuffix) {
        setProperties("SubUnitsSuffix", subUnitsSuffix);
        return this;
    }

    public Variant getSuppressZeroFeet() {
        return getProperties("SuppressZeroFeet");
    }

    public DimOrdinate setSuppressZeroFeet(Variant suppressZeroFeet) {
        setProperties("SuppressZeroFeet", suppressZeroFeet);
        return this;
    }

    public Variant getSuppressZeroInches() {
        return getProperties("SuppressZeroInches");
    }

    public DimOrdinate setSuppressZeroInches(Variant suppressZeroInches) {
        setProperties("SuppressZeroInches", suppressZeroInches);
        return this;
    }

    public Variant getToleranceSuppressZeroFeet() {
        return getProperties("ToleranceSuppressZeroFeet");
    }

    public DimOrdinate setToleranceSuppressZeroFeet(Variant toleranceSuppressZeroFeet) {
        setProperties("ToleranceSuppressZeroFeet", toleranceSuppressZeroFeet);
        return this;
    }

    public Variant getToleranceSuppressZeroInches() {
        return getProperties("ToleranceSuppressZeroInches");
    }

    public DimOrdinate setToleranceSuppressZeroInches(Variant toleranceSuppressZeroInches) {
        setProperties("ToleranceSuppressZeroInches", toleranceSuppressZeroInches);
        return this;
    }

    public int getUnitsFormat() {
        return getProperties("UnitsFormat").getInt();
    }

    public DimOrdinate setUnitsFormat(int unitsFormat) {
        setProperties("UnitsFormat", unitsFormat);
        return this;
    }

}
// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * DimDiametric - AutoCAD DimDiametric 实体
 */
@AcadEntityName("AcDbDiametricDimension")
public class DimDiametric extends Dimension {

    public DimDiametric(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAltRoundDistance() {
        return getProperties("AltRoundDistance");
    }

    public DimDiametric setAltRoundDistance(Variant altRoundDistance) {
        setProperties("AltRoundDistance", altRoundDistance);
        return this;
    }

    public Variant getAltSuppressLeadingZeros() {
        return getProperties("AltSuppressLeadingZeros");
    }

    public DimDiametric setAltSuppressLeadingZeros(Variant altSuppressLeadingZeros) {
        setProperties("AltSuppressLeadingZeros", altSuppressLeadingZeros);
        return this;
    }

    public Variant getAltSuppressTrailingZeros() {
        return getProperties("AltSuppressTrailingZeros");
    }

    public DimDiametric setAltSuppressTrailingZeros(Variant altSuppressTrailingZeros) {
        setProperties("AltSuppressTrailingZeros", altSuppressTrailingZeros);
        return this;
    }

    public Variant getAltSuppressZeroFeet() {
        return getProperties("AltSuppressZeroFeet");
    }

    public DimDiametric setAltSuppressZeroFeet(Variant altSuppressZeroFeet) {
        setProperties("AltSuppressZeroFeet", altSuppressZeroFeet);
        return this;
    }

    public Variant getAltSuppressZeroInches() {
        return getProperties("AltSuppressZeroInches");
    }

    public DimDiametric setAltSuppressZeroInches(Variant altSuppressZeroInches) {
        setProperties("AltSuppressZeroInches", altSuppressZeroInches);
        return this;
    }

    public Variant getAltTextPrefix() {
        return getProperties("AltTextPrefix");
    }

    public DimDiametric setAltTextPrefix(Variant altTextPrefix) {
        setProperties("AltTextPrefix", altTextPrefix);
        return this;
    }

    public Variant getAltTextSuffix() {
        return getProperties("AltTextSuffix");
    }

    public DimDiametric setAltTextSuffix(Variant altTextSuffix) {
        setProperties("AltTextSuffix", altTextSuffix);
        return this;
    }

    public int getAltTolerancePrecision() {
        return getProperties("AltTolerancePrecision").getInt();
    }

    public DimDiametric setAltTolerancePrecision(int altTolerancePrecision) {
        setProperties("AltTolerancePrecision", altTolerancePrecision);
        return this;
    }

    public Variant getAltToleranceSuppressLeadingZeros() {
        return getProperties("AltToleranceSuppressLeadingZeros");
    }

    public DimDiametric setAltToleranceSuppressLeadingZeros(Variant altToleranceSuppressLeadingZeros) {
        setProperties("AltToleranceSuppressLeadingZeros", altToleranceSuppressLeadingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressTrailingZeros() {
        return getProperties("AltToleranceSuppressTrailingZeros");
    }

    public DimDiametric setAltToleranceSuppressTrailingZeros(Variant altToleranceSuppressTrailingZeros) {
        setProperties("AltToleranceSuppressTrailingZeros", altToleranceSuppressTrailingZeros);
        return this;
    }

    public Variant getAltToleranceSuppressZeroFeet() {
        return getProperties("AltToleranceSuppressZeroFeet");
    }

    public DimDiametric setAltToleranceSuppressZeroFeet(Variant altToleranceSuppressZeroFeet) {
        setProperties("AltToleranceSuppressZeroFeet", altToleranceSuppressZeroFeet);
        return this;
    }

    public Variant getAltToleranceSuppressZeroInches() {
        return getProperties("AltToleranceSuppressZeroInches");
    }

    public DimDiametric setAltToleranceSuppressZeroInches(Variant altToleranceSuppressZeroInches) {
        setProperties("AltToleranceSuppressZeroInches", altToleranceSuppressZeroInches);
        return this;
    }

    public Variant getAltUnits() {
        return getProperties("AltUnits");
    }

    public DimDiametric setAltUnits(Variant altUnits) {
        setProperties("AltUnits", altUnits);
        return this;
    }

    public int getAltUnitsFormat() {
        return getProperties("AltUnitsFormat").getInt();
    }

    public DimDiametric setAltUnitsFormat(int altUnitsFormat) {
        setProperties("AltUnitsFormat", altUnitsFormat);
        return this;
    }

    public int getAltUnitsPrecision() {
        return getProperties("AltUnitsPrecision").getInt();
    }

    public DimDiametric setAltUnitsPrecision(int altUnitsPrecision) {
        setProperties("AltUnitsPrecision", altUnitsPrecision);
        return this;
    }

    public double getAltUnitsScale() {
        return getProperties("AltUnitsScale").getDouble();
    }

    public DimDiametric setAltUnitsScale(double altUnitsScale) {
        setProperties("AltUnitsScale", altUnitsScale);
        return this;
    }

    public Variant getArrowhead1Block() {
        return getProperties("Arrowhead1Block");
    }

    public DimDiametric setArrowhead1Block(Variant arrowhead1Block) {
        setProperties("Arrowhead1Block", arrowhead1Block);
        return this;
    }

    public int getArrowhead1Type() {
        return getProperties("Arrowhead1Type").getInt();
    }

    public DimDiametric setArrowhead1Type(int arrowhead1Type) {
        setProperties("Arrowhead1Type", arrowhead1Type);
        return this;
    }

    public Variant getArrowhead2Block() {
        return getProperties("Arrowhead2Block");
    }

    public DimDiametric setArrowhead2Block(Variant arrowhead2Block) {
        setProperties("Arrowhead2Block", arrowhead2Block);
        return this;
    }

    public int getArrowhead2Type() {
        return getProperties("Arrowhead2Type").getInt();
    }

    public DimDiametric setArrowhead2Type(int arrowhead2Type) {
        setProperties("Arrowhead2Type", arrowhead2Type);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public DimDiametric setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public Variant getCenterMarkSize() {
        return getProperties("CenterMarkSize");
    }

    public DimDiametric setCenterMarkSize(Variant centerMarkSize) {
        setProperties("CenterMarkSize", centerMarkSize);
        return this;
    }

    public int getCenterType() {
        return getProperties("CenterType").getInt();
    }

    public DimDiametric setCenterType(int centerType) {
        setProperties("CenterType", centerType);
        return this;
    }

    public Variant getDimConstrDesc() {
        return getProperties("DimConstrDesc");
    }

    public DimDiametric setDimConstrDesc(Variant dimConstrDesc) {
        setProperties("DimConstrDesc", dimConstrDesc);
        return this;
    }

    public Variant getDimConstrExpression() {
        return getProperties("DimConstrExpression");
    }

    public DimDiametric setDimConstrExpression(Variant dimConstrExpression) {
        setProperties("DimConstrExpression", dimConstrExpression);
        return this;
    }

    public Variant getDimConstrForm() {
        return getProperties("DimConstrForm");
    }

    public DimDiametric setDimConstrForm(Variant dimConstrForm) {
        setProperties("DimConstrForm", dimConstrForm);
        return this;
    }

    public String getDimConstrName() {
        return getProperties("DimConstrName").getString();
    }

    public DimDiametric setDimConstrName(String dimConstrName) {
        setProperties("DimConstrName", dimConstrName);
        return this;
    }

    public Variant getDimConstrReference() {
        return getProperties("DimConstrReference");
    }

    public DimDiametric setDimConstrReference(Variant dimConstrReference) {
        setProperties("DimConstrReference", dimConstrReference);
        return this;
    }

    public Variant getDimConstrValue() {
        return getProperties("DimConstrValue");
    }

    public DimDiametric setDimConstrValue(Variant dimConstrValue) {
        setProperties("DimConstrValue", dimConstrValue);
        return this;
    }

    public Variant getDimLine1Suppress() {
        return getProperties("DimLine1Suppress");
    }

    public DimDiametric setDimLine1Suppress(Variant dimLine1Suppress) {
        setProperties("DimLine1Suppress", dimLine1Suppress);
        return this;
    }

    public Variant getDimLine2Suppress() {
        return getProperties("DimLine2Suppress");
    }

    public DimDiametric setDimLine2Suppress(Variant dimLine2Suppress) {
        setProperties("DimLine2Suppress", dimLine2Suppress);
        return this;
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public DimDiametric setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public int getDimensionLineWeight() {
        return getProperties("DimensionLineWeight").getInt();
    }

    public DimDiametric setDimensionLineWeight(int dimensionLineWeight) {
        setProperties("DimensionLineWeight", dimensionLineWeight);
        return this;
    }

    public Variant getDimensionLinetype() {
        return getProperties("DimensionLinetype");
    }

    public DimDiametric setDimensionLinetype(Variant dimensionLinetype) {
        setProperties("DimensionLinetype", dimensionLinetype);
        return this;
    }

    public int getFit() {
        return getProperties("Fit").getInt();
    }

    public DimDiametric setFit(int fit) {
        setProperties("Fit", fit);
        return this;
    }

    public Variant getForceLineInside() {
        return getProperties("ForceLineInside");
    }

    public DimDiametric setForceLineInside(Variant forceLineInside) {
        setProperties("ForceLineInside", forceLineInside);
        return this;
    }

    public int getFractionFormat() {
        return getProperties("FractionFormat").getInt();
    }

    public DimDiametric setFractionFormat(int fractionFormat) {
        setProperties("FractionFormat", fractionFormat);
        return this;
    }

    public DimDiametric setLeaderLength(double leaderLength) {
        setProperties("LeaderLength", leaderLength);
        return this;
    }

    public double getLinearScaleFactor() {
        return getProperties("LinearScaleFactor").getDouble();
    }

    public DimDiametric setLinearScaleFactor(double linearScaleFactor) {
        setProperties("LinearScaleFactor", linearScaleFactor);
        return this;
    }

    public Variant getMeasurement() {
        return getProperties("Measurement");
    }

    public int getPrimaryUnitsPrecision() {
        return getProperties("PrimaryUnitsPrecision").getInt();
    }

    public DimDiametric setPrimaryUnitsPrecision(int primaryUnitsPrecision) {
        setProperties("PrimaryUnitsPrecision", primaryUnitsPrecision);
        return this;
    }

    public Variant getRoundDistance() {
        return getProperties("RoundDistance");
    }

    public DimDiametric setRoundDistance(Variant roundDistance) {
        setProperties("RoundDistance", roundDistance);
        return this;
    }

    public Variant getSuppressZeroFeet() {
        return getProperties("SuppressZeroFeet");
    }

    public DimDiametric setSuppressZeroFeet(Variant suppressZeroFeet) {
        setProperties("SuppressZeroFeet", suppressZeroFeet);
        return this;
    }

    public Variant getSuppressZeroInches() {
        return getProperties("SuppressZeroInches");
    }

    public DimDiametric setSuppressZeroInches(Variant suppressZeroInches) {
        setProperties("SuppressZeroInches", suppressZeroInches);
        return this;
    }

    public Variant getTextInside() {
        return getProperties("TextInside");
    }

    public DimDiametric setTextInside(Variant textInside) {
        setProperties("TextInside", textInside);
        return this;
    }

    public Variant getTextInsideAlign() {
        return getProperties("TextInsideAlign");
    }

    public DimDiametric setTextInsideAlign(Variant textInsideAlign) {
        setProperties("TextInsideAlign", textInsideAlign);
        return this;
    }

    public Variant getTextOutsideAlign() {
        return getProperties("TextOutsideAlign");
    }

    public DimDiametric setTextOutsideAlign(Variant textOutsideAlign) {
        setProperties("TextOutsideAlign", textOutsideAlign);
        return this;
    }

    public Variant getToleranceSuppressZeroFeet() {
        return getProperties("ToleranceSuppressZeroFeet");
    }

    public DimDiametric setToleranceSuppressZeroFeet(Variant toleranceSuppressZeroFeet) {
        setProperties("ToleranceSuppressZeroFeet", toleranceSuppressZeroFeet);
        return this;
    }

    public Variant getToleranceSuppressZeroInches() {
        return getProperties("ToleranceSuppressZeroInches");
    }

    public DimDiametric setToleranceSuppressZeroInches(Variant toleranceSuppressZeroInches) {
        setProperties("ToleranceSuppressZeroInches", toleranceSuppressZeroInches);
        return this;
    }

    public int getUnitsFormat() {
        return getProperties("UnitsFormat").getInt();
    }

    public DimDiametric setUnitsFormat(int unitsFormat) {
        setProperties("UnitsFormat", unitsFormat);
        return this;
    }

}
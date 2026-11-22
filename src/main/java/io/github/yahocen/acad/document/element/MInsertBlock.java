// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * MInsertBlock - AutoCAD MInsertBlock 实体
 */
@AcadEntityName("AcDbMInsertBlock")
public class MInsertBlock extends AcadEntity {

    public MInsertBlock(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getColumnSpacing() {
        return getProperties("ColumnSpacing");
    }

    public MInsertBlock setColumnSpacing(Variant columnSpacing) {
        setProperties("ColumnSpacing", columnSpacing);
        return this;
    }

    public Variant getColumns() {
        return getProperties("Columns");
    }

    public MInsertBlock setColumns(Variant columns) {
        setProperties("Columns", columns);
        return this;
    }

    public String getEffectiveName() {
        return getProperties("EffectiveName").getString();
    }

    public Variant getHasAttributes() {
        return getProperties("HasAttributes");
    }

    public Variant getInsUnits() {
        return getProperties("InsUnits");
    }

    public Variant getInsUnitsFactor() {
        return getProperties("InsUnitsFactor");
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public MInsertBlock setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public Variant getIsDynamicBlock() {
        return getProperties("IsDynamicBlock");
    }

    public String getName() {
        return getProperties("Name").getString();
    }

    public MInsertBlock setName(String name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public MInsertBlock setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public MInsertBlock setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public Variant getRowSpacing() {
        return getProperties("RowSpacing");
    }

    public MInsertBlock setRowSpacing(Variant rowSpacing) {
        setProperties("RowSpacing", rowSpacing);
        return this;
    }

    public Variant getRows() {
        return getProperties("Rows");
    }

    public MInsertBlock setRows(Variant rows) {
        setProperties("Rows", rows);
        return this;
    }

    public double getXEffectiveScaleFactor() {
        return getProperties("XEffectiveScaleFactor").getDouble();
    }

    public MInsertBlock setXEffectiveScaleFactor(double xEffectiveScaleFactor) {
        setProperties("XEffectiveScaleFactor", xEffectiveScaleFactor);
        return this;
    }

    public double getXScaleFactor() {
        return getProperties("XScaleFactor").getDouble();
    }

    public MInsertBlock setXScaleFactor(double xScaleFactor) {
        setProperties("XScaleFactor", xScaleFactor);
        return this;
    }

    public double getYEffectiveScaleFactor() {
        return getProperties("YEffectiveScaleFactor").getDouble();
    }

    public MInsertBlock setYEffectiveScaleFactor(double yEffectiveScaleFactor) {
        setProperties("YEffectiveScaleFactor", yEffectiveScaleFactor);
        return this;
    }

    public double getYScaleFactor() {
        return getProperties("YScaleFactor").getDouble();
    }

    public MInsertBlock setYScaleFactor(double yScaleFactor) {
        setProperties("YScaleFactor", yScaleFactor);
        return this;
    }

    public double getZEffectiveScaleFactor() {
        return getProperties("ZEffectiveScaleFactor").getDouble();
    }

    public MInsertBlock setZEffectiveScaleFactor(double zEffectiveScaleFactor) {
        setProperties("ZEffectiveScaleFactor", zEffectiveScaleFactor);
        return this;
    }

    public double getZScaleFactor() {
        return getProperties("ZScaleFactor").getDouble();
    }

    public MInsertBlock setZScaleFactor(double zScaleFactor) {
        setProperties("ZScaleFactor", zScaleFactor);
        return this;
    }

    public Variant explode() {
        return call("Explode");
    }

    public Variant getAttributes() {
        return call("GetAttributes");
    }

    public Variant getConstantAttributes() {
        return call("GetConstantAttributes");
    }

    public Variant getDynamicBlockProperties() {
        return call("GetDynamicBlockProperties");
    }

    public Variant resetBlock() {
        return call("ResetBlock");
    }

    public Variant convertToAnonymousBlock() {
        return call("ConvertToAnonymousBlock");
    }

    public Variant convertToStaticBlock(Variant newBlockName) {
        return call("ConvertToStaticBlock", newBlockName);
    }

}
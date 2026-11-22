// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.constant.AlignmentEnum;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Text - AutoCAD Text 实体
 */
@AcadEntityName("AcDbText")
public class Text extends AcadEntity {

    public Text(Dispatch dispatch) {
        super(dispatch);
    }

    public int getAlignment() {
        return getProperties("Alignment").getInt();
    }

    public Text setAlignment(int alignment) {
        setProperties("Alignment", alignment);
        return this;
    }

    public Text setAlignment(AlignmentEnum alignment) {
        setAlignment(alignment.getAlignment());
        return this;
    }

    public Variant getBackward() {
        return getProperties("Backward");
    }

    public Text setBackward(Variant backward) {
        setProperties("Backward", backward);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Text setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public int getHorizontalAlignment() {
        return getProperties("HorizontalAlignment").getInt();
    }

    public Text setHorizontalAlignment(int horizontalAlignment) {
        setProperties("HorizontalAlignment", horizontalAlignment);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public Text setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Text setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getObliqueAngle() {
        return getProperties("ObliqueAngle").getDouble();
    }

    public Text setObliqueAngle(double obliqueAngle) {
        setProperties("ObliqueAngle", obliqueAngle);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public Text setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public Text setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public Text setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public Point getTextAlignmentPoint() {
        return Point.of(getProperties("TextAlignmentPoint").toSafeArray());
    }

    public Text setTextAlignmentPoint(Point textAlignmentPoint) {
        setProperties("TextAlignmentPoint", textAlignmentPoint.toVariant());
        return this;
    }

    public Variant getTextGenerationFlag() {
        return getProperties("TextGenerationFlag");
    }

    public Text setTextGenerationFlag(Variant textGenerationFlag) {
        setProperties("TextGenerationFlag", textGenerationFlag);
        return this;
    }

    public String getTextString() {
        return getProperties("TextString").getString();
    }

    public Text setTextString(String textString) {
        setProperties("TextString", textString);
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public Text setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

    public Variant getUpsideDown() {
        return getProperties("UpsideDown");
    }

    public Text setUpsideDown(Variant upsideDown) {
        setProperties("UpsideDown", upsideDown);
        return this;
    }

    public int getVerticalAlignment() {
        return getProperties("VerticalAlignment").getInt();
    }

    public Text setVerticalAlignment(int verticalAlignment) {
        setProperties("VerticalAlignment", verticalAlignment);
        return this;
    }

    public Variant fieldCode() {
        return call("FieldCode");
    }

}
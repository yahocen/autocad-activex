// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * MText - AutoCAD MText 实体
 */
@AcadEntityName("AcDbMText")
public class MText extends AcadEntity {

    public MText(Dispatch dispatch) {
        super(dispatch);
    }

    /**
     * 多行文字的附着（对正）方式，AcAttachmentPoint 枚举，不是坐标点：
     * 1 左上、2 中上、3 右上、4 左中、5 正中、6 右中、7 左下、8 中下、9 右下。
     */
    @Operation(summary = "获取附着方式", description = "AcAttachmentPoint 枚举：1 左上、2 中上、3 右上、4 左中、5 正中、6 右中、7 左下、8 中下、9 右下")
    public int getAttachmentPoint() {
        return getProperties("AttachmentPoint").getInt();
    }

    @Operation(summary = "设置附着方式", description = "AcAttachmentPoint 枚举：1 左上、2 中上、3 右上、4 左中、5 正中、6 右中、7 左下、8 中下、9 右下")
    public MText setAttachmentPoint(@Parameter(description = "附着方式枚举值 1~9") int attachmentPoint) {
        setProperties("AttachmentPoint", attachmentPoint);
        return this;
    }

    public Variant getBackgroundFill() {
        return getProperties("BackgroundFill");
    }

    public MText setBackgroundFill(Variant backgroundFill) {
        setProperties("BackgroundFill", backgroundFill);
        return this;
    }

    public int getDrawingDirection() {
        return getProperties("DrawingDirection").getInt();
    }

    public MText setDrawingDirection(int drawingDirection) {
        setProperties("DrawingDirection", drawingDirection);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public MText setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public MText setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public Variant getLineSpacingDistance() {
        return getProperties("LineSpacingDistance");
    }

    public MText setLineSpacingDistance(Variant lineSpacingDistance) {
        setProperties("LineSpacingDistance", lineSpacingDistance);
        return this;
    }

    public Variant getLineSpacingFactor() {
        return getProperties("LineSpacingFactor");
    }

    public MText setLineSpacingFactor(Variant lineSpacingFactor) {
        setProperties("LineSpacingFactor", lineSpacingFactor);
        return this;
    }

    public int getLineSpacingStyle() {
        return getProperties("LineSpacingStyle").getInt();
    }

    public MText setLineSpacingStyle(int lineSpacingStyle) {
        setProperties("LineSpacingStyle", lineSpacingStyle);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public MText setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public MText setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public MText setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public String getTextString() {
        return getProperties("TextString").getString();
    }

    public MText setTextString(String textString) {
        setProperties("TextString", textString);
        return this;
    }

    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    public MText setWidth(double width) {
        setProperties("Width", width);
        return this;
    }

    public Variant fieldCode() {
        return call("FieldCode");
    }

}
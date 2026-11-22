// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.base.Point;

/**
 * 圆柱体图形类
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-BB6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E9E">Cylinder Object (ActiveX)</a>
 * @author Yahocen
 */
@Schema(
    title = "Cylinder AutoCAD 圆柱体",
    description = "AutoCAD 中圆柱体对象"
)
public class Cylinder extends AcadEntity {

    public Cylinder(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 获取圆柱体的中心点（底面圆心）
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-5EFC87E1-5A1E-4B2C-8552-67A6F367617A">Center Property (ActiveX)</a>
     * @return 中心点坐标
     */
    @Operation(summary = "获取圆柱体中心点", description = "获取圆柱体的中心点（底面圆心）坐标")
    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    /**
     * 设置圆柱体的中心点
     * @param center 中心点坐标
     */
    @Operation(summary = "设置圆柱体中心点", description = "设置圆柱体的中心点坐标")
    public Cylinder setCenter(@Parameter(description = "中心点坐标") Point center) {
        setProperties("Center", center);
        return this;
    }

    /**
     * 获取圆柱体的底面半径
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-28F16E3B-56A0-44AA-9449-1DB66BBF08DA">Radius Property (ActiveX)</a>
     * @return 底面半径
     */
    @Operation(summary = "获取圆柱体底面半径", description = "获取圆柱体的底面半径")
    public double getRadius() {
        return getProperties("Radius").getDouble();
    }

    /**
     * 获取圆柱体的高度
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-9B6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E9C">Height Property (ActiveX)</a>
     * @return 高度
     */
    @Operation(summary = "获取圆柱体高度", description = "获取圆柱体的高度")
    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    /**
     * 获取圆柱体的体积
     * @return 体积
     */
    @Operation(summary = "获取圆柱体体积", description = "获取圆柱体的体积")
    public double getVolume() {
        return Math.PI * getRadius() * getRadius() * getHeight();
    }

    /**
     * 获取圆柱体的底面积
     * @return 底面积
     */
    @Operation(summary = "获取圆柱体底面积", description = "获取圆柱体的底面积")
    public double getBaseArea() {
        return Math.PI * getRadius() * getRadius();
    }

    /**
     * 获取圆柱体的侧面积
     * @return 侧面积
     */
    @Operation(summary = "获取圆柱体侧面积", description = "获取圆柱体的侧面积")
    public double getLateralArea() {
        return 2 * Math.PI * getRadius() * getHeight();
    }

    /**
     * 获取圆柱体的表面积（两个底面积 + 侧面积）
     * @return 表面积
     */
    @Operation(summary = "获取圆柱体表面积", description = "获取圆柱体的表面积（两个底面积 + 侧面积）")
    public double getSurfaceArea() {
        return 2 * getBaseArea() + getLateralArea();
    }

    /**
     * 获取圆柱体的底面周长
     * @return 底面周长
     */
    @Operation(summary = "获取圆柱体底面周长", description = "获取圆柱体的底面周长")
    public double getBaseCircumference() {
        return 2 * Math.PI * getRadius();
    }

    /**
     * 获取圆柱体的顶面圆心坐标
     * @return 顶面圆心坐标
     */
    @Operation(summary = "获取圆柱体顶面圆心", description = "获取圆柱体的顶面圆心坐标")
    public Point getTopCenter() {
        Point center = getCenter();
        return Point.of(center.getX(), center.getY(), center.getZ() + getHeight());
    }

    /**
     * 获取圆柱体的直径
     * @return 直径
     */
    @Operation(summary = "获取圆柱体直径", description = "获取圆柱体的直径")
    public double getDiameter() {
        return 2 * getRadius();
    }

}

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
 * 圆锥体图形类
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-AB6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E9D">Cone Object (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Cone AutoCAD 圆锥体",
    description = "AutoCAD 中圆锥体对象"
)
public class Cone extends AcadEntity {

    public Cone(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 获取圆锥体的中心点（底面圆心）
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-5EFC87E1-5A1E-4B2C-8552-67A6F367617A">Center Property (ActiveX)</a>
     * @return 中心点坐标
     */
    @Operation(summary = "获取圆锥体中心点", description = "获取圆锥体的中心点（底面圆心）坐标")
    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    /**
     * 设置圆锥体的中心点
     * @param center 中心点坐标
     */
    @Operation(summary = "设置圆锥体中心点", description = "设置圆锥体的中心点坐标")
    public Cone setCenter(@Parameter(description = "中心点坐标") Point center) {
        setProperties("Center", center);
        return this;
    }

    /**
     * 获取圆锥体的底面半径
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-28F16E3B-56A0-44AA-9449-1DB66BBF08DA">Radius Property (ActiveX)</a>
     * @return 底面半径
     */
    @Operation(summary = "获取圆锥体底面半径", description = "获取圆锥体的底面半径")
    public double getRadius() {
        return getProperties("Radius").getDouble();
    }

    /**
     * 获取圆锥体的高度
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-9B6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E9C">Height Property (ActiveX)</a>
     * @return 高度
     */
    @Operation(summary = "获取圆锥体高度", description = "获取圆锥体的高度")
    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    /**
     * 获取圆锥体的体积
     * @return 体积
     */
    @Operation(summary = "获取圆锥体体积", description = "获取圆锥体的体积")
    public double getVolume() {
        return (1.0 / 3.0) * Math.PI * getRadius() * getRadius() * getHeight();
    }

    /**
     * 获取圆锥体的底面积
     * @return 底面积
     */
    @Operation(summary = "获取圆锥体底面积", description = "获取圆锥体的底面积")
    public double getBaseArea() {
        return Math.PI * getRadius() * getRadius();
    }

    /**
     * 获取圆锥体的侧面积
     * @return 侧面积
     */
    @Operation(summary = "获取圆锥体侧面积", description = "获取圆锥体的侧面积")
    public double getLateralArea() {
        double r = getRadius();
        double h = getHeight();
        double slantHeight = Math.sqrt(r * r + h * h);
        return Math.PI * r * slantHeight;
    }

    /**
     * 获取圆锥体的表面积（底面积 + 侧面积）
     * @return 表面积
     */
    @Operation(summary = "获取圆锥体表面积", description = "获取圆锥体的表面积（底面积 + 侧面积）")
    public double getSurfaceArea() {
        return getBaseArea() + getLateralArea();
    }

    /**
     * 获取圆锥体的母线长度（斜高）
     * @return 母线长度
     */
    @Operation(summary = "获取圆锥体母线长度", description = "获取圆锥体的母线长度（斜高）")
    public double getSlantHeight() {
        double r = getRadius();
        double h = getHeight();
        return Math.sqrt(r * r + h * h);
    }

    /**
     * 获取圆锥体的顶点坐标
     * @return 顶点坐标
     */
    @Operation(summary = "获取圆锥体顶点坐标", description = "获取圆锥体的顶点坐标")
    public Point getApex() {
        Point center = getCenter();
        return Point.of(center.getX(), center.getY(), center.getZ() + getHeight());
    }

}

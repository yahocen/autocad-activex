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
 * 长方体（矩形盒）图形类
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-6B6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E99">Box Object (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Box AutoCAD 长方体",
    description = "AutoCAD 中长方体对象"
)
public class Box extends AcadEntity {

    public Box(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 获取长方体的中心点
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-5EFC87E1-5A1E-4B2C-8552-67A6F367617A">Center Property (ActiveX)</a>
     * @return 中心点坐标
     */
    @Operation(summary = "获取长方体的中心点", description = "获取长方体的中心点坐标")
    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    /**
     * 设置长方体的中心点
     * @param center 中心点坐标
     */
    @Operation(summary = "设置长方体的中心点", description = "设置长方体的中心点坐标")
    public Box setCenter(@Parameter(description = "中心点坐标") Point center) {
        setProperties("Center", center);
        return this;
    }

    /**
     * 获取长方体的长度（X 方向）
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-7B6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E9A">Length Property (ActiveX)</a>
     * @return 长度
     */
    @Operation(summary = "获取长方体的长度", description = "获取长方体的长度（X 方向）")
    public double getLength() {
        return getProperties("Length").getDouble();
    }

    /**
     * 获取长方体的宽度（Y 方向）
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-8B6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E9B">Width Property (ActiveX)</a>
     * @return 宽度
     */
    @Operation(summary = "获取长方体的宽度", description = "获取长方体的宽度（Y 方向）")
    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    /**
     * 获取长方体的高度（Z 方向）
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-9B6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E9C">Height Property (ActiveX)</a>
     * @return 高度
     */
    @Operation(summary = "获取长方体的高度", description = "获取长方体的高度（Z 方向）")
    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    /**
     * 获取长方体的体积
     * @return 体积
     */
    @Operation(summary = "获取长方体的体积", description = "获取长方体的体积")
    public double getVolume() {
        return getLength() * getWidth() * getHeight();
    }

    /**
     * 获取长方体的表面积
     * @return 表面积
     */
    @Operation(summary = "获取长方体的表面积", description = "获取长方体的表面积")
    public double getSurfaceArea() {
        double l = getLength();
        double w = getWidth();
        double h = getHeight();
        return 2 * (l * w + l * h + w * h);
    }

    /**
     * 获取长方体底面的对角线长度
     * @return 底面对角线长度
     */
    @Operation(summary = "获取长方体底面对角线", description = "获取长方体底面的对角线长度")
    public double getBaseDiagonal() {
        return Math.sqrt(getLength() * getLength() + getWidth() * getWidth());
    }

    /**
     * 获取长方体的空间对角线长度
     * @return 空间对角线长度
     */
    @Operation(summary = "获取长方体空间对角线", description = "获取长方体的空间对角线长度")
    public double getSpaceDiagonal() {
        return Math.sqrt(
            getLength() * getLength() +
            getWidth() * getWidth() +
            getHeight() * getHeight()
        );
    }

}

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
 * 圆环体图形类
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-DB6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7EA0">Torus Object (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Torus AutoCAD 圆环体",
    description = "AutoCAD 中圆环体对象"
)
public class Torus extends AcadEntity {

    public Torus(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 获取圆环体的中心点
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-5EFC87E1-5A1E-4B2C-8552-67A6F367617A">Center Property (ActiveX)</a>
     * @return 中心点坐标
     */
    @Operation(summary = "获取圆环体中心点", description = "获取圆环体的中心点坐标")
    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    /**
     * 设置圆环体的中心点
     * @param center 中心点坐标
     */
    @Operation(summary = "设置圆环体中心点", description = "设置圆环体的中心点坐标")
    public Torus setCenter(@Parameter(description = "中心点坐标") Point center) {
        setProperties("Center", center);
        return this;
    }

    /**
     * 获取圆环体的主半径（圆环中心到管中心的距离）
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-EB6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7EA1">MajorRadius Property (ActiveX)</a>
     * @return 主半径
     */
    @Operation(summary = "获取主半径", description = "获取圆环体的主半径（圆环中心到管中心的距离）")
    public double getMajorRadius() {
        return getProperties("MajorRadius").getDouble();
    }

    /**
     * 获取圆环体的次半径（管半径）
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-FB6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7EA2">MinorRadius Property (ActiveX)</a>
     * @return 次半径（管半径）
     */
    @Operation(summary = "获取次半径", description = "获取圆环体的次半径（管半径）")
    public double getMinorRadius() {
        return getProperties("MinorRadius").getDouble();
    }

    /**
     * 获取圆环体的体积
     * @return 体积
     */
    @Operation(summary = "获取圆环体体积", description = "获取圆环体的体积")
    public double getVolume() {
        double R = getMajorRadius();
        double r = getMinorRadius();
        return 2 * Math.PI * Math.PI * R * r * r;
    }

    /**
     * 获取圆环体的表面积
     * @return 表面积
     */
    @Operation(summary = "获取圆环体表面积", description = "获取圆环体的表面积")
    public double getSurfaceArea() {
        double R = getMajorRadius();
        double r = getMinorRadius();
        return 4 * Math.PI * Math.PI * R * r;
    }

    /**
     * 获取圆环体的外径
     * @return 外径
     */
    @Operation(summary = "获取外径", description = "获取圆环体的外径")
    public double getOuterDiameter() {
        return 2 * (getMajorRadius() + getMinorRadius());
    }

    /**
     * 获取圆环体的内径
     * @return 内径
     */
    @Operation(summary = "获取内径", description = "获取圆环体的内径")
    public double getInnerDiameter() {
        return 2 * (getMajorRadius() - getMinorRadius());
    }

    /**
     * 获取圆环体的管直径
     * @return 管直径
     */
    @Operation(summary = "获取管直径", description = "获取圆环体的管直径")
    public double getTubeDiameter() {
        return 2 * getMinorRadius();
    }

    /**
     * 获取圆环体的中心线周长
     * @return 中心线周长
     */
    @Operation(summary = "获取中心线周长", description = "获取圆环体的中心线周长")
    public double getCenterLineCircumference() {
        return 2 * Math.PI * getMajorRadius();
    }

    /**
     * 判断圆环体是否为自相交圆环（苹果形）
     * 当次半径 > 主半径时为自相交圆环
     * @return 是否为自相交圆环
     */
    @Operation(summary = "判断是否为自相交圆环", description = "判断圆环体是否为自相交圆环（苹果形），当次半径 > 主半径时为自相交圆环")
    public boolean isSelfIntersecting() {
        return getMinorRadius() > getMajorRadius();
    }

    /**
     * 判断圆环体是否为环形（无孔）
     * 当次半径 = 主半径时为环形
     * @return 是否为环形
     */
    @Operation(summary = "判断是否为环形圆环", description = "判断圆环体是否为环形（无孔），当次半径 = 主半径时为环形")
    public boolean isHornTorus() {
        return Math.abs(getMinorRadius() - getMajorRadius()) < 1e-10;
    }

}

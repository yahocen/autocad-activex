package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.base.Point;

/**
 * 球体图形类
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-CB6B6B5A-7B7E-4C0C-8B0A-6C6B7B7E7E9F">Sphere Object (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Sphere AutoCAD 球体",
    description = "AutoCAD 中球体对象"
)
public class Sphere extends AcadEntity {

    public Sphere(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 获取球体的中心点
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-5EFC87E1-5A1E-4B2C-8552-67A6F367617A">Center Property (ActiveX)</a>
     * @return 中心点坐标
     */
    @Operation(summary = "获取球体中心点", description = "获取球体的中心点坐标")
    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    /**
     * 设置球体的中心点
     * @param center 中心点坐标
     */
    @Operation(summary = "设置球体中心点", description = "设置球体的中心点坐标")
    public Sphere setCenter(@Parameter(description = "中心点坐标") Point center) {
        setProperties("Center", center);
        return this;
    }

    /**
     * 获取球体的半径
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-28F16E3B-56A0-44AA-9449-1DB66BBF08DA">Radius Property (ActiveX)</a>
     * @return 半径
     */
    @Operation(summary = "获取球体半径", description = "获取球体的半径")
    public double getRadius() {
        return getProperties("Radius").getDouble();
    }

    /**
     * 获取球体的直径
     * @return 直径
     */
    @Operation(summary = "获取球体直径", description = "获取球体的直径")
    public double getDiameter() {
        return 2 * getRadius();
    }

    /**
     * 获取球体的体积
     * @return 体积
     */
    @Operation(summary = "获取球体体积", description = "获取球体的体积")
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(getRadius(), 3);
    }

    /**
     * 获取球体的表面积
     * @return 表面积
     */
    @Operation(summary = "获取球体表面积", description = "获取球体的表面积")
    public double getSurfaceArea() {
        return 4 * Math.PI * getRadius() * getRadius();
    }

    /**
     * 获取球体的大圆周长
     * @return 大圆周长
     */
    @Operation(summary = "获取大圆周长", description = "获取球体的大圆周长")
    public double getGreatCircleCircumference() {
        return 2 * Math.PI * getRadius();
    }

    /**
     * 获取球体的大圆面积
     * @return 大圆面积
     */
    @Operation(summary = "获取大圆面积", description = "获取球体的大圆面积")
    public double getGreatCircleArea() {
        return Math.PI * getRadius() * getRadius();
    }

    /**
     * 获取球体的上半球体积
     * @return 上半球体积
     */
    @Operation(summary = "获取上半球体积", description = "获取球体的上半球体积")
    public double getHemisphereVolume() {
        return (2.0 / 3.0) * Math.PI * Math.pow(getRadius(), 3);
    }

    /**
     * 获取球体的上半球表面积
     * @return 上半球表面积（包含底面）
     */
    @Operation(summary = "获取上半球表面积", description = "获取球体的上半球表面积（包含底面）")
    public double getHemisphereSurfaceArea() {
        return 2 * Math.PI * getRadius() * getRadius() + getGreatCircleArea();
    }

}

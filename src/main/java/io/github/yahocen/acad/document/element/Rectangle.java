package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.base.Point;

/**
 * 矩形图形类（轻量多段线）
 * 注意：AutoCAD 中矩形实际上是闭合的轻量多段线
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-35D9E28C-38A8-477A-AFCE-F21952092190">Polyline Object (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Rectangle AutoCAD 矩形",
    description = "AutoCAD 中矩形对象（轻量多段线）"
)
public class Rectangle extends AcadEntity {

    public Rectangle(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 获取矩形的第一个角点
     * @return 第一个角点坐标
     */
    @Operation(summary = "获取第一个角点", description = "获取矩形的第一个角点坐标")
    public Point getFirstCorner() {
        // 矩形的第一个顶点
        return getCoordinate(0);
    }

    /**
     * 获取矩形的对角点（第三个角点）
     * @return 对角点坐标
     */
    @Operation(summary = "获取对角点", description = "获取矩形的对角点（第三个角点）坐标")
    public Point getOppositeCorner() {
        // 矩形的第三个顶点（对角点）
        return getCoordinate(2);
    }

    /**
     * 获取矩形的长度（X 方向）
     * @return 长度
     */
    @Operation(summary = "获取矩形长度", description = "获取矩形的长度（X 方向）")
    public double getLength() {
        Point p0 = getCoordinate(0);
        Point p1 = getCoordinate(1);
        return Math.abs(p1.getX() - p0.getX());
    }

    /**
     * 获取矩形的宽度（Y 方向）
     * @return 宽度
     */
    @Operation(summary = "获取矩形宽度", description = "获取矩形的宽度（Y 方向）")
    public double getWidth() {
        Point p0 = getCoordinate(0);
        Point p3 = getCoordinate(3);
        return Math.abs(p3.getY() - p0.getY());
    }

    /**
     * 获取矩形的面积
     * @return 面积
     */
    @Operation(summary = "获取矩形面积", description = "获取矩形的面积")
    public double getArea() {
        return getLength() * getWidth();
    }

    /**
     * 获取矩形的周长
     * @return 周长
     */
    @Operation(summary = "获取矩形周长", description = "获取矩形的周长")
    public double getPerimeter() {
        return 2 * (getLength() + getWidth());
    }

    /**
     * 获取矩形对角线长度
     * @return 对角线长度
     */
    @Operation(summary = "获取矩形对角线", description = "获取矩形对角线长度")
    public double getDiagonal() {
        double length = getLength();
        double width = getWidth();
        return Math.sqrt(length * length + width * width);
    }

    /**
     * 获取矩形的中心点
     * @return 中心点坐标
     */
    @Operation(summary = "获取矩形中心点", description = "获取矩形的中心点坐标")
    public Point getCenter() {
        Point p0 = getFirstCorner();
        Point p2 = getOppositeCorner();
        return Point.of(
            (p0.getX() + p2.getX()) / 2,
            (p0.getY() + p2.getY()) / 2,
            (p0.getZ() + p2.getZ()) / 2
        );
    }

    /**
     * 获取矩形的宽高比
     * @return 宽高比
     */
    @Operation(summary = "获取宽高比", description = "获取矩形的宽高比")
    public double getAspectRatio() {
        return getWidth() / getLength();
    }

    /**
     * 判断矩形是否为正方形
     * @return 是否为正方形
     */
    @Operation(summary = "判断是否为正方形", description = "判断矩形是否为正方形")
    public boolean isSquare() {
        return Math.abs(getLength() - getWidth()) < 1e-10;
    }

    /**
     * 获取指定索引的顶点坐标
     * @param index 顶点索引（0-3）
     * @return 顶点坐标
     */
    @Operation(summary = "获取指定索引顶点坐标", description = "获取矩形指定索引的顶点坐标")
    public Point getCoordinate(@Parameter(description = "顶点索引（0-3）") int index) {
        return Point.of(call("Coordinate", index).toSafeArray());
    }

    /**
     * 获取矩形的标高
     * @return 标高
     */
    @Operation(summary = "获取标高", description = "获取矩形的标高")
    public double getElevation() {
        return getProperties("Elevation").getDouble();
    }

}

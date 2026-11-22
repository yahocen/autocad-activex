package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.document.base.Point;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Trace - AutoCAD Trace 实体
 */
@AcadEntityName("AcDbTrace")
public class Trace extends AcadEntity {

    public Trace(Dispatch dispatch) {
        super(dispatch);
    }

    @Operation(summary = "获取指定顶点坐标", description = "Coordinate(Index) 带索引属性：返回第 index 个顶点（从 0 开始）的三维坐标")
    public Point getCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index) {
        return Point.of(getIndexedProperty("Coordinate", index));
    }

    @Operation(summary = "设置指定顶点坐标", description = "Coordinate(Index) 带索引属性：修改第 index 个顶点（从 0 开始）的坐标")
    public Trace setCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index, @Parameter(description = "新坐标") Point point) {
        setIndexedProperty("Coordinate", point.toVariant(), index);
        return this;
    }

    public Variant getCoordinates() {
        return getProperties("Coordinates");
    }

    public Trace setCoordinates(Variant coordinates) {
        setProperties("Coordinates", coordinates);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Trace setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public Trace setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

}
package io.github.yahocen.acad.document.element;

import java.util.List;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Polyline - AutoCAD Polyline 实体
 */
@AcadEntityName("AcDb2dPolyline")
public class Polyline extends AcadEntity {

    public Polyline(Dispatch dispatch) {
        super(dispatch);
    }

    public double getArea() {
        return getProperties("Area").getDouble();
    }

    public Variant getClosed() {
        return getProperties("Closed");
    }

    public Polyline setClosed(Variant closed) {
        setProperties("Closed", closed);
        return this;
    }

    @Operation(summary = "设置是否闭合", description = "设置多段线是否闭合（首尾自动相连）。作为填充边界时必须闭合：polyline(...).setClosed(true)")
    @Parameter(name = "closed", description = "true=闭合")
    public Polyline setClosed(boolean closed) {
        setProperties("Closed", closed);
        return this;
    }

    public double getConstantWidth() {
        return getProperties("ConstantWidth").getDouble();
    }

    public Polyline setConstantWidth(double constantWidth) {
        setProperties("ConstantWidth", constantWidth);
        return this;
    }

    @Operation(summary = "获取指定顶点坐标", description = "Coordinate(Index) 带索引属性：返回第 index 个顶点（从 0 开始）的三维坐标")
    public Point getCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index) {
        return Point.of(getIndexedProperty("Coordinate", index));
    }

    @Operation(summary = "设置指定顶点坐标", description = "Coordinate(Index) 带索引属性：修改第 index 个顶点（从 0 开始）的坐标")
    public Polyline setCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index, @Parameter(description = "新坐标") Point point) {
        setIndexedProperty("Coordinate", point.toVariant(), index);
        return this;
    }

    public Variant getCoordinates() {
        return getProperties("Coordinates");
    }

    public Polyline setCoordinates(Variant coordinates) {
        setProperties("Coordinates", coordinates);
        return this;
    }

    public Variant getElevation() {
        return getProperties("Elevation");
    }

    public Polyline setElevation(Variant elevation) {
        setProperties("Elevation", elevation);
        return this;
    }

    public double getLength() {
        return getProperties("Length").getDouble();
    }

    public Variant getLinetypeGeneration() {
        return getProperties("LinetypeGeneration");
    }

    public Polyline setLinetypeGeneration(Variant linetypeGeneration) {
        setProperties("LinetypeGeneration", linetypeGeneration);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Polyline setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public Polyline setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

    public int getType() {
        return getProperties("Type").getInt();
    }

    public Polyline setType(int type) {
        setProperties("Type", type);
        return this;
    }

    public Variant appendVertex(Variant vertex) {
        return call("AppendVertex", vertex);
    }

    public Variant explode() {
        return call("Explode");
    }

    public Variant getBulge(Variant index) {
        return call("GetBulge", index);
    }

    public Variant setBulge(Variant index, Variant bulge) {
        return call("SetBulge", index, bulge);
    }

    public Variant getWidth(Variant index, Variant startWidth, Variant endWidth) {
        return call("GetWidth", index, startWidth, endWidth);
    }

    public Variant setWidth(Variant index, Variant startWidth, Variant endWidth) {
        return call("SetWidth", index, startWidth, endWidth);
    }

    public Variant offset(Variant distance) {
        return call("Offset", distance);
    }

    @Operation(summary = "获取全部顶点", description = "把 Coordinates 按每点 3 个坐标切分为点列表")
    public List<Point> getPoints() {
        return Point.listOf(getCoordinates(), 3);
    }

    @Operation(summary = "获取第一个顶点", description = "等同 getCoordinate(0)")
    public Point getFirstPoint() {
        return getCoordinate(0);
    }

    @Operation(summary = "获取最后一个顶点", description = "多段线的最后一个顶点")
    public Point getLastPoint() {
        var points = getPoints();
        return points.get(points.size() - 1);
    }

}

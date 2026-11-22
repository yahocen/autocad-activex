package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.document.base.Point;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * PolygonMesh - AutoCAD PolygonMesh 实体
 */
@AcadEntityName("AcDbPolygonMesh")
public class PolygonMesh extends AcadEntity {

    public PolygonMesh(Dispatch dispatch) {
        super(dispatch);
    }

    @Operation(summary = "获取指定顶点坐标", description = "Coordinate(Index) 带索引属性：返回第 index 个顶点（从 0 开始）的三维坐标")
    public Point getCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index) {
        return Point.of(getIndexedProperty("Coordinate", index));
    }

    @Operation(summary = "设置指定顶点坐标", description = "Coordinate(Index) 带索引属性：修改第 index 个顶点（从 0 开始）的坐标")
    public PolygonMesh setCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index, @Parameter(description = "新坐标") Point point) {
        setIndexedProperty("Coordinate", point.toVariant(), index);
        return this;
    }

    public Variant getCoordinates() {
        return getProperties("Coordinates");
    }

    public PolygonMesh setCoordinates(Variant coordinates) {
        setProperties("Coordinates", coordinates);
        return this;
    }

    public Variant getMClose() {
        return getProperties("MClose");
    }

    public PolygonMesh setMClose(Variant mClose) {
        setProperties("MClose", mClose);
        return this;
    }

    public Variant getMDensity() {
        return getProperties("MDensity");
    }

    public PolygonMesh setMDensity(Variant mDensity) {
        setProperties("MDensity", mDensity);
        return this;
    }

    public int getMVertexCount() {
        return getProperties("MVertexCount").getInt();
    }

    public Variant getNClose() {
        return getProperties("NClose");
    }

    public PolygonMesh setNClose(Variant nClose) {
        setProperties("NClose", nClose);
        return this;
    }

    public Variant getNDensity() {
        return getProperties("NDensity");
    }

    public PolygonMesh setNDensity(Variant nDensity) {
        setProperties("NDensity", nDensity);
        return this;
    }

    public int getNVertexCount() {
        return getProperties("NVertexCount").getInt();
    }

    public int getType() {
        return getProperties("Type").getInt();
    }

    public PolygonMesh setType(int type) {
        setProperties("Type", type);
        return this;
    }

    public Variant appendVertex(Variant vertex) {
        return call("AppendVertex", vertex);
    }

    public Variant explode() {
        return call("Explode");
    }

}
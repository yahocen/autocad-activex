package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.document.base.Point;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * SubDMesh - AutoCAD SubDMesh 实体
 */
@AcadEntityName("AcDbSubDMesh")
public class SubDMesh extends AcadEntity {

    public SubDMesh(Dispatch dispatch) {
        super(dispatch);
    }

    @Operation(summary = "获取指定顶点坐标", description = "Coordinate(Index) 带索引属性：返回第 index 个顶点（从 0 开始）的三维坐标")
    public Point getCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index) {
        return Point.of(getIndexedProperty("Coordinate", index));
    }

    @Operation(summary = "设置指定顶点坐标", description = "Coordinate(Index) 带索引属性：修改第 index 个顶点（从 0 开始）的坐标")
    public SubDMesh setCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index, @Parameter(description = "新坐标") Point point) {
        setIndexedProperty("Coordinate", point.toVariant(), index);
        return this;
    }

    public Variant getCoordinates() {
        return getProperties("Coordinates");
    }

    public SubDMesh setCoordinates(Variant coordinates) {
        setProperties("Coordinates", coordinates);
        return this;
    }

    public int getFaceCount() {
        return getProperties("FaceCount").getInt();
    }

    public Variant getSmoothness() {
        return getProperties("Smoothness");
    }

    public SubDMesh setSmoothness(Variant smoothness) {
        setProperties("Smoothness", smoothness);
        return this;
    }

    public int getVertexCount() {
        return getProperties("VertexCount").getInt();
    }

}
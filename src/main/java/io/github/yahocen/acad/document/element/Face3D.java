package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.document.base.Point;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Face3D - AutoCAD Face3D 实体
 */
@AcadEntityName("AcDbFace")
public class Face3D extends AcadEntity {

    public Face3D(Dispatch dispatch) {
        super(dispatch);
    }

    @Operation(summary = "获取指定顶点坐标", description = "Coordinate(Index) 带索引属性：返回第 index 个顶点（从 0 开始）的三维坐标")    public Point getCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index) {
        return Point.of(getIndexedProperty("Coordinate", index));
    }

    @Operation(summary = "设置指定顶点坐标", description = "Coordinate(Index) 带索引属性：修改第 index 个顶点（从 0 开始）的坐标")
    public Face3D setCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index, @Parameter(description = "新坐标") Point point) {
        setIndexedProperty("Coordinate", point.toVariant(), index);
        return this;
    }

    public Variant getCoordinates() {
        return getProperties("Coordinates");
    }

    public Face3D setCoordinates(Variant coordinates) {
        setProperties("Coordinates", coordinates);
        return this;
    }

    public Variant getVisibilityEdge1() {
        return getProperties("VisibilityEdge1");
    }

    public Face3D setVisibilityEdge1(Variant visibilityEdge1) {
        setProperties("VisibilityEdge1", visibilityEdge1);
        return this;
    }

    public Variant getVisibilityEdge2() {
        return getProperties("VisibilityEdge2");
    }

    public Face3D setVisibilityEdge2(Variant visibilityEdge2) {
        setProperties("VisibilityEdge2", visibilityEdge2);
        return this;
    }

    public Variant getVisibilityEdge3() {
        return getProperties("VisibilityEdge3");
    }

    public Face3D setVisibilityEdge3(Variant visibilityEdge3) {
        setProperties("VisibilityEdge3", visibilityEdge3);
        return this;
    }

    public Variant getVisibilityEdge4() {
        return getProperties("VisibilityEdge4");
    }

    public Face3D setVisibilityEdge4(Variant visibilityEdge4) {
        setProperties("VisibilityEdge4", visibilityEdge4);
        return this;
    }

    public Variant getInvisibleEdge(Variant index) {
        return call("GetInvisibleEdge", index);
    }

    public Variant setInvisibleEdge(Variant index, Variant state) {
        return call("SetInvisibleEdge", index, state);
    }

}
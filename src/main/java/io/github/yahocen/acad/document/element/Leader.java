package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.document.base.Point;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.github.yahocen.acad.util.EntityUtil;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Leader - AutoCAD Leader 实体
 */
@AcadEntityName("AcDbLeader")
public class Leader extends AcadEntity {

    public Leader(Dispatch dispatch) {
        super(dispatch);
    }

    public AcadEntity getAnnotation() {
        Variant v = getProperties("Annotation");
        Dispatch d = v.getvt() == Variant.VariantDispatch ? v.getDispatch() : null;
        return d == null || d.m_pDispatch == 0 ? null : EntityUtil.parse(d);
    }

    public Leader setAnnotation(AcadEntity annotation) {
        setProperties("Annotation", annotation.getDispatch());
        return this;
    }

    public Variant getArrowheadBlock() {
        return getProperties("ArrowheadBlock");
    }

    public Leader setArrowheadBlock(Variant arrowheadBlock) {
        setProperties("ArrowheadBlock", arrowheadBlock);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public Leader setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public int getArrowheadType() {
        return getProperties("ArrowheadType").getInt();
    }

    public Leader setArrowheadType(int arrowheadType) {
        setProperties("ArrowheadType", arrowheadType);
        return this;
    }

    @Operation(summary = "获取指定顶点坐标", description = "Coordinate(Index) 带索引属性：返回第 index 个顶点（从 0 开始）的三维坐标")
    public Point getCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index) {
        return Point.of(getIndexedProperty("Coordinate", index));
    }

    @Operation(summary = "设置指定顶点坐标", description = "Coordinate(Index) 带索引属性：修改第 index 个顶点（从 0 开始）的坐标")
    public Leader setCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index, @Parameter(description = "新坐标") Point point) {
        setIndexedProperty("Coordinate", point.toVariant(), index);
        return this;
    }

    public Variant getCoordinates() {
        return getProperties("Coordinates");
    }

    public Leader setCoordinates(Variant coordinates) {
        setProperties("Coordinates", coordinates);
        return this;
    }

    public int getDimensionLineColor() {
        return getProperties("DimensionLineColor").getInt();
    }

    public Leader setDimensionLineColor(int dimensionLineColor) {
        setProperties("DimensionLineColor", dimensionLineColor);
        return this;
    }

    public int getDimensionLineWeight() {
        return getProperties("DimensionLineWeight").getInt();
    }

    public Leader setDimensionLineWeight(int dimensionLineWeight) {
        setProperties("DimensionLineWeight", dimensionLineWeight);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public Leader setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public Leader setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public Variant getTextGap() {
        return getProperties("TextGap");
    }

    public Leader setTextGap(Variant textGap) {
        setProperties("TextGap", textGap);
        return this;
    }

    public int getType() {
        return getProperties("Type").getInt();
    }

    public Leader setType(int type) {
        setProperties("Type", type);
        return this;
    }

    public int getVerticalTextPosition() {
        return getProperties("VerticalTextPosition").getInt();
    }

    public Leader setVerticalTextPosition(int verticalTextPosition) {
        setProperties("VerticalTextPosition", verticalTextPosition);
        return this;
    }

    public Variant evaluate() {
        return call("Evaluate");
    }

}
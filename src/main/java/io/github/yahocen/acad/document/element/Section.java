package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.document.base.Point;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.github.yahocen.acad.document.manager.SectionSettings;
import io.github.yahocen.acad.document.manager.AcCmColor;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Section - AutoCAD Section 实体
 */
@AcadEntityName("AcDbSection")
public class Section extends AcadEntity {

    public Section(Dispatch dispatch) {
        super(dispatch);
    }

    public double getBottomHeight() {
        return getProperties("BottomHeight").getDouble();
    }

    public Section setBottomHeight(double bottomHeight) {
        setProperties("BottomHeight", bottomHeight);
        return this;
    }

    @Operation(summary = "获取指定顶点坐标", description = "Coordinate(Index) 带索引属性：返回第 index 个顶点（从 0 开始）的三维坐标")
    public Point getCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index) {
        return Point.of(getIndexedProperty("Coordinate", index));
    }

    @Operation(summary = "设置指定顶点坐标", description = "Coordinate(Index) 带索引属性：修改第 index 个顶点（从 0 开始）的坐标")
    public Section setCoordinate(@Parameter(description = "顶点索引，从 0 开始") int index, @Parameter(description = "新坐标") Point point) {
        setIndexedProperty("Coordinate", point.toVariant(), index);
        return this;
    }

    public Variant getElevation() {
        return getProperties("Elevation");
    }

    public Section setElevation(Variant elevation) {
        setProperties("Elevation", elevation);
        return this;
    }

    public AcCmColor getIndicatorFillColor() {
        return new AcCmColor(getProperties("IndicatorFillColor").getDispatch());
    }

    public Section setIndicatorFillColor(AcCmColor indicatorFillColor) {
        setProperties("IndicatorFillColor", indicatorFillColor.getDispatch());
        return this;
    }

    public int getIndicatorTransparency() {
        return getProperties("IndicatorTransparency").getInt();
    }

    public Section setIndicatorTransparency(int indicatorTransparency) {
        setProperties("IndicatorTransparency", indicatorTransparency);
        return this;
    }

    public Variant getLiveSectionEnabled() {
        return getProperties("LiveSectionEnabled");
    }

    public Section setLiveSectionEnabled(Variant liveSectionEnabled) {
        setProperties("LiveSectionEnabled", liveSectionEnabled);
        return this;
    }

    public String getName() {
        return getProperties("Name").getString();
    }

    public Section setName(String name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Variant getNumVertices() {
        return getProperties("NumVertices");
    }

    public SectionSettings getSettings() {
        return new SectionSettings(getProperties("Settings").getDispatch());
    }

    public int getState() {
        return getProperties("State").getInt();
    }

    public Section setState(int state) {
        setProperties("State", state);
        return this;
    }

    public double getTopHeight() {
        return getProperties("TopHeight").getDouble();
    }

    public Section setTopHeight(double topHeight) {
        setProperties("TopHeight", topHeight);
        return this;
    }

    public Variant getVerticalDirection() {
        return getProperties("VerticalDirection");
    }

    public Section setVerticalDirection(Variant verticalDirection) {
        setProperties("VerticalDirection", verticalDirection);
        return this;
    }

    public Variant getVertices() {
        return getProperties("Vertices");
    }

    public Section setVertices(Variant vertices) {
        setProperties("Vertices", vertices);
        return this;
    }

    public Variant getViewingDirection() {
        return getProperties("ViewingDirection");
    }

    public Section setViewingDirection(Variant viewingDirection) {
        setProperties("ViewingDirection", viewingDirection);
        return this;
    }

    public Variant addVertex(Variant nIndex, Variant val) {
        return call("AddVertex", nIndex, val);
    }

    public Variant removeVertex(Variant nIndex) {
        return call("RemoveVertex", nIndex);
    }

    public Variant hitTest(Variant varPtHit, Variant pHit, Variant pSegmentIndex, Variant pPtOnSegment, int pSubItem) {
        return call("HitTest", varPtHit, pHit, pSegmentIndex, pPtOnSegment, pSubItem);
    }

    public Variant createJog(Variant varPt) {
        return call("CreateJog", varPt);
    }

    public Variant generateSectionGeometry(int pEntity, Variant pIntersectionBoundaryObjs, Variant pIntersectionFillObjs, Variant pBackgroudnObjs, Variant pForegroudObjs, Variant pCurveTangencyObjs) {
        return call("GenerateSectionGeometry", pEntity, pIntersectionBoundaryObjs, pIntersectionFillObjs, pBackgroudnObjs, pForegroudObjs, pCurveTangencyObjs);
    }

}
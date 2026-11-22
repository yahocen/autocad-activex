// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.document.manager.View;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * PViewport - AutoCAD PViewport 实体
 */
@AcadEntityName("AcDbViewport")
public class PViewport extends AcadEntity {

    public PViewport(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getArcSmoothness() {
        return getProperties("ArcSmoothness");
    }

    public PViewport setArcSmoothness(Variant arcSmoothness) {
        setProperties("ArcSmoothness", arcSmoothness);
        return this;
    }

    public Point getCenter() {
        return Point.of(getProperties("Center").toSafeArray());
    }

    public PViewport setCenter(Point center) {
        setProperties("Center", center.toVariant());
        return this;
    }

    public Variant getClipped() {
        return getProperties("Clipped");
    }

    public double getCustomScale() {
        return getProperties("CustomScale").getDouble();
    }

    public PViewport setCustomScale(double customScale) {
        setProperties("CustomScale", customScale);
        return this;
    }

    public Variant getDirection() {
        return getProperties("Direction");
    }

    public PViewport setDirection(Variant direction) {
        setProperties("Direction", direction);
        return this;
    }

    public Variant getDisplayLocked() {
        return getProperties("DisplayLocked");
    }

    public PViewport setDisplayLocked(Variant displayLocked) {
        setProperties("DisplayLocked", displayLocked);
        return this;
    }

    public Variant getGridOn() {
        return getProperties("GridOn");
    }

    public PViewport setGridOn(Variant gridOn) {
        setProperties("GridOn", gridOn);
        return this;
    }

    public Variant getHasSheetView() {
        return getProperties("HasSheetView");
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public PViewport setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public long getLabelBlockId() {
        return getProperties("LabelBlockId").getLong();
    }

    public PViewport setLabelBlockId(long labelBlockId) {
        setProperties("LabelBlockId", labelBlockId);
        return this;
    }

    public Variant getLayerPropertyOverrides() {
        return getProperties("LayerPropertyOverrides");
    }

    public double getLensLength() {
        return getProperties("LensLength").getDouble();
    }

    public PViewport setLensLength(double lensLength) {
        setProperties("LensLength", lensLength);
        return this;
    }

    public View getModelView() {
        return new View(getProperties("ModelView").getDispatch());
    }

    public PViewport setModelView(View modelView) {
        setProperties("ModelView", modelView.getDispatch());
        return this;
    }

    public Variant getRemoveHiddenLines() {
        return getProperties("RemoveHiddenLines");
    }

    public PViewport setRemoveHiddenLines(Variant removeHiddenLines) {
        setProperties("RemoveHiddenLines", removeHiddenLines);
        return this;
    }

    public Variant getShadePlot() {
        return getProperties("ShadePlot");
    }

    public PViewport setShadePlot(Variant shadePlot) {
        setProperties("ShadePlot", shadePlot);
        return this;
    }

    public View getSheetView() {
        return new View(getProperties("SheetView").getDispatch());
    }

    public PViewport setSheetView(View sheetView) {
        setProperties("SheetView", sheetView.getDispatch());
        return this;
    }

    public Point getSnapBasePoint() {
        return Point.of(getProperties("SnapBasePoint").toSafeArray());
    }

    public PViewport setSnapBasePoint(Point snapBasePoint) {
        setProperties("SnapBasePoint", snapBasePoint.toVariant());
        return this;
    }

    public Variant getSnapOn() {
        return getProperties("SnapOn");
    }

    public PViewport setSnapOn(Variant snapOn) {
        setProperties("SnapOn", snapOn);
        return this;
    }

    public double getSnapRotationAngle() {
        return getProperties("SnapRotationAngle").getDouble();
    }

    public PViewport setSnapRotationAngle(double snapRotationAngle) {
        setProperties("SnapRotationAngle", snapRotationAngle);
        return this;
    }

    public int getStandardScale() {
        return getProperties("StandardScale").getInt();
    }

    public PViewport setStandardScale(int standardScale) {
        setProperties("StandardScale", standardScale);
        return this;
    }

    public int getStandardScale2() {
        return getProperties("StandardScale2").getInt();
    }

    public PViewport setStandardScale2(int standardScale2) {
        setProperties("StandardScale2", standardScale2);
        return this;
    }

    public Variant getStyleSheet() {
        return getProperties("StyleSheet");
    }

    public PViewport setStyleSheet(Variant styleSheet) {
        setProperties("StyleSheet", styleSheet);
        return this;
    }

    public Variant getTarget() {
        return getProperties("Target");
    }

    public PViewport setTarget(Variant target) {
        setProperties("Target", target);
        return this;
    }

    public double getTwistAngle() {
        return getProperties("TwistAngle").getDouble();
    }

    public PViewport setTwistAngle(double twistAngle) {
        setProperties("TwistAngle", twistAngle);
        return this;
    }

    public Variant getUCSIconAtOrigin() {
        return getProperties("UCSIconAtOrigin");
    }

    public PViewport setUCSIconAtOrigin(Variant uCSIconAtOrigin) {
        setProperties("UCSIconAtOrigin", uCSIconAtOrigin);
        return this;
    }

    public Variant getUCSIconOn() {
        return getProperties("UCSIconOn");
    }

    public PViewport setUCSIconOn(Variant uCSIconOn) {
        setProperties("UCSIconOn", uCSIconOn);
        return this;
    }

    public Variant getUCSPerViewport() {
        return getProperties("UCSPerViewport");
    }

    public PViewport setUCSPerViewport(Variant uCSPerViewport) {
        setProperties("UCSPerViewport", uCSPerViewport);
        return this;
    }

    public Variant getViewportOn() {
        return getProperties("ViewportOn");
    }

    public PViewport setViewportOn(Variant viewportOn) {
        setProperties("ViewportOn", viewportOn);
        return this;
    }

    public Variant getVisualStyle() {
        return getProperties("VisualStyle");
    }

    public PViewport setVisualStyle(Variant visualStyle) {
        setProperties("VisualStyle", visualStyle);
        return this;
    }

    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    public PViewport setWidth(double width) {
        setProperties("Width", width);
        return this;
    }

    public Variant getGridSpacing(Variant xSpacing, Variant ySpacing) {
        return call("GetGridSpacing", xSpacing, ySpacing);
    }

    public Variant setGridSpacing(Variant xSpacing, Variant ySpacing) {
        return call("SetGridSpacing", xSpacing, ySpacing);
    }

    public Variant getSnapSpacing(Variant xSpacing, Variant ySpacing) {
        return call("GetSnapSpacing", xSpacing, ySpacing);
    }

    public Variant setSnapSpacing(Variant xSpacing, Variant ySpacing) {
        return call("SetSnapSpacing", xSpacing, ySpacing);
    }

    public Variant display(Variant bStatus) {
        return call("Display", bStatus);
    }

    public Variant syncModelView() {
        return call("SyncModelView");
    }

}
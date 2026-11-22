// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.document.Block;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadLayout — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Layout extends AcadObject {

    public Layout(Dispatch dispatch) {
        super(dispatch);
    }
    public Block getBlock() {
        return new Block(getProperties("Block").getDispatch());
    }

    public Variant getCanonicalMediaName() {
        return getProperties("CanonicalMediaName");
    }

    public Layout setCanonicalMediaName(Variant canonicalMediaName) {
        setProperties("CanonicalMediaName", canonicalMediaName);
        return this;
    }

    public Variant getCenterPlot() {
        return getProperties("CenterPlot");
    }

    public Layout setCenterPlot(Variant centerPlot) {
        setProperties("CenterPlot", centerPlot);
        return this;
    }

    public Variant getConfigName() {
        return getProperties("ConfigName");
    }

    public Layout setConfigName(Variant configName) {
        setProperties("ConfigName", configName);
        return this;
    }

    public Variant getModelType() {
        return getProperties("ModelType");
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public Layout setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public int getPaperUnits() {
        return getProperties("PaperUnits").getInt();
    }

    public Layout setPaperUnits(int paperUnits) {
        setProperties("PaperUnits", paperUnits);
        return this;
    }

    public Variant getPlotHidden() {
        return getProperties("PlotHidden");
    }

    public Layout setPlotHidden(Variant plotHidden) {
        setProperties("PlotHidden", plotHidden);
        return this;
    }

    public Variant getPlotOrigin() {
        return getProperties("PlotOrigin");
    }

    public Layout setPlotOrigin(Variant plotOrigin) {
        setProperties("PlotOrigin", plotOrigin);
        return this;
    }

    public int getPlotRotation() {
        return getProperties("PlotRotation").getInt();
    }

    public Layout setPlotRotation(int plotRotation) {
        setProperties("PlotRotation", plotRotation);
        return this;
    }

    public int getPlotType() {
        return getProperties("PlotType").getInt();
    }

    public Layout setPlotType(int plotType) {
        setProperties("PlotType", plotType);
        return this;
    }

    public Variant getPlotViewportBorders() {
        return getProperties("PlotViewportBorders");
    }

    public Layout setPlotViewportBorders(Variant plotViewportBorders) {
        setProperties("PlotViewportBorders", plotViewportBorders);
        return this;
    }

    public Variant getPlotViewportsFirst() {
        return getProperties("PlotViewportsFirst");
    }

    public Layout setPlotViewportsFirst(Variant plotViewportsFirst) {
        setProperties("PlotViewportsFirst", plotViewportsFirst);
        return this;
    }

    public Variant getPlotWithLineweights() {
        return getProperties("PlotWithLineweights");
    }

    public Layout setPlotWithLineweights(Variant plotWithLineweights) {
        setProperties("PlotWithLineweights", plotWithLineweights);
        return this;
    }

    public Variant getPlotWithPlotStyles() {
        return getProperties("PlotWithPlotStyles");
    }

    public Layout setPlotWithPlotStyles(Variant plotWithPlotStyles) {
        setProperties("PlotWithPlotStyles", plotWithPlotStyles);
        return this;
    }

    public Variant getScaleLineweights() {
        return getProperties("ScaleLineweights");
    }

    public Layout setScaleLineweights(Variant scaleLineweights) {
        setProperties("ScaleLineweights", scaleLineweights);
        return this;
    }

    public Variant getShowPlotStyles() {
        return getProperties("ShowPlotStyles");
    }

    public Layout setShowPlotStyles(Variant showPlotStyles) {
        setProperties("ShowPlotStyles", showPlotStyles);
        return this;
    }

    public int getStandardScale() {
        return getProperties("StandardScale").getInt();
    }

    public Layout setStandardScale(int standardScale) {
        setProperties("StandardScale", standardScale);
        return this;
    }

    public Variant getStyleSheet() {
        return getProperties("StyleSheet");
    }

    public Layout setStyleSheet(Variant styleSheet) {
        setProperties("StyleSheet", styleSheet);
        return this;
    }

    public Variant getTabOrder() {
        return getProperties("TabOrder");
    }

    public Layout setTabOrder(Variant tabOrder) {
        setProperties("TabOrder", tabOrder);
        return this;
    }

    public Variant getUseStandardScale() {
        return getProperties("UseStandardScale");
    }

    public Layout setUseStandardScale(Variant useStandardScale) {
        setProperties("UseStandardScale", useStandardScale);
        return this;
    }

    public Variant getViewToPlot() {
        return getProperties("ViewToPlot");
    }

    public Layout setViewToPlot(Variant viewToPlot) {
        setProperties("ViewToPlot", viewToPlot);
        return this;
    }

    public Variant getCustomScale(Variant numerator, Variant denominator) {
        Variant result = Disp.call(getClass(), dispatch, "GetCustomScale", numerator, denominator);
        return result;
    }

    public Variant setCustomScale(Variant numerator, Variant denominator) {
        Variant result = Disp.call(getClass(), dispatch, "SetCustomScale", numerator, denominator);
        return result;
    }

    public Variant getPaperMargins(Variant lowerLeft, Variant upperRight) {
        Variant result = Disp.call(getClass(), dispatch, "GetPaperMargins", lowerLeft, upperRight);
        return result;
    }

    public Variant getPaperSize(Variant width, Variant height) {
        Variant result = Disp.call(getClass(), dispatch, "GetPaperSize", width, height);
        return result;
    }

    public Variant getWindowToPlot(Variant lowerLeft, Variant upperRight) {
        Variant result = Disp.call(getClass(), dispatch, "GetWindowToPlot", lowerLeft, upperRight);
        return result;
    }

    public Variant setWindowToPlot(Variant lowerLeft, Variant upperRight) {
        Variant result = Disp.call(getClass(), dispatch, "SetWindowToPlot", lowerLeft, upperRight);
        return result;
    }

    public Variant copyFrom(Variant pPlotConfig) {
        Variant result = Disp.call(getClass(), dispatch, "CopyFrom", pPlotConfig);
        return result;
    }

    public Variant getCanonicalMediaNames() {
        Variant result = Disp.call(getClass(), dispatch, "GetCanonicalMediaNames");
        return result;
    }

    public Variant getPlotDeviceNames() {
        Variant result = Disp.call(getClass(), dispatch, "GetPlotDeviceNames");
        return result;
    }

    public Variant getPlotStyleTableNames() {
        Variant result = Disp.call(getClass(), dispatch, "GetPlotStyleTableNames");
        return result;
    }

    public Variant refreshPlotDeviceInfo() {
        Variant result = Disp.call(getClass(), dispatch, "RefreshPlotDeviceInfo");
        return result;
    }

    public Variant getLocaleMediaName(Variant name) {
        Variant result = Disp.call(getClass(), dispatch, "GetLocaleMediaName", name);
        return result;
    }

}

package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadPlotConfiguration — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PlotConfiguration extends AcadObject {

    public PlotConfiguration(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getCanonicalMediaName() {
        return getProperties("CanonicalMediaName");
    }

    public PlotConfiguration setCanonicalMediaName(Variant canonicalMediaName) {
        setProperties("CanonicalMediaName", canonicalMediaName);
        return this;
    }

    public Variant getCenterPlot() {
        return getProperties("CenterPlot");
    }

    public PlotConfiguration setCenterPlot(Variant centerPlot) {
        setProperties("CenterPlot", centerPlot);
        return this;
    }

    public Variant getConfigName() {
        return getProperties("ConfigName");
    }

    public PlotConfiguration setConfigName(Variant configName) {
        setProperties("ConfigName", configName);
        return this;
    }

    public Variant getModelType() {
        return getProperties("ModelType");
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public PlotConfiguration setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public int getPaperUnits() {
        return getProperties("PaperUnits").getInt();
    }

    public PlotConfiguration setPaperUnits(int paperUnits) {
        setProperties("PaperUnits", paperUnits);
        return this;
    }

    public Variant getPlotHidden() {
        return getProperties("PlotHidden");
    }

    public PlotConfiguration setPlotHidden(Variant plotHidden) {
        setProperties("PlotHidden", plotHidden);
        return this;
    }

    public Variant getPlotOrigin() {
        return getProperties("PlotOrigin");
    }

    public PlotConfiguration setPlotOrigin(Variant plotOrigin) {
        setProperties("PlotOrigin", plotOrigin);
        return this;
    }

    public int getPlotRotation() {
        return getProperties("PlotRotation").getInt();
    }

    public PlotConfiguration setPlotRotation(int plotRotation) {
        setProperties("PlotRotation", plotRotation);
        return this;
    }

    public int getPlotType() {
        return getProperties("PlotType").getInt();
    }

    public PlotConfiguration setPlotType(int plotType) {
        setProperties("PlotType", plotType);
        return this;
    }

    public Variant getPlotViewportBorders() {
        return getProperties("PlotViewportBorders");
    }

    public PlotConfiguration setPlotViewportBorders(Variant plotViewportBorders) {
        setProperties("PlotViewportBorders", plotViewportBorders);
        return this;
    }

    public Variant getPlotViewportsFirst() {
        return getProperties("PlotViewportsFirst");
    }

    public PlotConfiguration setPlotViewportsFirst(Variant plotViewportsFirst) {
        setProperties("PlotViewportsFirst", plotViewportsFirst);
        return this;
    }

    public Variant getPlotWithLineweights() {
        return getProperties("PlotWithLineweights");
    }

    public PlotConfiguration setPlotWithLineweights(Variant plotWithLineweights) {
        setProperties("PlotWithLineweights", plotWithLineweights);
        return this;
    }

    public Variant getPlotWithPlotStyles() {
        return getProperties("PlotWithPlotStyles");
    }

    public PlotConfiguration setPlotWithPlotStyles(Variant plotWithPlotStyles) {
        setProperties("PlotWithPlotStyles", plotWithPlotStyles);
        return this;
    }

    public Variant getScaleLineweights() {
        return getProperties("ScaleLineweights");
    }

    public PlotConfiguration setScaleLineweights(Variant scaleLineweights) {
        setProperties("ScaleLineweights", scaleLineweights);
        return this;
    }

    public Variant getShowPlotStyles() {
        return getProperties("ShowPlotStyles");
    }

    public PlotConfiguration setShowPlotStyles(Variant showPlotStyles) {
        setProperties("ShowPlotStyles", showPlotStyles);
        return this;
    }

    public int getStandardScale() {
        return getProperties("StandardScale").getInt();
    }

    public PlotConfiguration setStandardScale(int standardScale) {
        setProperties("StandardScale", standardScale);
        return this;
    }

    public Variant getStyleSheet() {
        return getProperties("StyleSheet");
    }

    public PlotConfiguration setStyleSheet(Variant styleSheet) {
        setProperties("StyleSheet", styleSheet);
        return this;
    }

    public Variant getUseStandardScale() {
        return getProperties("UseStandardScale");
    }

    public PlotConfiguration setUseStandardScale(Variant useStandardScale) {
        setProperties("UseStandardScale", useStandardScale);
        return this;
    }

    public Variant getViewToPlot() {
        return getProperties("ViewToPlot");
    }

    public PlotConfiguration setViewToPlot(Variant viewToPlot) {
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

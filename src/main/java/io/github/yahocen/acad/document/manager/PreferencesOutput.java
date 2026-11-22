package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesOutput — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesOutput extends ComObject {

    public PreferencesOutput(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getAutomaticPlotLog() {
        return Disp.get(getClass(), dispatch, "AutomaticPlotLog");
    }

    public PreferencesOutput setAutomaticPlotLog(Variant automaticPlotLog) {
        Disp.put(getClass(), dispatch, "AutomaticPlotLog", automaticPlotLog);
        return this;
    }

    public Variant getContinuousPlotLog() {
        return Disp.get(getClass(), dispatch, "ContinuousPlotLog");
    }

    public PreferencesOutput setContinuousPlotLog(Variant continuousPlotLog) {
        Disp.put(getClass(), dispatch, "ContinuousPlotLog", continuousPlotLog);
        return this;
    }

    public Variant getDefaultOutputDevice() {
        return Disp.get(getClass(), dispatch, "DefaultOutputDevice");
    }

    public PreferencesOutput setDefaultOutputDevice(Variant defaultOutputDevice) {
        Disp.put(getClass(), dispatch, "DefaultOutputDevice", defaultOutputDevice);
        return this;
    }

    public Variant getDefaultPlotStyleForLayer() {
        return Disp.get(getClass(), dispatch, "DefaultPlotStyleForLayer");
    }

    public PreferencesOutput setDefaultPlotStyleForLayer(Variant defaultPlotStyleForLayer) {
        Disp.put(getClass(), dispatch, "DefaultPlotStyleForLayer", defaultPlotStyleForLayer);
        return this;
    }

    public Variant getDefaultPlotStyleForObjects() {
        return Disp.get(getClass(), dispatch, "DefaultPlotStyleForObjects");
    }

    public PreferencesOutput setDefaultPlotStyleForObjects(Variant defaultPlotStyleForObjects) {
        Disp.put(getClass(), dispatch, "DefaultPlotStyleForObjects", defaultPlotStyleForObjects);
        return this;
    }

    public Variant getDefaultPlotStyleTable() {
        return Disp.get(getClass(), dispatch, "DefaultPlotStyleTable");
    }

    public PreferencesOutput setDefaultPlotStyleTable(Variant defaultPlotStyleTable) {
        Disp.put(getClass(), dispatch, "DefaultPlotStyleTable", defaultPlotStyleTable);
        return this;
    }

    public Variant getDefaultPlotToFilePath() {
        return Disp.get(getClass(), dispatch, "DefaultPlotToFilePath");
    }

    public PreferencesOutput setDefaultPlotToFilePath(Variant defaultPlotToFilePath) {
        Disp.put(getClass(), dispatch, "DefaultPlotToFilePath", defaultPlotToFilePath);
        return this;
    }

    public int getOLEQuality() {
        return Disp.get(getClass(), dispatch, "OLEQuality").getInt();
    }

    public PreferencesOutput setOLEQuality(int oLEQuality) {
        Disp.put(getClass(), dispatch, "OLEQuality", oLEQuality);
        return this;
    }

    public Variant getPlotLegacy() {
        return Disp.get(getClass(), dispatch, "PlotLegacy");
    }

    public PreferencesOutput setPlotLegacy(Variant plotLegacy) {
        Disp.put(getClass(), dispatch, "PlotLegacy", plotLegacy);
        return this;
    }

    public int getPlotPolicy() {
        return Disp.get(getClass(), dispatch, "PlotPolicy").getInt();
    }

    public PreferencesOutput setPlotPolicy(int plotPolicy) {
        Disp.put(getClass(), dispatch, "PlotPolicy", plotPolicy);
        return this;
    }

    public Variant getPrinterPaperSizeAlert() {
        return Disp.get(getClass(), dispatch, "PrinterPaperSizeAlert");
    }

    public PreferencesOutput setPrinterPaperSizeAlert(Variant printerPaperSizeAlert) {
        Disp.put(getClass(), dispatch, "PrinterPaperSizeAlert", printerPaperSizeAlert);
        return this;
    }

    public int getPrinterSpoolAlert() {
        return Disp.get(getClass(), dispatch, "PrinterSpoolAlert").getInt();
    }

    public PreferencesOutput setPrinterSpoolAlert(int printerSpoolAlert) {
        Disp.put(getClass(), dispatch, "PrinterSpoolAlert", printerSpoolAlert);
        return this;
    }

    public Variant getUseLastPlotSettings() {
        return Disp.get(getClass(), dispatch, "UseLastPlotSettings");
    }

    public PreferencesOutput setUseLastPlotSettings(Variant useLastPlotSettings) {
        Disp.put(getClass(), dispatch, "UseLastPlotSettings", useLastPlotSettings);
        return this;
    }

}

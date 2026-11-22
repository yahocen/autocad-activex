// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPlot — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Plot extends ComObject {

    public Plot(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getBatchPlotProgress() {
        return Disp.get(getClass(), dispatch, "BatchPlotProgress");
    }

    public Plot setBatchPlotProgress(Variant batchPlotProgress) {
        Disp.put(getClass(), dispatch, "BatchPlotProgress", batchPlotProgress);
        return this;
    }

    public Variant getNumberOfCopies() {
        return Disp.get(getClass(), dispatch, "NumberOfCopies");
    }

    public Plot setNumberOfCopies(Variant numberOfCopies) {
        Disp.put(getClass(), dispatch, "NumberOfCopies", numberOfCopies);
        return this;
    }

    public Variant getQuietErrorMode() {
        return Disp.get(getClass(), dispatch, "QuietErrorMode");
    }

    public Plot setQuietErrorMode(Variant quietErrorMode) {
        Disp.put(getClass(), dispatch, "QuietErrorMode", quietErrorMode);
        return this;
    }

    public Variant displayPlotPreview(int preview) {
        Variant result = Disp.call(getClass(), dispatch, "DisplayPlotPreview", preview);
        return result;
    }

    public Variant plotToFile(Variant plotFile, Variant plotConfig) {
        Variant result = Disp.call(getClass(), dispatch, "PlotToFile", plotFile, plotConfig);
        return result;
    }

    public Variant plotToDevice(Variant plotConfig) {
        Variant result = Disp.call(getClass(), dispatch, "PlotToDevice", plotConfig);
        return result;
    }

    public Variant setLayoutsToPlot(Variant layoutList) {
        Variant result = Disp.call(getClass(), dispatch, "SetLayoutsToPlot", layoutList);
        return result;
    }

    public Variant startBatchMode(Variant entryCount) {
        Variant result = Disp.call(getClass(), dispatch, "StartBatchMode", entryCount);
        return result;
    }

}

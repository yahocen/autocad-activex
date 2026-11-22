// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadDatabasePreferences — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class DatabasePreferences extends ComObject {

    public DatabasePreferences(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getAllowLongSymbolNames() {
        return Disp.get(getClass(), dispatch, "AllowLongSymbolNames");
    }

    public DatabasePreferences setAllowLongSymbolNames(Variant allowLongSymbolNames) {
        Disp.put(getClass(), dispatch, "AllowLongSymbolNames", allowLongSymbolNames);
        return this;
    }

    public Variant getApplication() {
        return Disp.get(getClass(), dispatch, "Application");
    }

    public Variant getContourLinesPerSurface() {
        return Disp.get(getClass(), dispatch, "ContourLinesPerSurface");
    }

    public DatabasePreferences setContourLinesPerSurface(Variant contourLinesPerSurface) {
        Disp.put(getClass(), dispatch, "ContourLinesPerSurface", contourLinesPerSurface);
        return this;
    }

    public Variant getDisplaySilhouette() {
        return Disp.get(getClass(), dispatch, "DisplaySilhouette");
    }

    public DatabasePreferences setDisplaySilhouette(Variant displaySilhouette) {
        Disp.put(getClass(), dispatch, "DisplaySilhouette", displaySilhouette);
        return this;
    }

    public Variant getLineWeightDisplay() {
        return Disp.get(getClass(), dispatch, "LineWeightDisplay");
    }

    public DatabasePreferences setLineWeightDisplay(Variant lineWeightDisplay) {
        Disp.put(getClass(), dispatch, "LineWeightDisplay", lineWeightDisplay);
        return this;
    }

    public int getLineweight() {
        return Disp.get(getClass(), dispatch, "Lineweight").getInt();
    }

    public DatabasePreferences setLineweight(int lineweight) {
        Disp.put(getClass(), dispatch, "Lineweight", lineweight);
        return this;
    }

    public Variant getMaxActiveViewports() {
        return Disp.get(getClass(), dispatch, "MaxActiveViewports");
    }

    public DatabasePreferences setMaxActiveViewports(Variant maxActiveViewports) {
        Disp.put(getClass(), dispatch, "MaxActiveViewports", maxActiveViewports);
        return this;
    }

    public Variant getOLELaunch() {
        return Disp.get(getClass(), dispatch, "OLELaunch");
    }

    public DatabasePreferences setOLELaunch(Variant oLELaunch) {
        Disp.put(getClass(), dispatch, "OLELaunch", oLELaunch);
        return this;
    }

    public Variant getObjectSortByPSOutput() {
        return Disp.get(getClass(), dispatch, "ObjectSortByPSOutput");
    }

    public DatabasePreferences setObjectSortByPSOutput(Variant objectSortByPSOutput) {
        Disp.put(getClass(), dispatch, "ObjectSortByPSOutput", objectSortByPSOutput);
        return this;
    }

    public Variant getObjectSortByPlotting() {
        return Disp.get(getClass(), dispatch, "ObjectSortByPlotting");
    }

    public DatabasePreferences setObjectSortByPlotting(Variant objectSortByPlotting) {
        Disp.put(getClass(), dispatch, "ObjectSortByPlotting", objectSortByPlotting);
        return this;
    }

    public Variant getObjectSortByRedraws() {
        return Disp.get(getClass(), dispatch, "ObjectSortByRedraws");
    }

    public DatabasePreferences setObjectSortByRedraws(Variant objectSortByRedraws) {
        Disp.put(getClass(), dispatch, "ObjectSortByRedraws", objectSortByRedraws);
        return this;
    }

    public Variant getObjectSortByRegens() {
        return Disp.get(getClass(), dispatch, "ObjectSortByRegens");
    }

    public DatabasePreferences setObjectSortByRegens(Variant objectSortByRegens) {
        Disp.put(getClass(), dispatch, "ObjectSortByRegens", objectSortByRegens);
        return this;
    }

    public Variant getObjectSortBySelection() {
        return Disp.get(getClass(), dispatch, "ObjectSortBySelection");
    }

    public DatabasePreferences setObjectSortBySelection(Variant objectSortBySelection) {
        Disp.put(getClass(), dispatch, "ObjectSortBySelection", objectSortBySelection);
        return this;
    }

    public Variant getObjectSortBySnap() {
        return Disp.get(getClass(), dispatch, "ObjectSortBySnap");
    }

    public DatabasePreferences setObjectSortBySnap(Variant objectSortBySnap) {
        Disp.put(getClass(), dispatch, "ObjectSortBySnap", objectSortBySnap);
        return this;
    }

    public Variant getRenderSmoothness() {
        return Disp.get(getClass(), dispatch, "RenderSmoothness");
    }

    public DatabasePreferences setRenderSmoothness(Variant renderSmoothness) {
        Disp.put(getClass(), dispatch, "RenderSmoothness", renderSmoothness);
        return this;
    }

    public Variant getSegmentPerPolyline() {
        return Disp.get(getClass(), dispatch, "SegmentPerPolyline");
    }

    public DatabasePreferences setSegmentPerPolyline(Variant segmentPerPolyline) {
        Disp.put(getClass(), dispatch, "SegmentPerPolyline", segmentPerPolyline);
        return this;
    }

    public Variant getSolidFill() {
        return Disp.get(getClass(), dispatch, "SolidFill");
    }

    public DatabasePreferences setSolidFill(Variant solidFill) {
        Disp.put(getClass(), dispatch, "SolidFill", solidFill);
        return this;
    }

    public Variant getTextFrameDisplay() {
        return Disp.get(getClass(), dispatch, "TextFrameDisplay");
    }

    public DatabasePreferences setTextFrameDisplay(Variant textFrameDisplay) {
        Disp.put(getClass(), dispatch, "TextFrameDisplay", textFrameDisplay);
        return this;
    }

    public Variant getXRefEdit() {
        return Disp.get(getClass(), dispatch, "XRefEdit");
    }

    public DatabasePreferences setXRefEdit(Variant xRefEdit) {
        Disp.put(getClass(), dispatch, "XRefEdit", xRefEdit);
        return this;
    }

    public Variant getXRefLayerVisibility() {
        return Disp.get(getClass(), dispatch, "XRefLayerVisibility");
    }

    public DatabasePreferences setXRefLayerVisibility(Variant xRefLayerVisibility) {
        Disp.put(getClass(), dispatch, "XRefLayerVisibility", xRefLayerVisibility);
        return this;
    }

}

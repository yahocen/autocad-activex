// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesDrafting — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesDrafting extends ComObject {

    public PreferencesDrafting(Dispatch dispatch) {
        super(dispatch);
    }
    public int getAlignmentPointAcquisition() {
        return Disp.get(getClass(), dispatch, "AlignmentPointAcquisition").getInt();
    }

    public PreferencesDrafting setAlignmentPointAcquisition(int alignmentPointAcquisition) {
        Disp.put(getClass(), dispatch, "AlignmentPointAcquisition", alignmentPointAcquisition);
        return this;
    }

    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getAutoSnapAperture() {
        return Disp.get(getClass(), dispatch, "AutoSnapAperture");
    }

    public PreferencesDrafting setAutoSnapAperture(Variant autoSnapAperture) {
        Disp.put(getClass(), dispatch, "AutoSnapAperture", autoSnapAperture);
        return this;
    }

    public Variant getAutoSnapApertureSize() {
        return Disp.get(getClass(), dispatch, "AutoSnapApertureSize");
    }

    public PreferencesDrafting setAutoSnapApertureSize(Variant autoSnapApertureSize) {
        Disp.put(getClass(), dispatch, "AutoSnapApertureSize", autoSnapApertureSize);
        return this;
    }

    public Variant getAutoSnapMagnet() {
        return Disp.get(getClass(), dispatch, "AutoSnapMagnet");
    }

    public PreferencesDrafting setAutoSnapMagnet(Variant autoSnapMagnet) {
        Disp.put(getClass(), dispatch, "AutoSnapMagnet", autoSnapMagnet);
        return this;
    }

    public Variant getAutoSnapMarker() {
        return Disp.get(getClass(), dispatch, "AutoSnapMarker");
    }

    public PreferencesDrafting setAutoSnapMarker(Variant autoSnapMarker) {
        Disp.put(getClass(), dispatch, "AutoSnapMarker", autoSnapMarker);
        return this;
    }

    public int getAutoSnapMarkerColor() {
        return Disp.get(getClass(), dispatch, "AutoSnapMarkerColor").getInt();
    }

    public PreferencesDrafting setAutoSnapMarkerColor(int autoSnapMarkerColor) {
        Disp.put(getClass(), dispatch, "AutoSnapMarkerColor", autoSnapMarkerColor);
        return this;
    }

    public Variant getAutoSnapMarkerSize() {
        return Disp.get(getClass(), dispatch, "AutoSnapMarkerSize");
    }

    public PreferencesDrafting setAutoSnapMarkerSize(Variant autoSnapMarkerSize) {
        Disp.put(getClass(), dispatch, "AutoSnapMarkerSize", autoSnapMarkerSize);
        return this;
    }

    public Variant getAutoSnapTooltip() {
        return Disp.get(getClass(), dispatch, "AutoSnapTooltip");
    }

    public PreferencesDrafting setAutoSnapTooltip(Variant autoSnapTooltip) {
        Disp.put(getClass(), dispatch, "AutoSnapTooltip", autoSnapTooltip);
        return this;
    }

    public Variant getAutoTrackTooltip() {
        return Disp.get(getClass(), dispatch, "AutoTrackTooltip");
    }

    public PreferencesDrafting setAutoTrackTooltip(Variant autoTrackTooltip) {
        Disp.put(getClass(), dispatch, "AutoTrackTooltip", autoTrackTooltip);
        return this;
    }

    public Variant getFullScreenTrackingVector() {
        return Disp.get(getClass(), dispatch, "FullScreenTrackingVector");
    }

    public PreferencesDrafting setFullScreenTrackingVector(Variant fullScreenTrackingVector) {
        Disp.put(getClass(), dispatch, "FullScreenTrackingVector", fullScreenTrackingVector);
        return this;
    }

    public Variant getPolarTrackingVector() {
        return Disp.get(getClass(), dispatch, "PolarTrackingVector");
    }

    public PreferencesDrafting setPolarTrackingVector(Variant polarTrackingVector) {
        Disp.put(getClass(), dispatch, "PolarTrackingVector", polarTrackingVector);
        return this;
    }

}

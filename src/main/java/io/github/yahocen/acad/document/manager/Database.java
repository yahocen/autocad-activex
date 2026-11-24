// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadDatabase — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Database extends ComObject {

    public Database(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getBlocks() {
        return Disp.get(getClass(), dispatch, "Blocks").getDispatch();
    }

    public Dispatch getDictionaries() {
        return Disp.get(getClass(), dispatch, "Dictionaries").getDispatch();
    }

    public Dispatch getDimStyles() {
        return Disp.get(getClass(), dispatch, "DimStyles").getDispatch();
    }

    public Variant getElevationModelSpace() {
        return Disp.get(getClass(), dispatch, "ElevationModelSpace");
    }

    public Database setElevationModelSpace(Variant elevationModelSpace) {
        Disp.put(getClass(), dispatch, "ElevationModelSpace", elevationModelSpace);
        return this;
    }

    public Variant getElevationPaperSpace() {
        return Disp.get(getClass(), dispatch, "ElevationPaperSpace");
    }

    public Database setElevationPaperSpace(Variant elevationPaperSpace) {
        Disp.put(getClass(), dispatch, "ElevationPaperSpace", elevationPaperSpace);
        return this;
    }

    public Dispatch getGroups() {
        return Disp.get(getClass(), dispatch, "Groups").getDispatch();
    }

    public Dispatch getLayers() {
        return Disp.get(getClass(), dispatch, "Layers").getDispatch();
    }

    public Dispatch getLayouts() {
        return Disp.get(getClass(), dispatch, "Layouts").getDispatch();
    }

    public Variant getLimits() {
        return Disp.get(getClass(), dispatch, "Limits");
    }

    public Database setLimits(Variant limits) {
        Disp.put(getClass(), dispatch, "Limits", limits);
        return this;
    }

    public Dispatch getLinetypes() {
        return Disp.get(getClass(), dispatch, "Linetypes").getDispatch();
    }

    public Dispatch getMaterials() {
        return Disp.get(getClass(), dispatch, "Materials").getDispatch();
    }

    public Dispatch getModelSpace() {
        return Disp.get(getClass(), dispatch, "ModelSpace").getDispatch();
    }

    public Dispatch getPaperSpace() {
        return Disp.get(getClass(), dispatch, "PaperSpace").getDispatch();
    }

    public Dispatch getPlotConfigurations() {
        return Disp.get(getClass(), dispatch, "PlotConfigurations").getDispatch();
    }

    public Dispatch getPreferences() {
        return Disp.get(getClass(), dispatch, "Preferences").getDispatch();
    }

    public Dispatch getRegisteredApplications() {
        return Disp.get(getClass(), dispatch, "RegisteredApplications").getDispatch();
    }

    public Dispatch getSectionManager() {
        return Disp.get(getClass(), dispatch, "SectionManager").getDispatch();
    }

    public Dispatch getSummaryInfo() {
        return Disp.get(getClass(), dispatch, "SummaryInfo").getDispatch();
    }

    public Dispatch getTextStyles() {
        return Disp.get(getClass(), dispatch, "TextStyles").getDispatch();
    }

    public Dispatch getUserCoordinateSystems() {
        return Disp.get(getClass(), dispatch, "UserCoordinateSystems").getDispatch();
    }

    public Dispatch getViewports() {
        return Disp.get(getClass(), dispatch, "Viewports").getDispatch();
    }

    public Dispatch getViews() {
        return Disp.get(getClass(), dispatch, "Views").getDispatch();
    }

    public Variant copyObjects(Variant objects, Variant owner, Variant idPairs) {
        return Disp.call(getClass(), dispatch, "CopyObjects", objects, owner, idPairs);
    }

    public Variant handleToObject(Variant handle) {
        return Disp.call(getClass(), dispatch, "HandleToObject", handle);
    }

    public Variant objectIdToObject(int objectID) {
        return Disp.call(getClass(), dispatch, "ObjectIdToObject", objectID);
    }

}

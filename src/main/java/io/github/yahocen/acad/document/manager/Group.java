// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.util.EntityUtil;

/**
 * AcadGroup — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Group extends ComCollection<AcadEntity> {

    public Group(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected AcadEntity wrap(Dispatch dispatch) {
        return EntityUtil.parse(dispatch);
    }
    public Group setLayer(Variant layer) {
        setProperties("Layer", layer);
        return this;
    }

    public Group setLinetype(Variant linetype) {
        setProperties("Linetype", linetype);
        return this;
    }

    public Group setLinetypeScale(Variant linetypeScale) {
        setProperties("LinetypeScale", linetypeScale);
        return this;
    }

    public Group setLineweight(Variant lineweight) {
        setProperties("Lineweight", lineweight);
        return this;
    }

    public Group setMaterial(Variant material) {
        setProperties("Material", material);
        return this;
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public Group setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Group setPlotStyleName(Variant plotStyleName) {
        setProperties("PlotStyleName", plotStyleName);
        return this;
    }

    public Group setTrueColor(Variant trueColor) {
        setProperties("TrueColor", trueColor);
        return this;
    }

    public Group setVisible(Variant visible) {
        setProperties("Visible", visible);
        return this;
    }

    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public Group setcolor(Variant color) {
        setProperties("color", color);
        return this;
    }

    public Variant highlight(Variant highlightFlag) {
        Variant result = Disp.call(getClass(), dispatch, "Highlight", highlightFlag);
        return result;
    }

    public Variant appendItems(Variant objects) {
        Variant result = Disp.call(getClass(), dispatch, "AppendItems", objects);
        return result;
    }

    public Variant removeItems(Variant objects) {
        Variant result = Disp.call(getClass(), dispatch, "RemoveItems", objects);
        return result;
    }

    public Variant update() {
        Variant result = Disp.call(getClass(), dispatch, "Update");
        return result;
    }

}

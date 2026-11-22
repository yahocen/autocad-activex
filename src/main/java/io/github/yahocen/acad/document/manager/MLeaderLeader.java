// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadMLeaderLeader — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class MLeaderLeader extends ComObject {

    public MLeaderLeader(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getArrowheadBlock() {
        return Disp.get(getClass(), dispatch, "ArrowheadBlock");
    }

    public MLeaderLeader setArrowheadBlock(Variant arrowheadBlock) {
        Disp.put(getClass(), dispatch, "ArrowheadBlock", arrowheadBlock);
        return this;
    }

    public Variant getArrowheadSize() {
        return Disp.get(getClass(), dispatch, "ArrowheadSize");
    }

    public MLeaderLeader setArrowheadSize(Variant arrowheadSize) {
        Disp.put(getClass(), dispatch, "ArrowheadSize", arrowheadSize);
        return this;
    }

    public int getArrowheadType() {
        return Disp.get(getClass(), dispatch, "ArrowheadType").getInt();
    }

    public MLeaderLeader setArrowheadType(int arrowheadType) {
        Disp.put(getClass(), dispatch, "ArrowheadType", arrowheadType);
        return this;
    }

    public Dispatch getHyperlinks() {
        return Disp.get(getClass(), dispatch, "Hyperlinks").getDispatch();
    }

    public Variant getLayer() {
        return Disp.get(getClass(), dispatch, "Layer");
    }

    public int getLeaderLineColor() {
        return Disp.get(getClass(), dispatch, "LeaderLineColor").getInt();
    }

    public MLeaderLeader setLeaderLineColor(int leaderLineColor) {
        Disp.put(getClass(), dispatch, "LeaderLineColor", leaderLineColor);
        return this;
    }

    public int getLeaderLineWeight() {
        return Disp.get(getClass(), dispatch, "LeaderLineWeight").getInt();
    }

    public MLeaderLeader setLeaderLineWeight(int leaderLineWeight) {
        Disp.put(getClass(), dispatch, "LeaderLineWeight", leaderLineWeight);
        return this;
    }

    public int getLeaderLinetype() {
        return Disp.get(getClass(), dispatch, "LeaderLinetype").getInt();
    }

    public MLeaderLeader setLeaderLinetype(int leaderLinetype) {
        Disp.put(getClass(), dispatch, "LeaderLinetype", leaderLinetype);
        return this;
    }

    public int getLeaderType() {
        return Disp.get(getClass(), dispatch, "LeaderType").getInt();
    }

    public MLeaderLeader setLeaderType(int leaderType) {
        Disp.put(getClass(), dispatch, "LeaderType", leaderType);
        return this;
    }

    public Variant getLinetype() {
        return Disp.get(getClass(), dispatch, "Linetype");
    }

    public int getLinetypeScale() {
        return Disp.get(getClass(), dispatch, "LinetypeScale").getInt();
    }

    public int getLineweight() {
        return Disp.get(getClass(), dispatch, "Lineweight").getInt();
    }

    public Variant getObjectName() {
        return Disp.get(getClass(), dispatch, "ObjectName");
    }

    public Variant getPlotStyleName() {
        return Disp.get(getClass(), dispatch, "PlotStyleName");
    }

    public int getcolor() {
        return Disp.get(getClass(), dispatch, "color").getInt();
    }

    public MLeaderLeader setcolor(int color) {
        Disp.put(getClass(), dispatch, "color", color);
        return this;
    }

    public Variant onModified() {
        Variant result = Disp.call(getClass(), dispatch, "OnModified");
        return result;
    }

}

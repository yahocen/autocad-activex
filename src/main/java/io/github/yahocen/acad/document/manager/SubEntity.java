// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadSubEntity — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 * <p>
 * 子实体引用基类（ObjectName=AcDbEntity），由 {@code Utility.GetSubEntity} 返回，
 * 表示三维实体的子实体（面/边/顶点/节点）引用，非独立可绘图实体。
 * 具体类型见 {@link SubEntSolidFace} / {@link SubEntSolidEdge} /
 * {@link SubEntSolidVertex} / {@link SubEntSolidNode}。
 * </p>
 */
public class SubEntity extends ComObject {

    public SubEntity(Dispatch dispatch) {
        super(dispatch);
    }

    public Dispatch getHyperlinks() {
        return Disp.get(getClass(), dispatch, "Hyperlinks").getDispatch();
    }

    public Variant getLayer() {
        return Disp.get(getClass(), dispatch, "Layer");
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

    public SubEntity setcolor(int color) {
        Disp.put(getClass(), dispatch, "color", color);
        return this;
    }

    public Variant onModified() {
        Variant result = Disp.call(getClass(), dispatch, "OnModified");
        return result;
    }

}

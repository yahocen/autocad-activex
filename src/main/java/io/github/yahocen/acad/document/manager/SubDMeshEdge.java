package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadSubDMeshEdge — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class SubDMeshEdge extends ComObject {

    public SubDMeshEdge(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getCreaseLevel() {
        return Disp.get(getClass(), dispatch, "CreaseLevel");
    }

    public SubDMeshEdge setCreaseLevel(Variant creaseLevel) {
        Disp.put(getClass(), dispatch, "CreaseLevel", creaseLevel);
        return this;
    }

    public int getCreaseType() {
        return Disp.get(getClass(), dispatch, "CreaseType").getInt();
    }

    public SubDMeshEdge setCreaseType(int creaseType) {
        Disp.put(getClass(), dispatch, "CreaseType", creaseType);
        return this;
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

    public SubDMeshEdge setcolor(int color) {
        Disp.put(getClass(), dispatch, "color", color);
        return this;
    }

    public Variant onModified() {
        Variant result = Disp.call(getClass(), dispatch, "OnModified");
        return result;
    }

}

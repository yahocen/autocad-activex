package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadView — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class View extends AcadObject {

    public View(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getCategoryName() {
        return getProperties("CategoryName");
    }

    public View setCategoryName(Variant categoryName) {
        setProperties("CategoryName", categoryName);
        return this;
    }

    public Variant getCenter() {
        return getProperties("Center");
    }

    public View setCenter(Variant center) {
        setProperties("Center", center);
        return this;
    }

    public Variant getDirection() {
        return getProperties("Direction");
    }

    public View setDirection(Variant direction) {
        setProperties("Direction", direction);
        return this;
    }

    public Variant getHasVpAssociation() {
        return getProperties("HasVpAssociation");
    }

    public View setHasVpAssociation(Variant hasVpAssociation) {
        setProperties("HasVpAssociation", hasVpAssociation);
        return this;
    }

    public Variant getHeight() {
        return getProperties("Height");
    }

    public View setHeight(Variant height) {
        setProperties("Height", height);
        return this;
    }

    public Variant getLayerState() {
        return getProperties("LayerState");
    }

    public View setLayerState(Variant layerState) {
        setProperties("LayerState", layerState);
        return this;
    }

    public long getLayoutId() {
        return getProperties("LayoutId").getLong();
    }

    public View setLayoutId(long layoutId) {
        setProperties("LayoutId", layoutId);
        return this;
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public View setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getTarget() {
        return getProperties("Target");
    }

    public View setTarget(Variant target) {
        setProperties("Target", target);
        return this;
    }

    public Variant getWidth() {
        return getProperties("Width");
    }

    public View setWidth(Variant width) {
        setProperties("Width", width);
        return this;
    }

}

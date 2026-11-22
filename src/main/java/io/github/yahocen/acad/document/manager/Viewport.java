package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadViewport — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Viewport extends AcadObject {

    public Viewport(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getArcSmoothness() {
        return getProperties("ArcSmoothness");
    }

    public Viewport setArcSmoothness(Variant arcSmoothness) {
        setProperties("ArcSmoothness", arcSmoothness);
        return this;
    }

    public Variant getCenter() {
        return getProperties("Center");
    }

    public Viewport setCenter(Variant center) {
        setProperties("Center", center);
        return this;
    }

    public Variant getDirection() {
        return getProperties("Direction");
    }

    public Viewport setDirection(Variant direction) {
        setProperties("Direction", direction);
        return this;
    }

    public Variant getGridOn() {
        return getProperties("GridOn");
    }

    public Viewport setGridOn(Variant gridOn) {
        setProperties("GridOn", gridOn);
        return this;
    }

    public Variant getHeight() {
        return getProperties("Height");
    }

    public Viewport setHeight(Variant height) {
        setProperties("Height", height);
        return this;
    }

    public Variant getLowerLeftCorner() {
        return getProperties("LowerLeftCorner");
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public Viewport setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getOrthoOn() {
        return getProperties("OrthoOn");
    }

    public Viewport setOrthoOn(Variant orthoOn) {
        setProperties("OrthoOn", orthoOn);
        return this;
    }

    public Variant getSnapBasePoint() {
        return getProperties("SnapBasePoint");
    }

    public Viewport setSnapBasePoint(Variant snapBasePoint) {
        setProperties("SnapBasePoint", snapBasePoint);
        return this;
    }

    public Variant getSnapOn() {
        return getProperties("SnapOn");
    }

    public Viewport setSnapOn(Variant snapOn) {
        setProperties("SnapOn", snapOn);
        return this;
    }

    public double getSnapRotationAngle() {
        return getProperties("SnapRotationAngle").getDouble();
    }

    public Viewport setSnapRotationAngle(double snapRotationAngle) {
        setProperties("SnapRotationAngle", snapRotationAngle);
        return this;
    }

    public Variant getTarget() {
        return getProperties("Target");
    }

    public Viewport setTarget(Variant target) {
        setProperties("Target", target);
        return this;
    }

    public Variant getUCSIconAtOrigin() {
        return getProperties("UCSIconAtOrigin");
    }

    public Viewport setUCSIconAtOrigin(Variant uCSIconAtOrigin) {
        setProperties("UCSIconAtOrigin", uCSIconAtOrigin);
        return this;
    }

    public Variant getUCSIconOn() {
        return getProperties("UCSIconOn");
    }

    public Viewport setUCSIconOn(Variant uCSIconOn) {
        setProperties("UCSIconOn", uCSIconOn);
        return this;
    }

    public Variant getUpperRightCorner() {
        return getProperties("UpperRightCorner");
    }

    public Variant getWidth() {
        return getProperties("Width");
    }

    public Viewport setWidth(Variant width) {
        setProperties("Width", width);
        return this;
    }

    public Variant split(int numWins) {
        Variant result = Disp.call(getClass(), dispatch, "Split", numWins);
        return result;
    }

    public Variant getGridSpacing(Variant xSpacing, Variant ySpacing) {
        Variant result = Disp.call(getClass(), dispatch, "GetGridSpacing", xSpacing, ySpacing);
        return result;
    }

    public Variant setGridSpacing(Variant xSpacing, Variant ySpacing) {
        Variant result = Disp.call(getClass(), dispatch, "SetGridSpacing", xSpacing, ySpacing);
        return result;
    }

    public Variant getSnapSpacing(Variant xSpacing, Variant ySpacing) {
        Variant result = Disp.call(getClass(), dispatch, "GetSnapSpacing", xSpacing, ySpacing);
        return result;
    }

    public Variant setSnapSpacing(Variant xSpacing, Variant ySpacing) {
        Variant result = Disp.call(getClass(), dispatch, "SetSnapSpacing", xSpacing, ySpacing);
        return result;
    }

    public Variant setView(Variant view) {
        Variant result = Disp.call(getClass(), dispatch, "SetView", view);
        return result;
    }

}

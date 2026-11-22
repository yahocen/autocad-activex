// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Underlay - AutoCAD Underlay 实体
 */
public class Underlay extends AcadEntity {

    public Underlay(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAdjustForBackground() {
        return getProperties("AdjustForBackground");
    }

    public Underlay setAdjustForBackground(Variant adjustForBackground) {
        setProperties("AdjustForBackground", adjustForBackground);
        return this;
    }

    public Variant getClippingEnabled() {
        return getProperties("ClippingEnabled");
    }

    public Underlay setClippingEnabled(Variant clippingEnabled) {
        setProperties("ClippingEnabled", clippingEnabled);
        return this;
    }

    public Variant getContrast() {
        return getProperties("Contrast");
    }

    public Underlay setContrast(Variant contrast) {
        setProperties("Contrast", contrast);
        return this;
    }

    public Variant getFade() {
        return getProperties("Fade");
    }

    public Underlay setFade(Variant fade) {
        setProperties("Fade", fade);
        return this;
    }

    public Variant getFile() {
        return getProperties("File");
    }

    public Underlay setFile(Variant file) {
        setProperties("File", file);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Underlay setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public String getItemName() {
        return getProperties("ItemName").getString();
    }

    public Underlay setItemName(String itemName) {
        setProperties("ItemName", itemName);
        return this;
    }

    public Variant getMonochrome() {
        return getProperties("Monochrome");
    }

    public Underlay setMonochrome(Variant monochrome) {
        setProperties("Monochrome", monochrome);
        return this;
    }

    public Variant getPosition() {
        return getProperties("Position");
    }

    public Underlay setPosition(Variant position) {
        setProperties("Position", position);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public Underlay setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public Underlay setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public int getUnderlayLayerOverrideApplied() {
        return getProperties("UnderlayLayerOverrideApplied").getInt();
    }

    public Underlay setUnderlayLayerOverrideApplied(int underlayLayerOverrideApplied) {
        setProperties("UnderlayLayerOverrideApplied", underlayLayerOverrideApplied);
        return this;
    }

    public String getUnderlayName() {
        return getProperties("UnderlayName").getString();
    }

    public Underlay setUnderlayName(String underlayName) {
        setProperties("UnderlayName", underlayName);
        return this;
    }

    public Variant getUnderlayVisibility() {
        return getProperties("UnderlayVisibility");
    }

    public Underlay setUnderlayVisibility(Variant underlayVisibility) {
        setProperties("UnderlayVisibility", underlayVisibility);
        return this;
    }

    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    public Underlay setWidth(double width) {
        setProperties("Width", width);
        return this;
    }

    public Variant clipBoundary(Variant boundry) {
        return call("ClipBoundary", boundry);
    }

}
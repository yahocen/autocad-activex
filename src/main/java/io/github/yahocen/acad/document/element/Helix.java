// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Helix - AutoCAD Helix 实体
 */
@AcadEntityName("AcDbHelix")
public class Helix extends AcadEntity {

    public Helix(Dispatch dispatch) {
        super(dispatch);
    }

    public double getBaseRadius() {
        return getProperties("BaseRadius").getDouble();
    }

    public Helix setBaseRadius(double baseRadius) {
        setProperties("BaseRadius", baseRadius);
        return this;
    }

    public int getConstrain() {
        return getProperties("Constrain").getInt();
    }

    public Helix setConstrain(int constrain) {
        setProperties("Constrain", constrain);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Helix setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public Variant getPosition() {
        return getProperties("Position");
    }

    public Helix setPosition(Variant position) {
        setProperties("Position", position);
        return this;
    }

    public double getTopRadius() {
        return getProperties("TopRadius").getDouble();
    }

    public Helix setTopRadius(double topRadius) {
        setProperties("TopRadius", topRadius);
        return this;
    }

    public double getTotalLength() {
        return getProperties("TotalLength").getDouble();
    }

    public double getTurnHeight() {
        return getProperties("TurnHeight").getDouble();
    }

    public Helix setTurnHeight(double turnHeight) {
        setProperties("TurnHeight", turnHeight);
        return this;
    }

    public double getTurnSlope() {
        return getProperties("TurnSlope").getDouble();
    }

    public double getTurns() {
        return getProperties("Turns").getDouble();
    }

    public Helix setTurns(double turns) {
        setProperties("Turns", turns);
        return this;
    }

    public int getTwist() {
        return getProperties("Twist").getInt();
    }

    public Helix setTwist(int twist) {
        setProperties("Twist", twist);
        return this;
    }

}
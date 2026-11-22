// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Surface;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * RevolvedSurface - AutoCAD RevolvedSurface 实体
 */
@AcadEntityName("AcDbRevolvedSurface")
public class RevolvedSurface extends Surface {

    public RevolvedSurface(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAxisDirection() {
        return getProperties("AxisDirection");
    }

    public Variant getAxisPosition() {
        return getProperties("AxisPosition");
    }

    public RevolvedSurface setAxisPosition(Variant axisPosition) {
        setProperties("AxisPosition", axisPosition);
        return this;
    }

    public double getRevolutionAngle() {
        return getProperties("RevolutionAngle").getDouble();
    }

    public RevolvedSurface setRevolutionAngle(double revolutionAngle) {
        setProperties("RevolutionAngle", revolutionAngle);
        return this;
    }

}
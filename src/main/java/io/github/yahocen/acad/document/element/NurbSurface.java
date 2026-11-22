// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Surface;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * NurbSurface - AutoCAD NurbSurface 实体
 */
@AcadEntityName("AcDbNurbSurface")
public class NurbSurface extends Surface {

    public NurbSurface(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getCvHullDisplay() {
        return getProperties("CvHullDisplay");
    }

    public NurbSurface setCvHullDisplay(Variant cvHullDisplay) {
        setProperties("CvHullDisplay", cvHullDisplay);
        return this;
    }

}
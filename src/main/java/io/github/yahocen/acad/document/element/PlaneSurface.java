// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Surface;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * PlaneSurface - AutoCAD PlaneSurface 实体
 */
@AcadEntityName("AcDbPlaneSurface")
public class PlaneSurface extends Surface {

    public PlaneSurface(Dispatch dispatch) {
        super(dispatch);
    }

}
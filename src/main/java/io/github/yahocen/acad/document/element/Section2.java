// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Section;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Section2 - AutoCAD Section2 实体
 */
public class Section2 extends Section {

    public Section2(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getSectionPlaneOffset() {
        return getProperties("SectionPlaneOffset");
    }

    public Section2 setSectionPlaneOffset(Variant sectionPlaneOffset) {
        setProperties("SectionPlaneOffset", sectionPlaneOffset);
        return this;
    }

    public Variant getSliceDepth() {
        return getProperties("SliceDepth");
    }

    public Section2 setSliceDepth(Variant sliceDepth) {
        setProperties("SliceDepth", sliceDepth);
        return this;
    }

    public int getState2() {
        return getProperties("State2").getInt();
    }

    public Section2 setState2(int state2) {
        setProperties("State2", state2);
        return this;
    }

}
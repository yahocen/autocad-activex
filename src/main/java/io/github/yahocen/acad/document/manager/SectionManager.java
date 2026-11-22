// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;
import io.github.yahocen.acad.document.element.Section;

/**
 * AcadSectionManager — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class SectionManager extends ComCollection<Section> {

    public SectionManager(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Section wrap(Dispatch dispatch) {
        return new Section(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int getLiveSection() {
        return Disp.call(getClass(), dispatch, "GetLiveSection").getInt();
    }

    public Variant getUniqueSectionName(Variant pBaseName) {
        Variant result = Disp.call(getClass(), dispatch, "GetUniqueSectionName", pBaseName);
        return result;
    }

}

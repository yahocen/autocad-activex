package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadSectionSettings — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class SectionSettings extends AcadObject {

    public SectionSettings(Dispatch dispatch) {
        super(dispatch);
    }
    public int getCurrentSectionType() {
        return getProperties("CurrentSectionType").getInt();
    }

    public SectionSettings setCurrentSectionType(int currentSectionType) {
        setProperties("CurrentSectionType", currentSectionType);
        return this;
    }

    public int getSectionTypeSettings(int secType) {
        return Disp.call(getClass(), dispatch, "GetSectionTypeSettings", secType).getInt();
    }

}

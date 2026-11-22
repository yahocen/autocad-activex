package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Underlay;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * DwfUnderlay - AutoCAD DwfUnderlay 实体
 */
@AcadEntityName("AcDbDwfReference")
public class DwfUnderlay extends Underlay {

    public DwfUnderlay(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getDWFFormat() {
        return getProperties("DWFFormat");
    }

    public DwfUnderlay setDWFFormat(Variant dWFFormat) {
        setProperties("DWFFormat", dWFFormat);
        return this;
    }

}
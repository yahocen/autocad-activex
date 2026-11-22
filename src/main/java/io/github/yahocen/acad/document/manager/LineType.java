package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadLineType — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class LineType extends AcadObject {

    public LineType(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getDescription() {
        return getProperties("Description");
    }

    public LineType setDescription(Variant description) {
        setProperties("Description", description);
        return this;
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public LineType setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

}

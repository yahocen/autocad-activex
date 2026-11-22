package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadMaterial — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Material extends AcadObject {

    public Material(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getDescription() {
        return getProperties("Description");
    }

    public Material setDescription(Variant description) {
        setProperties("Description", description);
        return this;
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public Material setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

}

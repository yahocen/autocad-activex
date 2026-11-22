package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadGroups — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Groups extends ComCollection<Group> {

    public Groups(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Group wrap(Dispatch dispatch) {
        return new Group(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant name) {
        return Disp.call(getClass(), dispatch, "Add", name).getInt();
    }

}

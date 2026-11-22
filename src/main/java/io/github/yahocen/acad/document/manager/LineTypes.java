package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadLineTypes — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class LineTypes extends ComCollection<LineType> {

    public LineTypes(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected LineType wrap(Dispatch dispatch) {
        return new LineType(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant name) {
        return Disp.call(getClass(), dispatch, "Add", name).getInt();
    }

    public Variant load(Variant name, Variant fileName) {
        Variant result = Disp.call(getClass(), dispatch, "Load", name, fileName);
        return result;
    }

}

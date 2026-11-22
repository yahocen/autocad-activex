package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadSortentsTable — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class SortentsTable extends AcadObject {

    public SortentsTable(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant moveToBottom(Variant objects) {
        Variant result = Disp.call(getClass(), dispatch, "MoveToBottom", objects);
        return result;
    }

    public Variant moveToTop(Variant objects) {
        Variant result = Disp.call(getClass(), dispatch, "MoveToTop", objects);
        return result;
    }

    public Variant moveBelow(Variant objects, Variant target) {
        Variant result = Disp.call(getClass(), dispatch, "MoveBelow", objects, target);
        return result;
    }

    public Variant moveAbove(Variant objects, Variant target) {
        Variant result = Disp.call(getClass(), dispatch, "MoveAbove", objects, target);
        return result;
    }

    public Variant swapOrder(Variant object1, Variant object2) {
        Variant result = Disp.call(getClass(), dispatch, "SwapOrder", object1, object2);
        return result;
    }

    public int block() {
        return Disp.call(getClass(), dispatch, "Block").getInt();
    }

    public Variant getFullDrawOrder(Variant objects, Variant honorSortentsSysvar) {
        Variant result = Disp.call(getClass(), dispatch, "GetFullDrawOrder", objects, honorSortentsSysvar);
        return result;
    }

    public Variant getRelativeDrawOrder(Variant objects, Variant honorSortentsSysvar) {
        Variant result = Disp.call(getClass(), dispatch, "GetRelativeDrawOrder", objects, honorSortentsSysvar);
        return result;
    }

    public Variant setRelativeDrawOrder(Variant objects) {
        Variant result = Disp.call(getClass(), dispatch, "SetRelativeDrawOrder", objects);
        return result;
    }

}

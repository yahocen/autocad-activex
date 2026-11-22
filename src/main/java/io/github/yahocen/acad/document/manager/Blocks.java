package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;
import io.github.yahocen.acad.document.Block;

/**
 * AcadBlocks — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Blocks extends ComCollection<Block> {

    public Blocks(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Block wrap(Dispatch dispatch) {
        return new Block(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant insertionPoint, Variant name) {
        return Disp.call(getClass(), dispatch, "Add", insertionPoint, name).getInt();
    }

}

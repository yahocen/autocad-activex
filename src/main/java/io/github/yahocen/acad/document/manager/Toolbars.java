package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadToolbars — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Toolbars extends ComCollection<Toolbar> {

    public Toolbars(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Toolbar wrap(Dispatch dispatch) {
        return new Toolbar(dispatch);
    }
    public Variant getLargeButtons() {
        return getProperties("LargeButtons");
    }

    public Toolbars setLargeButtons(Variant largeButtons) {
        setProperties("LargeButtons", largeButtons);
        return this;
    }

    public MenuGroup getParent() {
        return new MenuGroup(getProperties("Parent").getDispatch());
    }

    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant toolbarName) {
        return Disp.call(getClass(), dispatch, "Add", toolbarName).getInt();
    }

}

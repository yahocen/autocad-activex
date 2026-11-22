// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadToolbar — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Toolbar extends ComCollection<ToolbarItem> {

    public Toolbar(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected ToolbarItem wrap(Dispatch dispatch) {
        return new ToolbarItem(dispatch);
    }
    public int getDockStatus() {
        return getProperties("DockStatus").getInt();
    }

    public Variant getFloatingRows() {
        return getProperties("FloatingRows");
    }

    public Toolbar setFloatingRows(Variant floatingRows) {
        setProperties("FloatingRows", floatingRows);
        return this;
    }

    public Variant getHeight() {
        return getProperties("Height");
    }

    public Variant getHelpString() {
        return getProperties("HelpString");
    }

    public Toolbar setHelpString(Variant helpString) {
        setProperties("HelpString", helpString);
        return this;
    }

    public Variant getLargeButtons() {
        return getProperties("LargeButtons");
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public Toolbar setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getParent() {
        return getProperties("Parent");
    }

    public Variant getTagString() {
        return getProperties("TagString");
    }

    public Variant getVisible() {
        return getProperties("Visible");
    }

    public Toolbar setVisible(Variant visible) {
        setProperties("Visible", visible);
        return this;
    }

    public Variant getWidth() {
        return getProperties("Width");
    }

    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public Variant getleft() {
        return getProperties("left");
    }

    public Toolbar setleft(Variant left) {
        setProperties("left", left);
        return this;
    }

    public Variant gettop() {
        return getProperties("top");
    }

    public Toolbar settop(Variant top) {
        setProperties("top", top);
        return this;
    }

    public int addToolbarButton(Variant index, Variant name, Variant helpString, Variant macro, Variant flyoutButton) {
        return Disp.call(getClass(), dispatch, "AddToolbarButton", index, name, helpString, macro, flyoutButton).getInt();
    }

    public int addSeparator(Variant index) {
        return Disp.call(getClass(), dispatch, "AddSeparator", index).getInt();
    }

    public Variant dock(int side) {
        Variant result = Disp.call(getClass(), dispatch, "Dock", side);
        return result;
    }

    public Variant float_(Variant top, Variant left, Variant numberFloatRows) {
        Variant result = Disp.call(getClass(), dispatch, "Float", top, left, numberFloatRows);
        return result;
    }

}

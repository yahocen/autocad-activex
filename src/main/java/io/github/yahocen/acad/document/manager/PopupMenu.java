// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadPopupMenu — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PopupMenu extends ComCollection<PopupMenuItem> {

    public PopupMenu(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected PopupMenuItem wrap(Dispatch dispatch) {
        return new PopupMenuItem(dispatch);
    }
    public Variant getName() {
        return getProperties("Name");
    }

    public PopupMenu setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getNameNoMnemonic() {
        return getProperties("NameNoMnemonic");
    }

    public Variant getOnMenuBar() {
        return getProperties("OnMenuBar");
    }

    public Variant getParent() {
        return getProperties("Parent");
    }

    public Variant getShortcutMenu() {
        return getProperties("ShortcutMenu");
    }

    public Variant getTagString() {
        return getProperties("TagString");
    }

    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int addMenuItem(Variant index, Variant label, Variant macro) {
        return Disp.call(getClass(), dispatch, "AddMenuItem", index, label, macro).getInt();
    }

    public int addSubMenu(Variant index, Variant label) {
        return Disp.call(getClass(), dispatch, "AddSubMenu", index, label).getInt();
    }

    public int addSeparator(Variant index) {
        return Disp.call(getClass(), dispatch, "AddSeparator", index).getInt();
    }

    public Variant insertInMenuBar(Variant index) {
        Variant result = Disp.call(getClass(), dispatch, "InsertInMenuBar", index);
        return result;
    }

    public Variant removeFromMenuBar() {
        Variant result = Disp.call(getClass(), dispatch, "RemoveFromMenuBar");
        return result;
    }

}

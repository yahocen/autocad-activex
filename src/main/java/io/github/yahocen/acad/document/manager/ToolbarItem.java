// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadToolbarItem — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class ToolbarItem extends ComObject {

    public ToolbarItem(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getCommandDisplayName() {
        return Disp.get(getClass(), dispatch, "CommandDisplayName");
    }

    public ToolbarItem setCommandDisplayName(Variant commandDisplayName) {
        Disp.put(getClass(), dispatch, "CommandDisplayName", commandDisplayName);
        return this;
    }

    public int getFlyout() {
        return Disp.get(getClass(), dispatch, "Flyout").getInt();
    }

    public Variant getHelpString() {
        return Disp.get(getClass(), dispatch, "HelpString");
    }

    public ToolbarItem setHelpString(Variant helpString) {
        Disp.put(getClass(), dispatch, "HelpString", helpString);
        return this;
    }

    public Variant getIndex() {
        return Disp.get(getClass(), dispatch, "Index");
    }

    public Variant getMacro() {
        return Disp.get(getClass(), dispatch, "Macro");
    }

    public ToolbarItem setMacro(Variant macro) {
        Disp.put(getClass(), dispatch, "Macro", macro);
        return this;
    }

    public Variant getName() {
        return Disp.get(getClass(), dispatch, "Name");
    }

    public ToolbarItem setName(Variant name) {
        Disp.put(getClass(), dispatch, "Name", name);
        return this;
    }

    public int getParent() {
        return Disp.get(getClass(), dispatch, "Parent").getInt();
    }

    public Variant getTagString() {
        return Disp.get(getClass(), dispatch, "TagString");
    }

    public ToolbarItem setTagString(Variant tagString) {
        Disp.put(getClass(), dispatch, "TagString", tagString);
        return this;
    }

    public int getType() {
        return Disp.get(getClass(), dispatch, "Type").getInt();
    }

    public Variant getBitmaps(Variant smallIconName, Variant largeIconName) {
        Variant result = Disp.call(getClass(), dispatch, "GetBitmaps", smallIconName, largeIconName);
        return result;
    }

    public Variant setBitmaps(Variant smallIconName, Variant largeIconName) {
        Variant result = Disp.call(getClass(), dispatch, "SetBitmaps", smallIconName, largeIconName);
        return result;
    }

    public Variant attachToolbarToFlyout(Variant menuGroupName, Variant toolbarName) {
        Variant result = Disp.call(getClass(), dispatch, "AttachToolbarToFlyout", menuGroupName, toolbarName);
        return result;
    }

    public Variant delete() {
        Variant result = Disp.call(getClass(), dispatch, "Delete");
        return result;
    }

}

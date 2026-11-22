// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferences — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Preferences extends ComObject {

    public Preferences(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public int getDisplay() {
        return Disp.get(getClass(), dispatch, "Display").getInt();
    }

    public int getDrafting() {
        return Disp.get(getClass(), dispatch, "Drafting").getInt();
    }

    public int getFiles() {
        return Disp.get(getClass(), dispatch, "Files").getInt();
    }

    public int getOpenSave() {
        return Disp.get(getClass(), dispatch, "OpenSave").getInt();
    }

    public int getOutput() {
        return Disp.get(getClass(), dispatch, "Output").getInt();
    }

    public int getProfiles() {
        return Disp.get(getClass(), dispatch, "Profiles").getInt();
    }

    public int getSelection() {
        return Disp.get(getClass(), dispatch, "Selection").getInt();
    }

    public int getSystem() {
        return Disp.get(getClass(), dispatch, "System").getInt();
    }

    public int getUser() {
        return Disp.get(getClass(), dispatch, "User").getInt();
    }

}

// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesProfiles — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesProfiles extends ComObject {

    public PreferencesProfiles(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getActiveProfile() {
        return Disp.get(getClass(), dispatch, "ActiveProfile");
    }

    public PreferencesProfiles setActiveProfile(Variant activeProfile) {
        Disp.put(getClass(), dispatch, "ActiveProfile", activeProfile);
        return this;
    }

    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant importProfile(Variant profileName, Variant regFile, Variant includePathInfo) {
        Variant result = Disp.call(getClass(), dispatch, "ImportProfile", profileName, regFile, includePathInfo);
        return result;
    }

    public Variant exportProfile(Variant profileName, Variant regFile) {
        Variant result = Disp.call(getClass(), dispatch, "ExportProfile", profileName, regFile);
        return result;
    }

    public Variant deleteProfile(Variant profileName) {
        Variant result = Disp.call(getClass(), dispatch, "DeleteProfile", profileName);
        return result;
    }

    public Variant resetProfile(Variant profile) {
        Variant result = Disp.call(getClass(), dispatch, "ResetProfile", profile);
        return result;
    }

    public Variant renameProfile(Variant origProfileName, Variant newProfileName) {
        Variant result = Disp.call(getClass(), dispatch, "RenameProfile", origProfileName, newProfileName);
        return result;
    }

    public Variant copyProfile(Variant oldProfileName, Variant newProfileName) {
        Variant result = Disp.call(getClass(), dispatch, "CopyProfile", oldProfileName, newProfileName);
        return result;
    }

    public Variant getAllProfileNames(Variant pNames) {
        Variant result = Disp.call(getClass(), dispatch, "GetAllProfileNames", pNames);
        return result;
    }

}

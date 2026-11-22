// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesOpenSave — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesOpenSave extends ComObject {

    public PreferencesOpenSave(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getAutoAudit() {
        return Disp.get(getClass(), dispatch, "AutoAudit");
    }

    public PreferencesOpenSave setAutoAudit(Variant autoAudit) {
        Disp.put(getClass(), dispatch, "AutoAudit", autoAudit);
        return this;
    }

    public Variant getAutoSaveInterval() {
        return Disp.get(getClass(), dispatch, "AutoSaveInterval");
    }

    public PreferencesOpenSave setAutoSaveInterval(Variant autoSaveInterval) {
        Disp.put(getClass(), dispatch, "AutoSaveInterval", autoSaveInterval);
        return this;
    }

    public Variant getCreateBackup() {
        return Disp.get(getClass(), dispatch, "CreateBackup");
    }

    public PreferencesOpenSave setCreateBackup(Variant createBackup) {
        Disp.put(getClass(), dispatch, "CreateBackup", createBackup);
        return this;
    }

    public int getDemandLoadARXApp() {
        return Disp.get(getClass(), dispatch, "DemandLoadARXApp").getInt();
    }

    public PreferencesOpenSave setDemandLoadARXApp(int demandLoadARXApp) {
        Disp.put(getClass(), dispatch, "DemandLoadARXApp", demandLoadARXApp);
        return this;
    }

    public Variant getFullCRCValidation() {
        return Disp.get(getClass(), dispatch, "FullCRCValidation");
    }

    public PreferencesOpenSave setFullCRCValidation(Variant fullCRCValidation) {
        Disp.put(getClass(), dispatch, "FullCRCValidation", fullCRCValidation);
        return this;
    }

    public Variant getIncrementalSavePercent() {
        return Disp.get(getClass(), dispatch, "IncrementalSavePercent");
    }

    public PreferencesOpenSave setIncrementalSavePercent(Variant incrementalSavePercent) {
        Disp.put(getClass(), dispatch, "IncrementalSavePercent", incrementalSavePercent);
        return this;
    }

    public Variant getLogFileOn() {
        return Disp.get(getClass(), dispatch, "LogFileOn");
    }

    public PreferencesOpenSave setLogFileOn(Variant logFileOn) {
        Disp.put(getClass(), dispatch, "LogFileOn", logFileOn);
        return this;
    }

    public Variant getMRUNumber() {
        return Disp.get(getClass(), dispatch, "MRUNumber");
    }

    public int getProxyImage() {
        return Disp.get(getClass(), dispatch, "ProxyImage").getInt();
    }

    public PreferencesOpenSave setProxyImage(int proxyImage) {
        Disp.put(getClass(), dispatch, "ProxyImage", proxyImage);
        return this;
    }

    public int getSaveAsType() {
        return Disp.get(getClass(), dispatch, "SaveAsType").getInt();
    }

    public PreferencesOpenSave setSaveAsType(int saveAsType) {
        Disp.put(getClass(), dispatch, "SaveAsType", saveAsType);
        return this;
    }

    public Variant getSavePreviewThumbnail() {
        return Disp.get(getClass(), dispatch, "SavePreviewThumbnail");
    }

    public PreferencesOpenSave setSavePreviewThumbnail(Variant savePreviewThumbnail) {
        Disp.put(getClass(), dispatch, "SavePreviewThumbnail", savePreviewThumbnail);
        return this;
    }

    public Variant getShowProxyDialogBox() {
        return Disp.get(getClass(), dispatch, "ShowProxyDialogBox");
    }

    public PreferencesOpenSave setShowProxyDialogBox(Variant showProxyDialogBox) {
        Disp.put(getClass(), dispatch, "ShowProxyDialogBox", showProxyDialogBox);
        return this;
    }

    public Variant getTempFileExtension() {
        return Disp.get(getClass(), dispatch, "TempFileExtension");
    }

    public PreferencesOpenSave setTempFileExtension(Variant tempFileExtension) {
        Disp.put(getClass(), dispatch, "TempFileExtension", tempFileExtension);
        return this;
    }

    public int getXrefDemandLoad() {
        return Disp.get(getClass(), dispatch, "XrefDemandLoad").getInt();
    }

    public PreferencesOpenSave setXrefDemandLoad(int xrefDemandLoad) {
        Disp.put(getClass(), dispatch, "XrefDemandLoad", xrefDemandLoad);
        return this;
    }

}

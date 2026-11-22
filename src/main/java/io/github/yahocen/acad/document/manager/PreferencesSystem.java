package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesSystem — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesSystem extends ComObject {

    public PreferencesSystem(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getBeepOnError() {
        return Disp.get(getClass(), dispatch, "BeepOnError");
    }

    public PreferencesSystem setBeepOnError(Variant beepOnError) {
        Disp.put(getClass(), dispatch, "BeepOnError", beepOnError);
        return this;
    }

    public Variant getDisplayOLEScale() {
        return Disp.get(getClass(), dispatch, "DisplayOLEScale");
    }

    public PreferencesSystem setDisplayOLEScale(Variant displayOLEScale) {
        Disp.put(getClass(), dispatch, "DisplayOLEScale", displayOLEScale);
        return this;
    }

    public Variant getEnableStartupDialog() {
        return Disp.get(getClass(), dispatch, "EnableStartupDialog");
    }

    public PreferencesSystem setEnableStartupDialog(Variant enableStartupDialog) {
        Disp.put(getClass(), dispatch, "EnableStartupDialog", enableStartupDialog);
        return this;
    }

    public Variant getLoadAcadLspInAllDocuments() {
        return Disp.get(getClass(), dispatch, "LoadAcadLspInAllDocuments");
    }

    public PreferencesSystem setLoadAcadLspInAllDocuments(Variant loadAcadLspInAllDocuments) {
        Disp.put(getClass(), dispatch, "LoadAcadLspInAllDocuments", loadAcadLspInAllDocuments);
        return this;
    }

    public Variant getShowWarningMessages() {
        return Disp.get(getClass(), dispatch, "ShowWarningMessages");
    }

    public PreferencesSystem setShowWarningMessages(Variant showWarningMessages) {
        Disp.put(getClass(), dispatch, "ShowWarningMessages", showWarningMessages);
        return this;
    }

    public Variant getSingleDocumentMode() {
        return Disp.get(getClass(), dispatch, "SingleDocumentMode");
    }

    public PreferencesSystem setSingleDocumentMode(Variant singleDocumentMode) {
        Disp.put(getClass(), dispatch, "SingleDocumentMode", singleDocumentMode);
        return this;
    }

    public Variant getStoreSQLIndex() {
        return Disp.get(getClass(), dispatch, "StoreSQLIndex");
    }

    public PreferencesSystem setStoreSQLIndex(Variant storeSQLIndex) {
        Disp.put(getClass(), dispatch, "StoreSQLIndex", storeSQLIndex);
        return this;
    }

    public Variant getTablesReadOnly() {
        return Disp.get(getClass(), dispatch, "TablesReadOnly");
    }

    public PreferencesSystem setTablesReadOnly(Variant tablesReadOnly) {
        Disp.put(getClass(), dispatch, "TablesReadOnly", tablesReadOnly);
        return this;
    }

}

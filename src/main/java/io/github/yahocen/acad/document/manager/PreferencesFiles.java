package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesFiles — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesFiles extends ComObject {

    public PreferencesFiles(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getActiveInvProject() {
        return Disp.get(getClass(), dispatch, "ActiveInvProject");
    }

    public PreferencesFiles setActiveInvProject(Variant activeInvProject) {
        Disp.put(getClass(), dispatch, "ActiveInvProject", activeInvProject);
        return this;
    }

    public Variant getAltFontFile() {
        return Disp.get(getClass(), dispatch, "AltFontFile");
    }

    public PreferencesFiles setAltFontFile(Variant altFontFile) {
        Disp.put(getClass(), dispatch, "AltFontFile", altFontFile);
        return this;
    }

    public Variant getAltTabletMenuFile() {
        return Disp.get(getClass(), dispatch, "AltTabletMenuFile");
    }

    public PreferencesFiles setAltTabletMenuFile(Variant altTabletMenuFile) {
        Disp.put(getClass(), dispatch, "AltTabletMenuFile", altTabletMenuFile);
        return this;
    }

    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getAutoSavePath() {
        return Disp.get(getClass(), dispatch, "AutoSavePath");
    }

    public PreferencesFiles setAutoSavePath(Variant autoSavePath) {
        Disp.put(getClass(), dispatch, "AutoSavePath", autoSavePath);
        return this;
    }

    public Variant getColorBookPath() {
        return Disp.get(getClass(), dispatch, "ColorBookPath");
    }

    public PreferencesFiles setColorBookPath(Variant colorBookPath) {
        Disp.put(getClass(), dispatch, "ColorBookPath", colorBookPath);
        return this;
    }

    public Variant getConfigFile() {
        return Disp.get(getClass(), dispatch, "ConfigFile");
    }

    public Variant getCustomDictionary() {
        return Disp.get(getClass(), dispatch, "CustomDictionary");
    }

    public PreferencesFiles setCustomDictionary(Variant customDictionary) {
        Disp.put(getClass(), dispatch, "CustomDictionary", customDictionary);
        return this;
    }

    public Variant getCustomIconPath() {
        return Disp.get(getClass(), dispatch, "CustomIconPath");
    }

    public PreferencesFiles setCustomIconPath(Variant customIconPath) {
        Disp.put(getClass(), dispatch, "CustomIconPath", customIconPath);
        return this;
    }

    public Variant getDefaultInternetURL() {
        return Disp.get(getClass(), dispatch, "DefaultInternetURL");
    }

    public PreferencesFiles setDefaultInternetURL(Variant defaultInternetURL) {
        Disp.put(getClass(), dispatch, "DefaultInternetURL", defaultInternetURL);
        return this;
    }

    public Variant getDriversPath() {
        return Disp.get(getClass(), dispatch, "DriversPath");
    }

    public PreferencesFiles setDriversPath(Variant driversPath) {
        Disp.put(getClass(), dispatch, "DriversPath", driversPath);
        return this;
    }

    public Variant getEnterpriseMenuFile() {
        return Disp.get(getClass(), dispatch, "EnterpriseMenuFile");
    }

    public PreferencesFiles setEnterpriseMenuFile(Variant enterpriseMenuFile) {
        Disp.put(getClass(), dispatch, "EnterpriseMenuFile", enterpriseMenuFile);
        return this;
    }

    public Variant getFontFileMap() {
        return Disp.get(getClass(), dispatch, "FontFileMap");
    }

    public PreferencesFiles setFontFileMap(Variant fontFileMap) {
        Disp.put(getClass(), dispatch, "FontFileMap", fontFileMap);
        return this;
    }

    public Variant getHelpFilePath() {
        return Disp.get(getClass(), dispatch, "HelpFilePath");
    }

    public PreferencesFiles setHelpFilePath(Variant helpFilePath) {
        Disp.put(getClass(), dispatch, "HelpFilePath", helpFilePath);
        return this;
    }

    public Variant getLicenseServer() {
        return Disp.get(getClass(), dispatch, "LicenseServer");
    }

    public Variant getLogFilePath() {
        return Disp.get(getClass(), dispatch, "LogFilePath");
    }

    public PreferencesFiles setLogFilePath(Variant logFilePath) {
        Disp.put(getClass(), dispatch, "LogFilePath", logFilePath);
        return this;
    }

    public Variant getMainDictionary() {
        return Disp.get(getClass(), dispatch, "MainDictionary");
    }

    public PreferencesFiles setMainDictionary(Variant mainDictionary) {
        Disp.put(getClass(), dispatch, "MainDictionary", mainDictionary);
        return this;
    }

    public Variant getMenuFile() {
        return Disp.get(getClass(), dispatch, "MenuFile");
    }

    public PreferencesFiles setMenuFile(Variant menuFile) {
        Disp.put(getClass(), dispatch, "MenuFile", menuFile);
        return this;
    }

    public Variant getObjectARXPath() {
        return Disp.get(getClass(), dispatch, "ObjectARXPath");
    }

    public PreferencesFiles setObjectARXPath(Variant objectARXPath) {
        Disp.put(getClass(), dispatch, "ObjectARXPath", objectARXPath);
        return this;
    }

    public Variant getPageSetupOverridesTemplateFile() {
        return Disp.get(getClass(), dispatch, "PageSetupOverridesTemplateFile");
    }

    public PreferencesFiles setPageSetupOverridesTemplateFile(Variant pageSetupOverridesTemplateFile) {
        Disp.put(getClass(), dispatch, "PageSetupOverridesTemplateFile", pageSetupOverridesTemplateFile);
        return this;
    }

    public Variant getPlotLogFilePath() {
        return Disp.get(getClass(), dispatch, "PlotLogFilePath");
    }

    public PreferencesFiles setPlotLogFilePath(Variant plotLogFilePath) {
        Disp.put(getClass(), dispatch, "PlotLogFilePath", plotLogFilePath);
        return this;
    }

    public Variant getPostScriptPrologFile() {
        return Disp.get(getClass(), dispatch, "PostScriptPrologFile");
    }

    public PreferencesFiles setPostScriptPrologFile(Variant postScriptPrologFile) {
        Disp.put(getClass(), dispatch, "PostScriptPrologFile", postScriptPrologFile);
        return this;
    }

    public Variant getPrintFile() {
        return Disp.get(getClass(), dispatch, "PrintFile");
    }

    public PreferencesFiles setPrintFile(Variant printFile) {
        Disp.put(getClass(), dispatch, "PrintFile", printFile);
        return this;
    }

    public Variant getPrintSpoolExecutable() {
        return Disp.get(getClass(), dispatch, "PrintSpoolExecutable");
    }

    public PreferencesFiles setPrintSpoolExecutable(Variant printSpoolExecutable) {
        Disp.put(getClass(), dispatch, "PrintSpoolExecutable", printSpoolExecutable);
        return this;
    }

    public Variant getPrintSpoolerPath() {
        return Disp.get(getClass(), dispatch, "PrintSpoolerPath");
    }

    public PreferencesFiles setPrintSpoolerPath(Variant printSpoolerPath) {
        Disp.put(getClass(), dispatch, "PrintSpoolerPath", printSpoolerPath);
        return this;
    }

    public Variant getPrinterConfigPath() {
        return Disp.get(getClass(), dispatch, "PrinterConfigPath");
    }

    public PreferencesFiles setPrinterConfigPath(Variant printerConfigPath) {
        Disp.put(getClass(), dispatch, "PrinterConfigPath", printerConfigPath);
        return this;
    }

    public Variant getPrinterDescPath() {
        return Disp.get(getClass(), dispatch, "PrinterDescPath");
    }

    public PreferencesFiles setPrinterDescPath(Variant printerDescPath) {
        Disp.put(getClass(), dispatch, "PrinterDescPath", printerDescPath);
        return this;
    }

    public Variant getPrinterStyleSheetPath() {
        return Disp.get(getClass(), dispatch, "PrinterStyleSheetPath");
    }

    public PreferencesFiles setPrinterStyleSheetPath(Variant printerStyleSheetPath) {
        Disp.put(getClass(), dispatch, "PrinterStyleSheetPath", printerStyleSheetPath);
        return this;
    }

    public Variant getQNewTemplateFile() {
        return Disp.get(getClass(), dispatch, "QNewTemplateFile");
    }

    public PreferencesFiles setQNewTemplateFile(Variant qNewTemplateFile) {
        Disp.put(getClass(), dispatch, "QNewTemplateFile", qNewTemplateFile);
        return this;
    }

    public Variant getSupportPath() {
        return Disp.get(getClass(), dispatch, "SupportPath");
    }

    public PreferencesFiles setSupportPath(Variant supportPath) {
        Disp.put(getClass(), dispatch, "SupportPath", supportPath);
        return this;
    }

    public Variant getTempFilePath() {
        return Disp.get(getClass(), dispatch, "TempFilePath");
    }

    public PreferencesFiles setTempFilePath(Variant tempFilePath) {
        Disp.put(getClass(), dispatch, "TempFilePath", tempFilePath);
        return this;
    }

    public Variant getTempXrefPath() {
        return Disp.get(getClass(), dispatch, "TempXrefPath");
    }

    public PreferencesFiles setTempXrefPath(Variant tempXrefPath) {
        Disp.put(getClass(), dispatch, "TempXrefPath", tempXrefPath);
        return this;
    }

    public Variant getTemplateDwgPath() {
        return Disp.get(getClass(), dispatch, "TemplateDwgPath");
    }

    public PreferencesFiles setTemplateDwgPath(Variant templateDwgPath) {
        Disp.put(getClass(), dispatch, "TemplateDwgPath", templateDwgPath);
        return this;
    }

    public Variant getTextEditor() {
        return Disp.get(getClass(), dispatch, "TextEditor");
    }

    public PreferencesFiles setTextEditor(Variant textEditor) {
        Disp.put(getClass(), dispatch, "TextEditor", textEditor);
        return this;
    }

    public Variant getTextureMapPath() {
        return Disp.get(getClass(), dispatch, "TextureMapPath");
    }

    public PreferencesFiles setTextureMapPath(Variant textureMapPath) {
        Disp.put(getClass(), dispatch, "TextureMapPath", textureMapPath);
        return this;
    }

    public Variant getToolPalettePath() {
        return Disp.get(getClass(), dispatch, "ToolPalettePath");
    }

    public PreferencesFiles setToolPalettePath(Variant toolPalettePath) {
        Disp.put(getClass(), dispatch, "ToolPalettePath", toolPalettePath);
        return this;
    }

    public Variant getWorkspacePath() {
        return Disp.get(getClass(), dispatch, "WorkspacePath");
    }

    public PreferencesFiles setWorkspacePath(Variant workspacePath) {
        Disp.put(getClass(), dispatch, "WorkspacePath", workspacePath);
        return this;
    }

    public Variant setProjectFilePath(Variant projectName, Variant projectFilePath) {
        Variant result = Disp.call(getClass(), dispatch, "SetProjectFilePath", projectName, projectFilePath);
        return result;
    }

    public Variant getProjectFilePath(Variant projectName) {
        Variant result = Disp.call(getClass(), dispatch, "GetProjectFilePath", projectName);
        return result;
    }

}

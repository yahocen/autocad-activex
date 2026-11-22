package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesUser — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesUser extends ComObject {

    public PreferencesUser(Dispatch dispatch) {
        super(dispatch);
    }
    public int getADCInsertUnitsDefaultSource() {
        return Disp.get(getClass(), dispatch, "ADCInsertUnitsDefaultSource").getInt();
    }

    public PreferencesUser setADCInsertUnitsDefaultSource(int aDCInsertUnitsDefaultSource) {
        Disp.put(getClass(), dispatch, "ADCInsertUnitsDefaultSource", aDCInsertUnitsDefaultSource);
        return this;
    }

    public int getADCInsertUnitsDefaultTarget() {
        return Disp.get(getClass(), dispatch, "ADCInsertUnitsDefaultTarget").getInt();
    }

    public PreferencesUser setADCInsertUnitsDefaultTarget(int aDCInsertUnitsDefaultTarget) {
        Disp.put(getClass(), dispatch, "ADCInsertUnitsDefaultTarget", aDCInsertUnitsDefaultTarget);
        return this;
    }

    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getHyperlinkDisplayCursor() {
        return Disp.get(getClass(), dispatch, "HyperlinkDisplayCursor");
    }

    public PreferencesUser setHyperlinkDisplayCursor(Variant hyperlinkDisplayCursor) {
        Disp.put(getClass(), dispatch, "HyperlinkDisplayCursor", hyperlinkDisplayCursor);
        return this;
    }

    public int getKeyboardAccelerator() {
        return Disp.get(getClass(), dispatch, "KeyboardAccelerator").getInt();
    }

    public PreferencesUser setKeyboardAccelerator(int keyboardAccelerator) {
        Disp.put(getClass(), dispatch, "KeyboardAccelerator", keyboardAccelerator);
        return this;
    }

    public int getKeyboardPriority() {
        return Disp.get(getClass(), dispatch, "KeyboardPriority").getInt();
    }

    public PreferencesUser setKeyboardPriority(int keyboardPriority) {
        Disp.put(getClass(), dispatch, "KeyboardPriority", keyboardPriority);
        return this;
    }

    public int getSCMCommandMode() {
        return Disp.get(getClass(), dispatch, "SCMCommandMode").getInt();
    }

    public PreferencesUser setSCMCommandMode(int sCMCommandMode) {
        Disp.put(getClass(), dispatch, "SCMCommandMode", sCMCommandMode);
        return this;
    }

    public int getSCMDefaultMode() {
        return Disp.get(getClass(), dispatch, "SCMDefaultMode").getInt();
    }

    public PreferencesUser setSCMDefaultMode(int sCMDefaultMode) {
        Disp.put(getClass(), dispatch, "SCMDefaultMode", sCMDefaultMode);
        return this;
    }

    public int getSCMEditMode() {
        return Disp.get(getClass(), dispatch, "SCMEditMode").getInt();
    }

    public PreferencesUser setSCMEditMode(int sCMEditMode) {
        Disp.put(getClass(), dispatch, "SCMEditMode", sCMEditMode);
        return this;
    }

    public Variant getSCMTimeMode() {
        return Disp.get(getClass(), dispatch, "SCMTimeMode");
    }

    public PreferencesUser setSCMTimeMode(Variant sCMTimeMode) {
        Disp.put(getClass(), dispatch, "SCMTimeMode", sCMTimeMode);
        return this;
    }

    public Variant getSCMTimeValue() {
        return Disp.get(getClass(), dispatch, "SCMTimeValue");
    }

    public PreferencesUser setSCMTimeValue(Variant sCMTimeValue) {
        Disp.put(getClass(), dispatch, "SCMTimeValue", sCMTimeValue);
        return this;
    }

    public Variant getShortCutMenuDisplay() {
        return Disp.get(getClass(), dispatch, "ShortCutMenuDisplay");
    }

    public PreferencesUser setShortCutMenuDisplay(Variant shortCutMenuDisplay) {
        Disp.put(getClass(), dispatch, "ShortCutMenuDisplay", shortCutMenuDisplay);
        return this;
    }

}

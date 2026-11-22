// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesDisplay — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesDisplay extends ComObject {

    public PreferencesDisplay(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public int getAutoTrackingVecColor() {
        return Disp.get(getClass(), dispatch, "AutoTrackingVecColor").getInt();
    }

    public PreferencesDisplay setAutoTrackingVecColor(int autoTrackingVecColor) {
        Disp.put(getClass(), dispatch, "AutoTrackingVecColor", autoTrackingVecColor);
        return this;
    }

    public Variant getCursorSize() {
        return Disp.get(getClass(), dispatch, "CursorSize");
    }

    public PreferencesDisplay setCursorSize(Variant cursorSize) {
        Disp.put(getClass(), dispatch, "CursorSize", cursorSize);
        return this;
    }

    public Variant getDisplayLayoutTabs() {
        return Disp.get(getClass(), dispatch, "DisplayLayoutTabs");
    }

    public PreferencesDisplay setDisplayLayoutTabs(Variant displayLayoutTabs) {
        Disp.put(getClass(), dispatch, "DisplayLayoutTabs", displayLayoutTabs);
        return this;
    }

    public Variant getDisplayScreenMenu() {
        return Disp.get(getClass(), dispatch, "DisplayScreenMenu");
    }

    public PreferencesDisplay setDisplayScreenMenu(Variant displayScreenMenu) {
        Disp.put(getClass(), dispatch, "DisplayScreenMenu", displayScreenMenu);
        return this;
    }

    public Variant getDisplayScrollBars() {
        return Disp.get(getClass(), dispatch, "DisplayScrollBars");
    }

    public PreferencesDisplay setDisplayScrollBars(Variant displayScrollBars) {
        Disp.put(getClass(), dispatch, "DisplayScrollBars", displayScrollBars);
        return this;
    }

    public Variant getDockedVisibleLines() {
        return Disp.get(getClass(), dispatch, "DockedVisibleLines");
    }

    public PreferencesDisplay setDockedVisibleLines(Variant dockedVisibleLines) {
        Disp.put(getClass(), dispatch, "DockedVisibleLines", dockedVisibleLines);
        return this;
    }

    public int getGraphicsWinLayoutBackgrndColor() {
        return Disp.get(getClass(), dispatch, "GraphicsWinLayoutBackgrndColor").getInt();
    }

    public PreferencesDisplay setGraphicsWinLayoutBackgrndColor(int graphicsWinLayoutBackgrndColor) {
        Disp.put(getClass(), dispatch, "GraphicsWinLayoutBackgrndColor", graphicsWinLayoutBackgrndColor);
        return this;
    }

    public int getGraphicsWinModelBackgrndColor() {
        return Disp.get(getClass(), dispatch, "GraphicsWinModelBackgrndColor").getInt();
    }

    public PreferencesDisplay setGraphicsWinModelBackgrndColor(int graphicsWinModelBackgrndColor) {
        Disp.put(getClass(), dispatch, "GraphicsWinModelBackgrndColor", graphicsWinModelBackgrndColor);
        return this;
    }

    public Variant getHistoryLines() {
        return Disp.get(getClass(), dispatch, "HistoryLines");
    }

    public PreferencesDisplay setHistoryLines(Variant historyLines) {
        Disp.put(getClass(), dispatch, "HistoryLines", historyLines);
        return this;
    }

    public Variant getImageFrameHighlight() {
        return Disp.get(getClass(), dispatch, "ImageFrameHighlight");
    }

    public PreferencesDisplay setImageFrameHighlight(Variant imageFrameHighlight) {
        Disp.put(getClass(), dispatch, "ImageFrameHighlight", imageFrameHighlight);
        return this;
    }

    public Variant getLayoutCreateViewport() {
        return Disp.get(getClass(), dispatch, "LayoutCreateViewport");
    }

    public PreferencesDisplay setLayoutCreateViewport(Variant layoutCreateViewport) {
        Disp.put(getClass(), dispatch, "LayoutCreateViewport", layoutCreateViewport);
        return this;
    }

    public int getLayoutCrosshairColor() {
        return Disp.get(getClass(), dispatch, "LayoutCrosshairColor").getInt();
    }

    public PreferencesDisplay setLayoutCrosshairColor(int layoutCrosshairColor) {
        Disp.put(getClass(), dispatch, "LayoutCrosshairColor", layoutCrosshairColor);
        return this;
    }

    public Variant getLayoutDisplayMargins() {
        return Disp.get(getClass(), dispatch, "LayoutDisplayMargins");
    }

    public PreferencesDisplay setLayoutDisplayMargins(Variant layoutDisplayMargins) {
        Disp.put(getClass(), dispatch, "LayoutDisplayMargins", layoutDisplayMargins);
        return this;
    }

    public Variant getLayoutDisplayPaper() {
        return Disp.get(getClass(), dispatch, "LayoutDisplayPaper");
    }

    public PreferencesDisplay setLayoutDisplayPaper(Variant layoutDisplayPaper) {
        Disp.put(getClass(), dispatch, "LayoutDisplayPaper", layoutDisplayPaper);
        return this;
    }

    public Variant getLayoutDisplayPaperShadow() {
        return Disp.get(getClass(), dispatch, "LayoutDisplayPaperShadow");
    }

    public PreferencesDisplay setLayoutDisplayPaperShadow(Variant layoutDisplayPaperShadow) {
        Disp.put(getClass(), dispatch, "LayoutDisplayPaperShadow", layoutDisplayPaperShadow);
        return this;
    }

    public Variant getLayoutShowPlotSetup() {
        return Disp.get(getClass(), dispatch, "LayoutShowPlotSetup");
    }

    public PreferencesDisplay setLayoutShowPlotSetup(Variant layoutShowPlotSetup) {
        Disp.put(getClass(), dispatch, "LayoutShowPlotSetup", layoutShowPlotSetup);
        return this;
    }

    public Variant getMaxAutoCADWindow() {
        return Disp.get(getClass(), dispatch, "MaxAutoCADWindow");
    }

    public PreferencesDisplay setMaxAutoCADWindow(Variant maxAutoCADWindow) {
        Disp.put(getClass(), dispatch, "MaxAutoCADWindow", maxAutoCADWindow);
        return this;
    }

    public int getModelCrosshairColor() {
        return Disp.get(getClass(), dispatch, "ModelCrosshairColor").getInt();
    }

    public PreferencesDisplay setModelCrosshairColor(int modelCrosshairColor) {
        Disp.put(getClass(), dispatch, "ModelCrosshairColor", modelCrosshairColor);
        return this;
    }

    public Variant getShowRasterImage() {
        return Disp.get(getClass(), dispatch, "ShowRasterImage");
    }

    public PreferencesDisplay setShowRasterImage(Variant showRasterImage) {
        Disp.put(getClass(), dispatch, "ShowRasterImage", showRasterImage);
        return this;
    }

    public Variant getTextFont() {
        return Disp.get(getClass(), dispatch, "TextFont");
    }

    public PreferencesDisplay setTextFont(Variant textFont) {
        Disp.put(getClass(), dispatch, "TextFont", textFont);
        return this;
    }

    public Variant getTextFontSize() {
        return Disp.get(getClass(), dispatch, "TextFontSize");
    }

    public PreferencesDisplay setTextFontSize(Variant textFontSize) {
        Disp.put(getClass(), dispatch, "TextFontSize", textFontSize);
        return this;
    }

    public int getTextFontStyle() {
        return Disp.get(getClass(), dispatch, "TextFontStyle").getInt();
    }

    public PreferencesDisplay setTextFontStyle(int textFontStyle) {
        Disp.put(getClass(), dispatch, "TextFontStyle", textFontStyle);
        return this;
    }

    public int getTextWinBackgrndColor() {
        return Disp.get(getClass(), dispatch, "TextWinBackgrndColor").getInt();
    }

    public PreferencesDisplay setTextWinBackgrndColor(int textWinBackgrndColor) {
        Disp.put(getClass(), dispatch, "TextWinBackgrndColor", textWinBackgrndColor);
        return this;
    }

    public int getTextWinTextColor() {
        return Disp.get(getClass(), dispatch, "TextWinTextColor").getInt();
    }

    public PreferencesDisplay setTextWinTextColor(int textWinTextColor) {
        Disp.put(getClass(), dispatch, "TextWinTextColor", textWinTextColor);
        return this;
    }

    public Variant getTrueColorImages() {
        return Disp.get(getClass(), dispatch, "TrueColorImages");
    }

    public PreferencesDisplay setTrueColorImages(Variant trueColorImages) {
        Disp.put(getClass(), dispatch, "TrueColorImages", trueColorImages);
        return this;
    }

    public Variant getXRefFadeIntensity() {
        return Disp.get(getClass(), dispatch, "XRefFadeIntensity");
    }

    public PreferencesDisplay setXRefFadeIntensity(Variant xRefFadeIntensity) {
        Disp.put(getClass(), dispatch, "XRefFadeIntensity", xRefFadeIntensity);
        return this;
    }

}

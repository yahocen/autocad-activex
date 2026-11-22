// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadTableStyle — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class TableStyle extends AcadObject {

    public TableStyle(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getBitFlags() {
        return getProperties("BitFlags");
    }

    public TableStyle setBitFlags(Variant bitFlags) {
        setProperties("BitFlags", bitFlags);
        return this;
    }

    public Variant getDescription() {
        return getProperties("Description");
    }

    public TableStyle setDescription(Variant description) {
        setProperties("Description", description);
        return this;
    }

    public int getFlowDirection() {
        return getProperties("FlowDirection").getInt();
    }

    public TableStyle setFlowDirection(int flowDirection) {
        setProperties("FlowDirection", flowDirection);
        return this;
    }

    public Variant getHeaderSuppressed() {
        return getProperties("HeaderSuppressed");
    }

    public TableStyle setHeaderSuppressed(Variant headerSuppressed) {
        setProperties("HeaderSuppressed", headerSuppressed);
        return this;
    }

    public Variant getHorzCellMargin() {
        return getProperties("HorzCellMargin");
    }

    public TableStyle setHorzCellMargin(Variant horzCellMargin) {
        setProperties("HorzCellMargin", horzCellMargin);
        return this;
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public TableStyle setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getNumCellStyles() {
        return getProperties("NumCellStyles");
    }

    public long getTemplateId() {
        return getProperties("TemplateId").getLong();
    }

    public TableStyle setTemplateId(long templateId) {
        setProperties("TemplateId", templateId);
        return this;
    }

    public Variant getTitleSuppressed() {
        return getProperties("TitleSuppressed");
    }

    public TableStyle setTitleSuppressed(Variant titleSuppressed) {
        setProperties("TitleSuppressed", titleSuppressed);
        return this;
    }

    public Variant getVertCellMargin() {
        return getProperties("VertCellMargin");
    }

    public TableStyle setVertCellMargin(Variant vertCellMargin) {
        setProperties("VertCellMargin", vertCellMargin);
        return this;
    }

    public Variant getTextStyle(int rowType) {
        Variant result = Disp.call(getClass(), dispatch, "GetTextStyle", rowType);
        return result;
    }

    public Variant setTextStyle(Variant rowTypes, Variant bstrName) {
        Variant result = Disp.call(getClass(), dispatch, "SetTextStyle", rowTypes, bstrName);
        return result;
    }

    public Variant getTextHeight(int rowType) {
        Variant result = Disp.call(getClass(), dispatch, "GetTextHeight", rowType);
        return result;
    }

    public Variant setTextHeight(Variant rowTypes, Variant textHeight) {
        Variant result = Disp.call(getClass(), dispatch, "SetTextHeight", rowTypes, textHeight);
        return result;
    }

    public int getAlignment(int rowType) {
        return Disp.call(getClass(), dispatch, "GetAlignment", rowType).getInt();
    }

    public Variant setAlignment(Variant rowTypes, int cellAlignment) {
        Variant result = Disp.call(getClass(), dispatch, "SetAlignment", rowTypes, cellAlignment);
        return result;
    }

    public int getColor(int rowType) {
        return Disp.call(getClass(), dispatch, "GetColor", rowType).getInt();
    }

    public Variant setColor(Variant rowTypes, Variant pColor) {
        Variant result = Disp.call(getClass(), dispatch, "SetColor", rowTypes, pColor);
        return result;
    }

    public int getBackgroundColor(int rowType) {
        return Disp.call(getClass(), dispatch, "GetBackgroundColor", rowType).getInt();
    }

    public Variant setBackgroundColor(Variant rowTypes, Variant pColor) {
        Variant result = Disp.call(getClass(), dispatch, "SetBackgroundColor", rowTypes, pColor);
        return result;
    }

    public Variant getBackgroundColorNone(int rowType) {
        Variant result = Disp.call(getClass(), dispatch, "GetBackgroundColorNone", rowType);
        return result;
    }

    public Variant setBackgroundColorNone(Variant rowTypes, Variant bValue) {
        Variant result = Disp.call(getClass(), dispatch, "SetBackgroundColorNone", rowTypes, bValue);
        return result;
    }

    public int getGridLineWeight(int gridLineType, int rowType) {
        return Disp.call(getClass(), dispatch, "GetGridLineWeight", gridLineType, rowType).getInt();
    }

    public Variant setGridLineWeight(Variant gridLineTypes, Variant rowTypes, int lineweight) {
        Variant result = Disp.call(getClass(), dispatch, "SetGridLineWeight", gridLineTypes, rowTypes, lineweight);
        return result;
    }

    public int getGridColor(int gridLineType, int rowType) {
        return Disp.call(getClass(), dispatch, "GetGridColor", gridLineType, rowType).getInt();
    }

    public Variant setGridColor(Variant gridLineTypes, Variant rowTypes, Variant pColor) {
        Variant result = Disp.call(getClass(), dispatch, "SetGridColor", gridLineTypes, rowTypes, pColor);
        return result;
    }

    public Variant getGridVisibility(int gridLineType, int rowType) {
        Variant result = Disp.call(getClass(), dispatch, "GetGridVisibility", gridLineType, rowType);
        return result;
    }

    public Variant setGridVisibility(Variant gridLineTypes, Variant rowTypes, Variant bValue) {
        Variant result = Disp.call(getClass(), dispatch, "SetGridVisibility", gridLineTypes, rowTypes, bValue);
        return result;
    }

    public Variant getDataType(int rowType, Variant pDataType, Variant pUnitType) {
        Variant result = Disp.call(getClass(), dispatch, "GetDataType", rowType, pDataType, pUnitType);
        return result;
    }

    public Variant setDataType(Variant rowTypes, int nDataType, int nUnitType) {
        Variant result = Disp.call(getClass(), dispatch, "SetDataType", rowTypes, nDataType, nUnitType);
        return result;
    }

    public Variant getFormat(int rowType) {
        Variant result = Disp.call(getClass(), dispatch, "GetFormat", rowType);
        return result;
    }

    public Variant setFormat(Variant rowTypes, Variant val) {
        Variant result = Disp.call(getClass(), dispatch, "SetFormat", rowTypes, val);
        return result;
    }

    public Variant createCellStyle(Variant bstrCellStyle) {
        Variant result = Disp.call(getClass(), dispatch, "CreateCellStyle", bstrCellStyle);
        return result;
    }

    public Variant createCellStyleFromStyle(Variant bstrCellStyle, Variant bstrSourceCellStyle) {
        Variant result = Disp.call(getClass(), dispatch, "CreateCellStyleFromStyle", bstrCellStyle, bstrSourceCellStyle);
        return result;
    }

    public Variant renameCellStyle(Variant bstrOldName, Variant bstrNewName) {
        Variant result = Disp.call(getClass(), dispatch, "RenameCellStyle", bstrOldName, bstrNewName);
        return result;
    }

    public Variant deleteCellStyle(Variant bstrCellStyle) {
        Variant result = Disp.call(getClass(), dispatch, "DeleteCellStyle", bstrCellStyle);
        return result;
    }

    public Variant getUniqueCellStyleName(Variant pszBaseName) {
        Variant result = Disp.call(getClass(), dispatch, "GetUniqueCellStyleName", pszBaseName);
        return result;
    }

    public Variant getIsCellStyleInUse(Variant pszCellStyle) {
        Variant result = Disp.call(getClass(), dispatch, "GetIsCellStyleInUse", pszCellStyle);
        return result;
    }

    public Variant getCellStyles(Variant cellStylesArray) {
        Variant result = Disp.call(getClass(), dispatch, "GetCellStyles", cellStylesArray);
        return result;
    }

    public int getTextStyleId(Variant bstrCellStyle) {
        return Disp.call(getClass(), dispatch, "GetTextStyleId", bstrCellStyle).getInt();
    }

    public Variant setTextStyleId(Variant bstrCellStyle, int val) {
        Variant result = Disp.call(getClass(), dispatch, "SetTextStyleId", bstrCellStyle, val);
        return result;
    }

    public Variant getTextHeight2(Variant bstrCellStyle) {
        Variant result = Disp.call(getClass(), dispatch, "GetTextHeight2", bstrCellStyle);
        return result;
    }

    public Variant setTextHeight2(Variant bstrCellStyle, Variant height) {
        Variant result = Disp.call(getClass(), dispatch, "SetTextHeight2", bstrCellStyle, height);
        return result;
    }

    public int getAlignment2(Variant bstrCellStyle) {
        return Disp.call(getClass(), dispatch, "GetAlignment2", bstrCellStyle).getInt();
    }

    public Variant setAlignment2(Variant bstrCellStyle, int cellAlignment) {
        Variant result = Disp.call(getClass(), dispatch, "SetAlignment2", bstrCellStyle, cellAlignment);
        return result;
    }

    public int getColor2(Variant bstrCellStyle) {
        return Disp.call(getClass(), dispatch, "GetColor2", bstrCellStyle).getInt();
    }

    public Variant setColor2(Variant bstrCellStyle, Variant color) {
        Variant result = Disp.call(getClass(), dispatch, "SetColor2", bstrCellStyle, color);
        return result;
    }

    public int getBackgroundColor2(Variant bstrCellStyle) {
        return Disp.call(getClass(), dispatch, "GetBackgroundColor2", bstrCellStyle).getInt();
    }

    public Variant setBackgroundColor2(Variant bstrCellStyle, Variant color) {
        Variant result = Disp.call(getClass(), dispatch, "SetBackgroundColor2", bstrCellStyle, color);
        return result;
    }

    public Variant getDataType2(Variant bstrCellStyle, Variant pDataType, Variant pUnitType) {
        Variant result = Disp.call(getClass(), dispatch, "GetDataType2", bstrCellStyle, pDataType, pUnitType);
        return result;
    }

    public Variant setDataType2(Variant bstrCellStyle, int nDataType, int nUnitType) {
        Variant result = Disp.call(getClass(), dispatch, "SetDataType2", bstrCellStyle, nDataType, nUnitType);
        return result;
    }

    public Variant getFormat2(Variant bstrCellStyle, Variant pbstrFormat) {
        Variant result = Disp.call(getClass(), dispatch, "GetFormat2", bstrCellStyle, pbstrFormat);
        return result;
    }

    public Variant setFormat2(Variant bstrCellStyle, Variant bstrFormat) {
        Variant result = Disp.call(getClass(), dispatch, "SetFormat2", bstrCellStyle, bstrFormat);
        return result;
    }

    public Variant getCellClass(Variant bstrCellStyle) {
        Variant result = Disp.call(getClass(), dispatch, "GetCellClass", bstrCellStyle);
        return result;
    }

    public Variant setCellClass(Variant bstrCellStyle, Variant cellClass) {
        Variant result = Disp.call(getClass(), dispatch, "SetCellClass", bstrCellStyle, cellClass);
        return result;
    }

    public Variant getRotation(Variant bstrCellStyle) {
        Variant result = Disp.call(getClass(), dispatch, "GetRotation", bstrCellStyle);
        return result;
    }

    public Variant setRotation(Variant bstrCellStyle, Variant rotation) {
        Variant result = Disp.call(getClass(), dispatch, "SetRotation", bstrCellStyle, rotation);
        return result;
    }

    public Variant getIsMergeAllEnabled(Variant bstrCellStyle) {
        Variant result = Disp.call(getClass(), dispatch, "GetIsMergeAllEnabled", bstrCellStyle);
        return result;
    }

    public Variant enableMergeAll(Variant bstrCellStyle, Variant bEnable) {
        Variant result = Disp.call(getClass(), dispatch, "EnableMergeAll", bstrCellStyle, bEnable);
        return result;
    }

    public int getGridLineWeight2(Variant bstrCellStyle, int gridLineType) {
        return Disp.call(getClass(), dispatch, "GetGridLineWeight2", bstrCellStyle, gridLineType).getInt();
    }

    public Variant setGridLineWeight2(Variant bstrCellStyle, int gridLineTypes, int lineweight) {
        Variant result = Disp.call(getClass(), dispatch, "SetGridLineWeight2", bstrCellStyle, gridLineTypes, lineweight);
        return result;
    }

    public int getGridColor2(Variant bstrCellStyle, int gridLineType) {
        return Disp.call(getClass(), dispatch, "GetGridColor2", bstrCellStyle, gridLineType).getInt();
    }

    public Variant setGridColor2(Variant bstrCellStyle, int gridLineTypes, Variant pColor) {
        Variant result = Disp.call(getClass(), dispatch, "SetGridColor2", bstrCellStyle, gridLineTypes, pColor);
        return result;
    }

    public Variant getGridVisibility2(Variant bstrCellStyle, int gridLineType) {
        Variant result = Disp.call(getClass(), dispatch, "GetGridVisibility2", bstrCellStyle, gridLineType);
        return result;
    }

    public Variant setGridVisibility2(Variant bstrCellStyle, int gridLineTypes, Variant bValue) {
        Variant result = Disp.call(getClass(), dispatch, "SetGridVisibility2", bstrCellStyle, gridLineTypes, bValue);
        return result;
    }

    public Variant setTemplateId(int val, int option) {
        Variant result = Disp.call(getClass(), dispatch, "SetTemplateId", val, option);
        return result;
    }

}

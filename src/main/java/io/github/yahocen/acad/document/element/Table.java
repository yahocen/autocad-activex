package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Table - AutoCAD Table 实体
 */
@AcadEntityName("AcDbTable")
public class Table extends AcadEntity {

    public Table(Dispatch dispatch) {
        super(dispatch);
    }

    public boolean getAllowManualHeights() {
        return getProperties("AllowManualHeights").getBoolean();
    }

    public Table setAllowManualHeights(boolean allowManualHeights) {
        setProperties("AllowManualHeights", allowManualHeights);
        return this;
    }

    public Variant getAllowManualPositions() {
        return getProperties("AllowManualPositions");
    }

    public Table setAllowManualPositions(Variant allowManualPositions) {
        setProperties("AllowManualPositions", allowManualPositions);
        return this;
    }

    public Variant getBreakSpacing() {
        return getProperties("BreakSpacing");
    }

    public Table setBreakSpacing(Variant breakSpacing) {
        setProperties("BreakSpacing", breakSpacing);
        return this;
    }

    public Variant getBreaksEnabled() {
        return getProperties("BreaksEnabled");
    }

    public Table setBreaksEnabled(Variant breaksEnabled) {
        setProperties("BreaksEnabled", breaksEnabled);
        return this;
    }

    public Table setColumnWidth(double columnWidth) {
        setProperties("ColumnWidth", columnWidth);
        return this;
    }

    public Variant getColumns() {
        return getProperties("Columns");
    }

    public Table setColumns(Variant columns) {
        setProperties("Columns", columns);
        return this;
    }

    public Variant getDirection() {
        return getProperties("Direction");
    }

    public Table setDirection(Variant direction) {
        setProperties("Direction", direction);
        return this;
    }

    public Table setEnableBreak(Variant enableBreak) {
        setProperties("EnableBreak", enableBreak);
        return this;
    }

    public int getFlowDirection() {
        return getProperties("FlowDirection").getInt();
    }

    public Table setFlowDirection(int flowDirection) {
        setProperties("FlowDirection", flowDirection);
        return this;
    }

    public Variant getHasSubSelection() {
        return getProperties("HasSubSelection");
    }

    public Variant getHeaderSuppressed() {
        return getProperties("HeaderSuppressed");
    }

    public Table setHeaderSuppressed(Variant headerSuppressed) {
        setProperties("HeaderSuppressed", headerSuppressed);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Table setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public Variant getHorzCellMargin() {
        return getProperties("HorzCellMargin");
    }

    public Table setHorzCellMargin(Variant horzCellMargin) {
        setProperties("HorzCellMargin", horzCellMargin);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public Table setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public double getMinimumTableHeight() {
        return getProperties("MinimumTableHeight").getDouble();
    }

    public double getMinimumTableWidth() {
        return getProperties("MinimumTableWidth").getDouble();
    }

    public Variant getRegenerateTableSuppressed() {
        return getProperties("RegenerateTableSuppressed");
    }

    public Table setRegenerateTableSuppressed(Variant regenerateTableSuppressed) {
        setProperties("RegenerateTableSuppressed", regenerateTableSuppressed);
        return this;
    }

    public Variant getRepeatBottomLabels() {
        return getProperties("RepeatBottomLabels");
    }

    public Table setRepeatBottomLabels(Variant repeatBottomLabels) {
        setProperties("RepeatBottomLabels", repeatBottomLabels);
        return this;
    }

    public Variant getRepeatTopLabels() {
        return getProperties("RepeatTopLabels");
    }

    public Table setRepeatTopLabels(Variant repeatTopLabels) {
        setProperties("RepeatTopLabels", repeatTopLabels);
        return this;
    }

    public Table setRowHeight(double rowHeight) {
        setProperties("RowHeight", rowHeight);
        return this;
    }

    public Variant getRows() {
        return getProperties("Rows");
    }

    public Table setRows(Variant rows) {
        setProperties("Rows", rows);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public Table setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public int getTableBreakFlowDirection() {
        return getProperties("TableBreakFlowDirection").getInt();
    }

    public Table setTableBreakFlowDirection(int tableBreakFlowDirection) {
        setProperties("TableBreakFlowDirection", tableBreakFlowDirection);
        return this;
    }

    public double getTableBreakHeight() {
        return getProperties("TableBreakHeight").getDouble();
    }

    public Table setTableBreakHeight(double tableBreakHeight) {
        setProperties("TableBreakHeight", tableBreakHeight);
        return this;
    }

    public Variant getTableStyleOverrides() {
        return getProperties("TableStyleOverrides");
    }

    public Variant getTitleSuppressed() {
        return getProperties("TitleSuppressed");
    }

    public Table setTitleSuppressed(Variant titleSuppressed) {
        setProperties("TitleSuppressed", titleSuppressed);
        return this;
    }

    public Variant getVertCellMargin() {
        return getProperties("VertCellMargin");
    }

    public Table setVertCellMargin(Variant vertCellMargin) {
        setProperties("VertCellMargin", vertCellMargin);
        return this;
    }

    public double getWidth() {
        return getProperties("Width").getDouble();
    }

    public Table setWidth(double width) {
        setProperties("Width", width);
        return this;
    }

    public Variant getColumnWidth(Variant col) {
        return call("GetColumnWidth", col);
    }

    public Variant setColumnWidth(Variant col, Variant width) {
        return call("SetColumnWidth", col, width);
    }

    public Variant getRowHeight(Variant row) {
        return call("GetRowHeight", row);
    }

    public Variant setRowHeight(Variant row, Variant height) {
        return call("SetRowHeight", row, height);
    }

    public Variant getMinimumColumnWidth(Variant col) {
        return call("GetMinimumColumnWidth", col);
    }

    public Variant getMinimumRowHeight(Variant row) {
        return call("GetMinimumRowHeight", row);
    }

    public int getAlignment(int rowType) {
        return call("GetAlignment", rowType).getInt();
    }

    public Variant setAlignment(Variant rowTypes, int cellAlignment) {
        return call("SetAlignment", rowTypes, cellAlignment);
    }

    public Variant getBackgroundColorNone(int rowType) {
        return call("GetBackgroundColorNone", rowType);
    }

    public Variant setBackgroundColorNone(Variant rowTypes, Variant bValue) {
        return call("SetBackgroundColorNone", rowTypes, bValue);
    }

    public int getBackgroundColor(int rowType) {
        return call("GetBackgroundColor", rowType).getInt();
    }

    public Variant setBackgroundColor(Variant rowTypes, int pColor) {
        return call("SetBackgroundColor", rowTypes, pColor);
    }

    public int getContentColor(int rowType) {
        return call("GetContentColor", rowType).getInt();
    }

    public Variant setContentColor(Variant rowTypes, int pColor) {
        return call("SetContentColor", rowTypes, pColor);
    }

    public Variant getTextStyle(int rowType) {
        return call("GetTextStyle", rowType);
    }

    public Variant setTextStyle(Variant rowTypes, Variant bstrName) {
        return call("SetTextStyle", rowTypes, bstrName);
    }

    public Variant getTextHeight(int rowType) {
        return call("GetTextHeight", rowType);
    }

    public Variant setTextHeight(Variant rowTypes, Variant textHeight) {
        return call("SetTextHeight", rowTypes, textHeight);
    }

    public int getGridLineWeight(int gridLineType, int rowType) {
        return call("GetGridLineWeight", gridLineType, rowType).getInt();
    }

    public Variant setGridLineWeight(Variant gridLineTypes, Variant rowTypes, int lineweight) {
        return call("SetGridLineWeight", gridLineTypes, rowTypes, lineweight);
    }

    public int getGridColor(int gridLineType, int rowType) {
        return call("GetGridColor", gridLineType, rowType).getInt();
    }

    public Variant setGridColor(Variant gridLineTypes, Variant rowTypes, int pColor) {
        return call("SetGridColor", gridLineTypes, rowTypes, pColor);
    }

    public Variant getGridVisibility(int gridLineType, int rowType) {
        return call("GetGridVisibility", gridLineType, rowType);
    }

    public Variant setGridVisibility(Variant gridLineTypes, Variant rowTypes, Variant bValue) {
        return call("SetGridVisibility", gridLineTypes, rowTypes, bValue);
    }

    public Variant clearTableStyleOverrides(Variant flag) {
        return call("ClearTableStyleOverrides", flag);
    }

    public int getCellType(Variant row, Variant col) {
        return call("GetCellType", row, col).getInt();
    }

    public Variant setCellType(Variant row, Variant col, int cellType) {
        return call("SetCellType", row, col, cellType);
    }

    public Variant getCellExtents(Variant row, Variant col, Variant bOuterCell) {
        return call("GetCellExtents", row, col, bOuterCell);
    }

    public Variant getAttachmentPoint(Variant row, Variant col) {
        return call("GetAttachmentPoint", row, col);
    }

    public int getCellAlignment(Variant row, Variant col) {
        return call("GetCellAlignment", row, col).getInt();
    }

    public Variant setCellAlignment(Variant row, Variant col, int cellAlignment) {
        return call("SetCellAlignment", row, col, cellAlignment);
    }

    public Variant getCellBackgroundColorNone(Variant row, Variant col) {
        return call("GetCellBackgroundColorNone", row, col);
    }

    public Variant setCellBackgroundColorNone(Variant row, Variant col, Variant bValue) {
        return call("SetCellBackgroundColorNone", row, col, bValue);
    }

    public int getCellBackgroundColor(Variant row, Variant col) {
        return call("GetCellBackgroundColor", row, col).getInt();
    }

    public Variant setCellBackgroundColor(Variant row, Variant col, int pColor) {
        return call("SetCellBackgroundColor", row, col, pColor);
    }

    public int getCellContentColor(Variant row, Variant col) {
        return call("GetCellContentColor", row, col).getInt();
    }

    public Variant setCellContentColor(Variant row, Variant col, int pColor) {
        return call("SetCellContentColor", row, col, pColor);
    }

    public Variant getCellStyleOverrides(Variant row, Variant col) {
        return call("GetCellStyleOverrides", row, col);
    }

    public Variant deleteCellContent(Variant row, Variant col) {
        return call("DeleteCellContent", row, col);
    }

    public int getRowType(Variant row) {
        return call("GetRowType", row).getInt();
    }

    public Variant getText(Variant row, Variant col) {
        return call("GetText", row, col);
    }

    public Variant setText(Variant row, Variant col, Variant pStr) {
        return call("SetText", row, col, pStr);
    }

    public Variant getCellTextStyle(Variant row, Variant col) {
        return call("GetCellTextStyle", row, col);
    }

    public Variant setCellTextStyle(Variant row, Variant col, Variant bstrName) {
        return call("SetCellTextStyle", row, col, bstrName);
    }

    public Variant getCellTextHeight(Variant row, Variant col) {
        return call("GetCellTextHeight", row, col);
    }

    public Variant setCellTextHeight(Variant row, Variant col, Variant textHeight) {
        return call("SetCellTextHeight", row, col, textHeight);
    }

    public int getTextRotation(Variant row, Variant col) {
        return call("GetTextRotation", row, col).getInt();
    }

    public Variant setTextRotation(Variant row, Variant col, int textRotation) {
        return call("SetTextRotation", row, col, textRotation);
    }

    public Variant getAutoScale(Variant row, Variant col) {
        return call("GetAutoScale", row, col);
    }

    public Variant setAutoScale(Variant row, Variant col, Variant bValue) {
        return call("SetAutoScale", row, col, bValue);
    }

    public int getBlockTableRecordId(Variant row, Variant col) {
        return call("GetBlockTableRecordId", row, col).getInt();
    }

    public Variant setBlockTableRecordId(Variant row, Variant col, int blkId, Variant bAutoFit) {
        return call("SetBlockTableRecordId", row, col, blkId, bAutoFit);
    }

    public Variant getBlockScale(Variant row, Variant col) {
        return call("GetBlockScale", row, col);
    }

    public Variant setBlockScale(Variant row, Variant col, Variant blkScale) {
        return call("SetBlockScale", row, col, blkScale);
    }

    public Variant getBlockRotation(Variant row, Variant col) {
        return call("GetBlockRotation", row, col);
    }

    public Variant setBlockRotation(Variant row, Variant col, Variant blkRotation) {
        return call("SetBlockRotation", row, col, blkRotation);
    }

    public Variant getBlockAttributeValue(Variant row, Variant col, int attdefId) {
        return call("GetBlockAttributeValue", row, col, attdefId);
    }

    public Variant setBlockAttributeValue(Variant row, Variant col, int attdefId, Variant bstrValue) {
        return call("SetBlockAttributeValue", row, col, attdefId, bstrValue);
    }

    public int getCellGridLineWeight(Variant row, Variant col, int edge) {
        return call("GetCellGridLineWeight", row, col, edge).getInt();
    }

    public Variant setCellGridLineWeight(Variant row, Variant col, Variant edges, int lineweight) {
        return call("SetCellGridLineWeight", row, col, edges, lineweight);
    }

    public int getCellGridColor(Variant row, Variant col, int edge) {
        return call("GetCellGridColor", row, col, edge).getInt();
    }

    public Variant setCellGridColor(Variant row, Variant col, Variant edges, int pColor) {
        return call("SetCellGridColor", row, col, edges, pColor);
    }

    public Variant getCellGridVisibility(Variant row, Variant col, int edge) {
        return call("GetCellGridVisibility", row, col, edge);
    }

    public Variant setCellGridVisibility(Variant row, Variant col, Variant edges, Variant bValue) {
        return call("SetCellGridVisibility", row, col, edges, bValue);
    }

    public Variant insertColumns(Variant col, Variant width, Variant cols) {
        return call("InsertColumns", col, width, cols);
    }

    public Variant deleteColumns(Variant col, Variant cols) {
        return call("DeleteColumns", col, cols);
    }

    public Variant insertRows(Variant row, Variant height, Variant rows) {
        return call("InsertRows", row, height, rows);
    }

    public Variant deleteRows(Variant row, Variant rows) {
        return call("DeleteRows", row, rows);
    }

    public Variant mergeCells(Variant minRow, Variant maxRow, Variant minCol, Variant maxCol) {
        return call("MergeCells", minRow, maxRow, minCol, maxCol);
    }

    public Variant unmergeCells(Variant minRow, Variant maxRow, Variant minCol, Variant maxCol) {
        return call("UnmergeCells", minRow, maxRow, minCol, maxCol);
    }

    public Variant isMergedCell(Variant row, Variant col, Variant minRow, Variant maxRow, Variant minCol, Variant maxCol) {
        return call("IsMergedCell", row, col, minRow, maxRow, minCol, maxCol);
    }

    public int getFieldId(Variant row, Variant col) {
        return call("GetFieldId", row, col).getInt();
    }

    public Variant setFieldId(Variant row, Variant col, int fieldId) {
        return call("SetFieldId", row, col, fieldId);
    }

    public Variant generateLayout() {
        return call("GenerateLayout");
    }

    public Variant recomputeTableBlock(Variant bForceUpdate) {
        return call("RecomputeTableBlock", bForceUpdate);
    }

    public Variant hitTest(Variant wpt, Variant wviewVec, Variant resultRowIndex, Variant resultColumnIndex) {
        return call("HitTest", wpt, wviewVec, resultRowIndex, resultColumnIndex);
    }

    public Variant select(Variant wpt, Variant wvwVec, Variant wvwxVec, Variant wxaper, Variant wyaper, Variant allowOutside, Variant resultRowIndex, Variant resultColumnIndex) {
        return call("Select", wpt, wvwVec, wvwxVec, wxaper, wyaper, allowOutside, resultRowIndex, resultColumnIndex);
    }

    public Variant selectSubRegion(Variant wpt1, Variant wpt2, Variant wvwVec, Variant wvwxVec, int seltype, Variant bIncludeCurrentSelection, Variant rowMin, Variant rowMax, Variant colMin, Variant colMax) {
        return call("SelectSubRegion", wpt1, wpt2, wvwVec, wvwxVec, seltype, bIncludeCurrentSelection, rowMin, rowMax, colMin, colMax);
    }

    public Variant reselectSubRegion() {
        return call("ReselectSubRegion");
    }

    public Variant getSubSelection(Variant rowMin, Variant rowMax, Variant colMin, Variant colMax) {
        return call("GetSubSelection", rowMin, rowMax, colMin, colMax);
    }

    public Variant setSubSelection(Variant rowMin, Variant rowMax, Variant colMin, Variant colMax) {
        return call("SetSubSelection", rowMin, rowMax, colMin, colMax);
    }

    public Variant clearSubSelection() {
        return call("ClearSubSelection");
    }

    public Variant getDataType(int rowType, int pDataType, int pUnitType) {
        return call("GetDataType", rowType, pDataType, pUnitType);
    }

    public Variant setDataType(Variant rowTypes, int dataType, int unitType) {
        return call("SetDataType", rowTypes, dataType, unitType);
    }

    public Variant getFormat(int rowType) {
        return call("GetFormat", rowType);
    }

    public Variant setFormat(Variant rowTypes, Variant pFormat) {
        return call("SetFormat", rowTypes, pFormat);
    }

    public Variant formatValue(Variant row, Variant col, int nOption, Variant pVal) {
        return call("FormatValue", row, col, nOption, pVal);
    }

    public Variant getCellDataType(Variant row, Variant col, int pDataType, int pUnitType) {
        return call("GetCellDataType", row, col, pDataType, pUnitType);
    }

    public Variant setCellDataType(Variant row, Variant col, int dataType, int unitType) {
        return call("SetCellDataType", row, col, dataType, unitType);
    }

    public Variant getCellFormat(Variant row, Variant col) {
        return call("GetCellFormat", row, col);
    }

    public Variant setCellFormat(Variant row, Variant col, Variant pFormat) {
        return call("SetCellFormat", row, col, pFormat);
    }

    public Variant getCellValue(Variant row, Variant col) {
        return call("GetCellValue", row, col);
    }

    public Variant setCellValue(Variant row, Variant col, Variant val) {
        return call("SetCellValue", row, col, val);
    }

    public Variant setCellValueFromText(Variant row, Variant col, Variant val, int nOption) {
        return call("SetCellValueFromText", row, col, val, nOption);
    }

    public Variant resetCellValue(Variant row, Variant col) {
        return call("ResetCellValue", row, col);
    }

    public Variant isEmpty(Variant nRow, Variant nCol) {
        return call("IsEmpty", nRow, nCol);
    }

    public Variant createContent(Variant nRow, Variant nCol, Variant nIndex) {
        return call("CreateContent", nRow, nCol, nIndex);
    }

    public Variant moveContent(Variant nRow, Variant nCol, Variant nFromIndex, Variant nToIndex) {
        return call("MoveContent", nRow, nCol, nFromIndex, nToIndex);
    }

    public Variant deleteContent(Variant nRow, Variant nCol) {
        return call("DeleteContent", nRow, nCol);
    }

    public Variant getValue(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetValue", nRow, nCol, nContent);
    }

    public Variant setValue(Variant nRow, Variant nCol, Variant nContent, Variant acValue) {
        return call("SetValue", nRow, nCol, nContent, acValue);
    }

    public Variant setValueFromText(Variant nRow, Variant nCol, Variant nContent, Variant szText, int nOption) {
        return call("SetValueFromText", nRow, nCol, nContent, szText, nOption);
    }

    public Variant getDataFormat(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetDataFormat", nRow, nCol, nContent);
    }

    public Variant setDataFormat(Variant nRow, Variant nCol, Variant nContent, Variant szFormat) {
        return call("SetDataFormat", nRow, nCol, nContent, szFormat);
    }

    public Variant getTextString(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetTextString", nRow, nCol, nContent);
    }

    public Variant setTextString(Variant nRow, Variant nCol, Variant nContent, Variant text) {
        return call("SetTextString", nRow, nCol, nContent, text);
    }

    public int getFieldId2(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetFieldId2", nRow, nCol, nContent).getInt();
    }

    public Variant setFieldId2(Variant nRow, Variant nCol, Variant nContent, int acDbObjectId, int nflag) {
        return call("SetFieldId2", nRow, nCol, nContent, acDbObjectId, nflag);
    }

    public int getBlockTableRecordId2(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetBlockTableRecordId2", nRow, nCol, nContent).getInt();
    }

    public Variant setBlockTableRecordId2(Variant nRow, Variant nCol, Variant nContent, int blkId, Variant autoFit) {
        return call("SetBlockTableRecordId2", nRow, nCol, nContent, blkId, autoFit);
    }

    public Variant getBlockAttributeValue2(Variant nRow, Variant nCol, Variant nContent, int blkId) {
        return call("GetBlockAttributeValue2", nRow, nCol, nContent, blkId);
    }

    public Variant setBlockAttributeValue2(Variant nRow, Variant nCol, Variant nContent, int blkId, Variant value) {
        return call("SetBlockAttributeValue2", nRow, nCol, nContent, blkId, value);
    }

    public Variant getCustomData(Variant nRow, Variant nCol, Variant szKey, Variant pData) {
        return call("GetCustomData", nRow, nCol, szKey, pData);
    }

    public Variant setCustomData(Variant nRow, Variant nCol, Variant szKey, Variant data) {
        return call("SetCustomData", nRow, nCol, szKey, data);
    }

    public Variant getCellStyle(Variant nRow, Variant nCol) {
        return call("GetCellStyle", nRow, nCol);
    }

    public Variant setCellStyle(Variant nRow, Variant nCol, Variant szCellStyle) {
        return call("SetCellStyle", nRow, nCol, szCellStyle);
    }

    public int getContentColor2(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetContentColor2", nRow, nCol, nContent).getInt();
    }

    public Variant setContentColor2(Variant nRow, Variant nCol, Variant nContent, int pColor) {
        return call("SetContentColor2", nRow, nCol, nContent, pColor);
    }

    public Variant getDataType2(Variant nRow, Variant nCol, Variant nContent, int pDataType, int pUnitType) {
        return call("GetDataType2", nRow, nCol, nContent, pDataType, pUnitType);
    }

    public Variant setDataType2(Variant nRow, Variant nCol, Variant nContent, int dataType, int unitType) {
        return call("SetDataType2", nRow, nCol, nContent, dataType, unitType);
    }

    public Variant getTextStyle2(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetTextStyle2", nRow, nCol, nContent);
    }

    public Variant setTextStyle2(Variant nRow, Variant nCol, Variant nContent, Variant bstrStyleName) {
        return call("SetTextStyle2", nRow, nCol, nContent, bstrStyleName);
    }

    public Variant getTextHeight2(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetTextHeight2", nRow, nCol, nContent);
    }

    public Variant setTextHeight2(Variant nRow, Variant nCol, Variant nContent, Variant height) {
        return call("SetTextHeight2", nRow, nCol, nContent, height);
    }

    public Variant getRotation(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetRotation", nRow, nCol, nContent);
    }

    public Variant setRotation(Variant nRow, Variant nCol, Variant nContent, Variant value) {
        return call("SetRotation", nRow, nCol, nContent, value);
    }

    public Variant getAutoScale2(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetAutoScale2", nRow, nCol, nContent);
    }

    public Variant setAutoScale2(Variant nRow, Variant nCol, Variant nContent, Variant bAutoFit) {
        return call("SetAutoScale2", nRow, nCol, nContent, bAutoFit);
    }

    public Variant getScale(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetScale", nRow, nCol, nContent);
    }

    public Variant setScale(Variant nRow, Variant nCol, Variant nContent, Variant scale) {
        return call("SetScale", nRow, nCol, nContent, scale);
    }

    public Variant removeAllOverrides(Variant nRow, Variant nCol) {
        return call("RemoveAllOverrides", nRow, nCol);
    }

    public int getGridLineWeight2(Variant nRow, Variant nCol, int nGridLineType) {
        return call("GetGridLineWeight2", nRow, nCol, nGridLineType).getInt();
    }

    public Variant setGridLineWeight2(Variant nRow, Variant nCol, int nGridLineType, int lineweight) {
        return call("SetGridLineWeight2", nRow, nCol, nGridLineType, lineweight);
    }

    public int getGridLinetype(Variant nRow, Variant nCol, int nGridLineType) {
        return call("GetGridLinetype", nRow, nCol, nGridLineType).getInt();
    }

    public Variant setGridLinetype(Variant nRow, Variant nCol, int nGridLineType, int idLinetype) {
        return call("SetGridLinetype", nRow, nCol, nGridLineType, idLinetype);
    }

    public int getGridColor2(Variant nRow, Variant nCol, int nGridLineType) {
        return call("GetGridColor2", nRow, nCol, nGridLineType).getInt();
    }

    public Variant setGridColor2(Variant nRow, Variant nCol, int nGridLineType, int pColor) {
        return call("SetGridColor2", nRow, nCol, nGridLineType, pColor);
    }

    public Variant getGridVisibility2(Variant nRow, Variant nCol, int nGridLineType) {
        return call("GetGridVisibility2", nRow, nCol, nGridLineType);
    }

    public Variant setGridVisibility2(Variant nRow, Variant nCol, int nGridLineType, Variant bVisible) {
        return call("SetGridVisibility2", nRow, nCol, nGridLineType, bVisible);
    }

    public Variant getGridDoubleLineSpacing(Variant nRow, Variant nCol, int nGridLineType) {
        return call("GetGridDoubleLineSpacing", nRow, nCol, nGridLineType);
    }

    public Variant setGridDoubleLineSpacing(Variant nRow, Variant nCol, int nGridLineType, Variant fSpacing) {
        return call("SetGridDoubleLineSpacing", nRow, nCol, nGridLineType, fSpacing);
    }

    public Variant getBreakHeight(Variant nIndex) {
        return call("GetBreakHeight", nIndex);
    }

    public Variant setBreakHeight(Variant nIndex, Variant height) {
        return call("SetBreakHeight", nIndex, height);
    }

    public int getContentType(Variant nRow, Variant nCol) {
        return call("GetContentType", nRow, nCol).getInt();
    }

    public Variant getMargin(Variant nRow, Variant nCol, int nMargin) {
        return call("GetMargin", nRow, nCol, nMargin);
    }

    public Variant setMargin(Variant nRow, Variant nCol, int nMargins, Variant fMargin) {
        return call("SetMargin", nRow, nCol, nMargins, fMargin);
    }

    public int getContentLayout(Variant row, Variant col) {
        return call("GetContentLayout", row, col).getInt();
    }

    public Variant setContentLayout(Variant row, Variant col, int nLayout) {
        return call("SetContentLayout", row, col, nLayout);
    }

    public int getOverride(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetOverride", nRow, nCol, nContent).getInt();
    }

    public Variant setOverride(Variant nRow, Variant nCol, Variant nContent, int nProp) {
        return call("SetOverride", nRow, nCol, nContent, nProp);
    }

    public int getGridLineStyle(Variant nRow, Variant nCol, int nGridLineType) {
        return call("GetGridLineStyle", nRow, nCol, nGridLineType).getInt();
    }

    public Variant setGridLineStyle(Variant nRow, Variant nCol, int nGridLineTypes, int nLineStyle) {
        return call("SetGridLineStyle", nRow, nCol, nGridLineTypes, nLineStyle);
    }

    public Variant insertRowsAndInherit(Variant nIndex, Variant nInheritFrom, Variant nNumRows) {
        return call("InsertRowsAndInherit", nIndex, nInheritFrom, nNumRows);
    }

    public Variant insertColumnsAndInherit(Variant col, Variant nInheritFrom, Variant nNumCols) {
        return call("InsertColumnsAndInherit", col, nInheritFrom, nNumCols);
    }

    public Variant getHasFormula(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetHasFormula", nRow, nCol, nContent);
    }

    public Variant getFormula(Variant nRow, Variant nCol, Variant nContent) {
        return call("GetFormula", nRow, nCol, nContent);
    }

    public Variant setFormula(Variant nRow, Variant nCol, Variant nContent, Variant pszFormula) {
        return call("SetFormula", nRow, nCol, nContent, pszFormula);
    }

    public Variant isContentEditable(Variant nRow, Variant nCol) {
        return call("IsContentEditable", nRow, nCol);
    }

    public Variant isFormatEditable(Variant nRow, Variant nCol) {
        return call("IsFormatEditable", nRow, nCol);
    }

    public int getCellState(Variant nRow, Variant nCol) {
        return call("GetCellState", nRow, nCol).getInt();
    }

    public Variant setCellState(Variant nRow, Variant nCol, int nLock) {
        return call("SetCellState", nRow, nCol, nLock);
    }

    public Variant enableMergeAll(Variant nRow, Variant nCol, Variant bEnable) {
        return call("EnableMergeAll", nRow, nCol, bEnable);
    }

    public Variant isMergeAllEnabled(Variant nRow, Variant nCol) {
        return call("IsMergeAllEnabled", nRow, nCol);
    }

    public Variant getColumnName(Variant nIndex) {
        return call("GetColumnName", nIndex);
    }

    public Variant setColumnName(Variant nIndex, Variant name) {
        return call("SetColumnName", nIndex, name);
    }

    public Variant setToolTip(Variant nRow, Variant nCol, Variant tip) {
        return call("SetToolTip", nRow, nCol, tip);
    }

}
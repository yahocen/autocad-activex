// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;
import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.util.EntityUtil;

/**
 * AcadPaperSpace — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PaperSpace extends ComCollection<AcadEntity> {

    public PaperSpace(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected AcadEntity wrap(Dispatch dispatch) {
        return EntityUtil.parse(dispatch);
    }
    public int getBlockScaling() {
        return getProperties("BlockScaling").getInt();
    }

    public PaperSpace setBlockScaling(int blockScaling) {
        setProperties("BlockScaling", blockScaling);
        return this;
    }

    public Variant getComments() {
        return getProperties("Comments");
    }

    public PaperSpace setComments(Variant comments) {
        setProperties("Comments", comments);
        return this;
    }

    public Variant getExplodable() {
        return getProperties("Explodable");
    }

    public PaperSpace setExplodable(Variant explodable) {
        setProperties("Explodable", explodable);
        return this;
    }

    public Variant getIsDynamicBlock() {
        return getProperties("IsDynamicBlock");
    }

    public Variant getIsLayout() {
        return getProperties("IsLayout");
    }

    public Variant getIsXRef() {
        return getProperties("IsXRef");
    }

    public int getLayout() {
        return getProperties("Layout").getInt();
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public PaperSpace setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getOrigin() {
        return getProperties("Origin");
    }

    public PaperSpace setOrigin(Variant origin) {
        setProperties("Origin", origin);
        return this;
    }

    public Variant getPath() {
        return getProperties("Path");
    }

    public PaperSpace setPath(Variant path) {
        setProperties("Path", path);
        return this;
    }

    public int getUnits() {
        return getProperties("Units").getInt();
    }

    public PaperSpace setUnits(int units) {
        setProperties("Units", units);
        return this;
    }

    public int getXRefDatabase() {
        return getProperties("XRefDatabase").getInt();
    }

    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public Variant addCustomObject(Variant className) {
        Variant result = Disp.call(getClass(), dispatch,"AddCustomObject", className);
        return result;
    }

    public int add3DFace(Variant point1, Variant point2, Variant point3, Variant point4) {
        return Disp.call(getClass(), dispatch,"Add3DFace", point1, point2, point3, point4).getInt();
    }

    public int add3DMesh(Variant m, Variant n, Variant pointsMatrix) {
        return Disp.call(getClass(), dispatch,"Add3DMesh", m, n, pointsMatrix).getInt();
    }

    public int add3DPoly(Variant pointsArray) {
        return Disp.call(getClass(), dispatch,"Add3DPoly", pointsArray).getInt();
    }

    public int addArc(Variant center, Variant radius, Variant startAngle, Variant endAngle) {
        return Disp.call(getClass(), dispatch,"AddArc", center, radius, startAngle, endAngle).getInt();
    }

    public int addAttribute(Variant height, int mode, Variant prompt, Variant insertionPoint, Variant tag, Variant value) {
        return Disp.call(getClass(), dispatch,"AddAttribute", height, mode, prompt, insertionPoint, tag, value).getInt();
    }

    public int addBox(Variant origin, Variant length, Variant width, Variant height) {
        return Disp.call(getClass(), dispatch,"AddBox", origin, length, width, height).getInt();
    }

    public int addCircle(Variant center, Variant radius) {
        return Disp.call(getClass(), dispatch,"AddCircle", center, radius).getInt();
    }

    public int addCone(Variant center, Variant baseRadius, Variant height) {
        return Disp.call(getClass(), dispatch,"AddCone", center, baseRadius, height).getInt();
    }

    public int addCylinder(Variant center, Variant radius, Variant height) {
        return Disp.call(getClass(), dispatch,"AddCylinder", center, radius, height).getInt();
    }

    public int addDimAligned(Variant extLine1Point, Variant extLine2Point, Variant textPosition) {
        return Disp.call(getClass(), dispatch,"AddDimAligned", extLine1Point, extLine2Point, textPosition).getInt();
    }

    public int addDimAngular(Variant angleVertex, Variant firstEndPoint, Variant secondEndPoint, Variant textPoint) {
        return Disp.call(getClass(), dispatch,"AddDimAngular", angleVertex, firstEndPoint, secondEndPoint, textPoint).getInt();
    }

    public int addDimDiametric(Variant chordPoint, Variant farChordPoint, Variant leaderLength) {
        return Disp.call(getClass(), dispatch,"AddDimDiametric", chordPoint, farChordPoint, leaderLength).getInt();
    }

    public int addDimRotated(Variant extLine1Point, Variant extLine2Point, Variant dimLineLocation, Variant rotationAngle) {
        return Disp.call(getClass(), dispatch,"AddDimRotated", extLine1Point, extLine2Point, dimLineLocation, rotationAngle).getInt();
    }

    public int addDimOrdinate(Variant definitionPoint, Variant leaderEndPoint, Variant useXAxis) {
        return Disp.call(getClass(), dispatch,"AddDimOrdinate", definitionPoint, leaderEndPoint, useXAxis).getInt();
    }

    public int addDimRadial(Variant center, Variant chordPoint, Variant leaderLength) {
        return Disp.call(getClass(), dispatch,"AddDimRadial", center, chordPoint, leaderLength).getInt();
    }

    public int addEllipse(Variant center, Variant majorAxis, Variant radiusRatio) {
        return Disp.call(getClass(), dispatch,"AddEllipse", center, majorAxis, radiusRatio).getInt();
    }

    public int addEllipticalCone(Variant center, Variant majorRadius, Variant minorRadius, Variant height) {
        return Disp.call(getClass(), dispatch,"AddEllipticalCone", center, majorRadius, minorRadius, height).getInt();
    }

    public int addEllipticalCylinder(Variant center, Variant majorRadius, Variant minorRadius, Variant height) {
        return Disp.call(getClass(), dispatch,"AddEllipticalCylinder", center, majorRadius, minorRadius, height).getInt();
    }

    public int addExtrudedSolid(Variant profile, Variant height, Variant taperAngle) {
        return Disp.call(getClass(), dispatch,"AddExtrudedSolid", profile, height, taperAngle).getInt();
    }

    public int addExtrudedSolidAlongPath(Variant profile, Variant path) {
        return Disp.call(getClass(), dispatch,"AddExtrudedSolidAlongPath", profile, path).getInt();
    }

    public int addLeader(Variant pointsArray, Variant annotation, int type) {
        return Disp.call(getClass(), dispatch,"AddLeader", pointsArray, annotation, type).getInt();
    }

    public int addMText(Variant insertionPoint, Variant width, Variant text) {
        return Disp.call(getClass(), dispatch,"AddMText", insertionPoint, width, text).getInt();
    }

    public int addPoint(Variant point) {
        return Disp.call(getClass(), dispatch,"AddPoint", point).getInt();
    }

    public int addLightWeightPolyline(Variant verticesList) {
        return Disp.call(getClass(), dispatch,"AddLightWeightPolyline", verticesList).getInt();
    }

    public int addPolyline(Variant verticesList) {
        return Disp.call(getClass(), dispatch,"AddPolyline", verticesList).getInt();
    }

    public int addRay(Variant point1, Variant point2) {
        return Disp.call(getClass(), dispatch,"AddRay", point1, point2).getInt();
    }

    public Variant addRegion(Variant objectList) {
        Variant result = Disp.call(getClass(), dispatch,"AddRegion", objectList);
        return result;
    }

    public int addRevolvedSolid(Variant profile, Variant axisPoint, Variant axisDir, Variant angle) {
        return Disp.call(getClass(), dispatch,"AddRevolvedSolid", profile, axisPoint, axisDir, angle).getInt();
    }

    public int addShape(Variant name, Variant insertionPoint, Variant scaleFactor, Variant rotationAngle) {
        return Disp.call(getClass(), dispatch,"AddShape", name, insertionPoint, scaleFactor, rotationAngle).getInt();
    }

    public int addSolid(Variant point1, Variant point2, Variant point3, Variant point4) {
        return Disp.call(getClass(), dispatch,"AddSolid", point1, point2, point3, point4).getInt();
    }

    public int addSphere(Variant center, Variant radius) {
        return Disp.call(getClass(), dispatch,"AddSphere", center, radius).getInt();
    }

    public int addSpline(Variant pointsArray, Variant startTangent, Variant endTangent) {
        return Disp.call(getClass(), dispatch,"AddSpline", pointsArray, startTangent, endTangent).getInt();
    }

    public int addText(Variant textString, Variant insertionPoint, Variant height) {
        return Disp.call(getClass(), dispatch,"AddText", textString, insertionPoint, height).getInt();
    }

    public int addTolerance(Variant text, Variant insertionPoint, Variant direction) {
        return Disp.call(getClass(), dispatch,"AddTolerance", text, insertionPoint, direction).getInt();
    }

    public int addTorus(Variant center, Variant torusRadius, Variant tubeRadius) {
        return Disp.call(getClass(), dispatch,"AddTorus", center, torusRadius, tubeRadius).getInt();
    }

    public int addTrace(Variant pointsArray) {
        return Disp.call(getClass(), dispatch,"AddTrace", pointsArray).getInt();
    }

    public int addWedge(Variant center, Variant length, Variant width, Variant height) {
        return Disp.call(getClass(), dispatch,"AddWedge", center, length, width, height).getInt();
    }

    public int addXline(Variant point1, Variant point2) {
        return Disp.call(getClass(), dispatch,"AddXline", point1, point2).getInt();
    }

    public int insertBlock(Variant insertionPoint, Variant name, Variant xscale, Variant yscale, Variant zscale, Variant rotation, Variant password) {
        return Disp.call(getClass(), dispatch,"InsertBlock", insertionPoint, name, xscale, yscale, zscale, rotation, password).getInt();
    }

    public int addHatch(Variant patternType, Variant patternName, Variant associativity, Variant hatchObjectType) {
        return Disp.call(getClass(), dispatch,"AddHatch", patternType, patternName, associativity, hatchObjectType).getInt();
    }

    public int addRaster(Variant imageFileName, Variant insertionPoint, Variant scaleFactor, Variant rotationAngle) {
        return Disp.call(getClass(), dispatch,"AddRaster", imageFileName, insertionPoint, scaleFactor, rotationAngle).getInt();
    }

    public int addLine(Variant startPoint, Variant endPoint) {
        return Disp.call(getClass(), dispatch,"AddLine", startPoint, endPoint).getInt();
    }

    public int addMInsertBlock(Variant insertionPoint, Variant name, Variant xscale, Variant yscale, Variant zscale, Variant rotation, Variant numRows, Variant numColumns, Variant rowSpacing, Variant columnSpacing, Variant password) {
        return Disp.call(getClass(), dispatch,"AddMInsertBlock", insertionPoint, name, xscale, yscale, zscale, rotation, numRows, numColumns, rowSpacing, columnSpacing, password).getInt();
    }

    public int addPolyfaceMesh(Variant vertexList, Variant faceList) {
        return Disp.call(getClass(), dispatch,"AddPolyfaceMesh", vertexList, faceList).getInt();
    }

    public int addMLine(Variant vertexList) {
        return Disp.call(getClass(), dispatch,"AddMLine", vertexList).getInt();
    }

    public int addDim3PointAngular(Variant angleVertex, Variant firstEndPoint, Variant secondEndPoint, Variant textPoint) {
        return Disp.call(getClass(), dispatch,"AddDim3PointAngular", angleVertex, firstEndPoint, secondEndPoint, textPoint).getInt();
    }

    public int attachExternalReference(Variant pathName, Variant name, Variant insertionPoint, Variant xscale, Variant yscale, Variant zscale, Variant rotation, Variant bOverlay, Variant password) {
        return Disp.call(getClass(), dispatch,"AttachExternalReference", pathName, name, insertionPoint, xscale, yscale, zscale, rotation, bOverlay, password).getInt();
    }

    public Variant unload() {
        Variant result = Disp.call(getClass(), dispatch,"Unload");
        return result;
    }

    public Variant reload() {
        Variant result = Disp.call(getClass(), dispatch,"Reload");
        return result;
    }

    public Variant bind(Variant bPrefixName) {
        Variant result = Disp.call(getClass(), dispatch,"Bind", bPrefixName);
        return result;
    }

    public Variant detach() {
        Variant result = Disp.call(getClass(), dispatch,"Detach");
        return result;
    }

    public int addTable(Variant insertionPoint, Variant numRows, Variant numColumns, Variant rowHeight, Variant colWidth) {
        return Disp.call(getClass(), dispatch,"AddTable", insertionPoint, numRows, numColumns, rowHeight, colWidth).getInt();
    }

    public int addDimArc(Variant arcCenter, Variant firstEndPoint, Variant secondEndPoint, Variant arcPoint) {
        return Disp.call(getClass(), dispatch,"AddDimArc", arcCenter, firstEndPoint, secondEndPoint, arcPoint).getInt();
    }

    public int addDimRadialLarge(Variant center, Variant chordPoint, Variant overrideCenter, Variant jogPoint, Variant jogAngle) {
        return Disp.call(getClass(), dispatch,"AddDimRadialLarge", center, chordPoint, overrideCenter, jogPoint, jogAngle).getInt();
    }

    public int addSection(Variant fromPoint, Variant toPoint, Variant planeVector) {
        return Disp.call(getClass(), dispatch,"AddSection", fromPoint, toPoint, planeVector).getInt();
    }

    public int addMLeader(Variant pointsArray, Variant leaderLineIndex) {
        return Disp.call(getClass(), dispatch,"AddMLeader", pointsArray, leaderLineIndex).getInt();
    }

    public int addPViewport(Variant center, Variant width, Variant height) {
        return Disp.call(getClass(), dispatch,"AddPViewport", center, width, height).getInt();
    }

}

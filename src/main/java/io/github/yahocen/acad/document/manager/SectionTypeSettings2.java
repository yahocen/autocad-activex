package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadSectionTypeSettings2 — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class SectionTypeSettings2 extends ComObject {

    public SectionTypeSettings2(Dispatch dispatch) {
        super(dispatch);
    }
    public int getBackgroundLinesColor() {
        return Disp.get(getClass(), dispatch, "BackgroundLinesColor").getInt();
    }

    public SectionTypeSettings2 setBackgroundLinesColor(int backgroundLinesColor) {
        Disp.put(getClass(), dispatch, "BackgroundLinesColor", backgroundLinesColor);
        return this;
    }

    public Variant getBackgroundLinesHiddenLine() {
        return Disp.get(getClass(), dispatch, "BackgroundLinesHiddenLine");
    }

    public SectionTypeSettings2 setBackgroundLinesHiddenLine(Variant backgroundLinesHiddenLine) {
        Disp.put(getClass(), dispatch, "BackgroundLinesHiddenLine", backgroundLinesHiddenLine);
        return this;
    }

    public Variant getBackgroundLinesLayer() {
        return Disp.get(getClass(), dispatch, "BackgroundLinesLayer");
    }

    public SectionTypeSettings2 setBackgroundLinesLayer(Variant backgroundLinesLayer) {
        Disp.put(getClass(), dispatch, "BackgroundLinesLayer", backgroundLinesLayer);
        return this;
    }

    public Variant getBackgroundLinesLinetype() {
        return Disp.get(getClass(), dispatch, "BackgroundLinesLinetype");
    }

    public SectionTypeSettings2 setBackgroundLinesLinetype(Variant backgroundLinesLinetype) {
        Disp.put(getClass(), dispatch, "BackgroundLinesLinetype", backgroundLinesLinetype);
        return this;
    }

    public int getBackgroundLinesLinetypeScale() {
        return Disp.get(getClass(), dispatch, "BackgroundLinesLinetypeScale").getInt();
    }

    public SectionTypeSettings2 setBackgroundLinesLinetypeScale(int backgroundLinesLinetypeScale) {
        Disp.put(getClass(), dispatch, "BackgroundLinesLinetypeScale", backgroundLinesLinetypeScale);
        return this;
    }

    public int getBackgroundLinesLineweight() {
        return Disp.get(getClass(), dispatch, "BackgroundLinesLineweight").getInt();
    }

    public SectionTypeSettings2 setBackgroundLinesLineweight(int backgroundLinesLineweight) {
        Disp.put(getClass(), dispatch, "BackgroundLinesLineweight", backgroundLinesLineweight);
        return this;
    }

    public Variant getBackgroundLinesPlotStyleName() {
        return Disp.get(getClass(), dispatch, "BackgroundLinesPlotStyleName");
    }

    public SectionTypeSettings2 setBackgroundLinesPlotStyleName(Variant backgroundLinesPlotStyleName) {
        Disp.put(getClass(), dispatch, "BackgroundLinesPlotStyleName", backgroundLinesPlotStyleName);
        return this;
    }

    public Variant getBackgroundLinesVisible() {
        return Disp.get(getClass(), dispatch, "BackgroundLinesVisible");
    }

    public SectionTypeSettings2 setBackgroundLinesVisible(Variant backgroundLinesVisible) {
        Disp.put(getClass(), dispatch, "BackgroundLinesVisible", backgroundLinesVisible);
        return this;
    }

    public int getCurveTangencyLinesColor() {
        return Disp.get(getClass(), dispatch, "CurveTangencyLinesColor").getInt();
    }

    public SectionTypeSettings2 setCurveTangencyLinesColor(int curveTangencyLinesColor) {
        Disp.put(getClass(), dispatch, "CurveTangencyLinesColor", curveTangencyLinesColor);
        return this;
    }

    public Variant getCurveTangencyLinesLayer() {
        return Disp.get(getClass(), dispatch, "CurveTangencyLinesLayer");
    }

    public SectionTypeSettings2 setCurveTangencyLinesLayer(Variant curveTangencyLinesLayer) {
        Disp.put(getClass(), dispatch, "CurveTangencyLinesLayer", curveTangencyLinesLayer);
        return this;
    }

    public Variant getCurveTangencyLinesLinetype() {
        return Disp.get(getClass(), dispatch, "CurveTangencyLinesLinetype");
    }

    public SectionTypeSettings2 setCurveTangencyLinesLinetype(Variant curveTangencyLinesLinetype) {
        Disp.put(getClass(), dispatch, "CurveTangencyLinesLinetype", curveTangencyLinesLinetype);
        return this;
    }

    public int getCurveTangencyLinesLinetypeScale() {
        return Disp.get(getClass(), dispatch, "CurveTangencyLinesLinetypeScale").getInt();
    }

    public SectionTypeSettings2 setCurveTangencyLinesLinetypeScale(int curveTangencyLinesLinetypeScale) {
        Disp.put(getClass(), dispatch, "CurveTangencyLinesLinetypeScale", curveTangencyLinesLinetypeScale);
        return this;
    }

    public int getCurveTangencyLinesLineweight() {
        return Disp.get(getClass(), dispatch, "CurveTangencyLinesLineweight").getInt();
    }

    public SectionTypeSettings2 setCurveTangencyLinesLineweight(int curveTangencyLinesLineweight) {
        Disp.put(getClass(), dispatch, "CurveTangencyLinesLineweight", curveTangencyLinesLineweight);
        return this;
    }

    public Variant getCurveTangencyLinesPlotStyleName() {
        return Disp.get(getClass(), dispatch, "CurveTangencyLinesPlotStyleName");
    }

    public SectionTypeSettings2 setCurveTangencyLinesPlotStyleName(Variant curveTangencyLinesPlotStyleName) {
        Disp.put(getClass(), dispatch, "CurveTangencyLinesPlotStyleName", curveTangencyLinesPlotStyleName);
        return this;
    }

    public Variant getCurveTangencyLinesVisible() {
        return Disp.get(getClass(), dispatch, "CurveTangencyLinesVisible");
    }

    public SectionTypeSettings2 setCurveTangencyLinesVisible(Variant curveTangencyLinesVisible) {
        Disp.put(getClass(), dispatch, "CurveTangencyLinesVisible", curveTangencyLinesVisible);
        return this;
    }

    public Variant getDestinationBlock() {
        return Disp.get(getClass(), dispatch, "DestinationBlock");
    }

    public SectionTypeSettings2 setDestinationBlock(Variant destinationBlock) {
        Disp.put(getClass(), dispatch, "DestinationBlock", destinationBlock);
        return this;
    }

    public Variant getDestinationFile() {
        return Disp.get(getClass(), dispatch, "DestinationFile");
    }

    public SectionTypeSettings2 setDestinationFile(Variant destinationFile) {
        Disp.put(getClass(), dispatch, "DestinationFile", destinationFile);
        return this;
    }

    public int getForegroundLinesColor() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesColor").getInt();
    }

    public SectionTypeSettings2 setForegroundLinesColor(int foregroundLinesColor) {
        Disp.put(getClass(), dispatch, "ForegroundLinesColor", foregroundLinesColor);
        return this;
    }

    public Variant getForegroundLinesEdgeTransparency() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesEdgeTransparency");
    }

    public SectionTypeSettings2 setForegroundLinesEdgeTransparency(Variant foregroundLinesEdgeTransparency) {
        Disp.put(getClass(), dispatch, "ForegroundLinesEdgeTransparency", foregroundLinesEdgeTransparency);
        return this;
    }

    public Variant getForegroundLinesFaceTransparency() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesFaceTransparency");
    }

    public SectionTypeSettings2 setForegroundLinesFaceTransparency(Variant foregroundLinesFaceTransparency) {
        Disp.put(getClass(), dispatch, "ForegroundLinesFaceTransparency", foregroundLinesFaceTransparency);
        return this;
    }

    public Variant getForegroundLinesHiddenLine() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesHiddenLine");
    }

    public SectionTypeSettings2 setForegroundLinesHiddenLine(Variant foregroundLinesHiddenLine) {
        Disp.put(getClass(), dispatch, "ForegroundLinesHiddenLine", foregroundLinesHiddenLine);
        return this;
    }

    public Variant getForegroundLinesLayer() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesLayer");
    }

    public SectionTypeSettings2 setForegroundLinesLayer(Variant foregroundLinesLayer) {
        Disp.put(getClass(), dispatch, "ForegroundLinesLayer", foregroundLinesLayer);
        return this;
    }

    public Variant getForegroundLinesLinetype() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesLinetype");
    }

    public SectionTypeSettings2 setForegroundLinesLinetype(Variant foregroundLinesLinetype) {
        Disp.put(getClass(), dispatch, "ForegroundLinesLinetype", foregroundLinesLinetype);
        return this;
    }

    public int getForegroundLinesLinetypeScale() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesLinetypeScale").getInt();
    }

    public SectionTypeSettings2 setForegroundLinesLinetypeScale(int foregroundLinesLinetypeScale) {
        Disp.put(getClass(), dispatch, "ForegroundLinesLinetypeScale", foregroundLinesLinetypeScale);
        return this;
    }

    public int getForegroundLinesLineweight() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesLineweight").getInt();
    }

    public SectionTypeSettings2 setForegroundLinesLineweight(int foregroundLinesLineweight) {
        Disp.put(getClass(), dispatch, "ForegroundLinesLineweight", foregroundLinesLineweight);
        return this;
    }

    public Variant getForegroundLinesPlotStyleName() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesPlotStyleName");
    }

    public SectionTypeSettings2 setForegroundLinesPlotStyleName(Variant foregroundLinesPlotStyleName) {
        Disp.put(getClass(), dispatch, "ForegroundLinesPlotStyleName", foregroundLinesPlotStyleName);
        return this;
    }

    public Variant getForegroundLinesVisible() {
        return Disp.get(getClass(), dispatch, "ForegroundLinesVisible");
    }

    public SectionTypeSettings2 setForegroundLinesVisible(Variant foregroundLinesVisible) {
        Disp.put(getClass(), dispatch, "ForegroundLinesVisible", foregroundLinesVisible);
        return this;
    }

    public int getGenerationOptions() {
        return Disp.get(getClass(), dispatch, "GenerationOptions").getInt();
    }

    public SectionTypeSettings2 setGenerationOptions(int generationOptions) {
        Disp.put(getClass(), dispatch, "GenerationOptions", generationOptions);
        return this;
    }

    public int getIntersectionBoundaryColor() {
        return Disp.get(getClass(), dispatch, "IntersectionBoundaryColor").getInt();
    }

    public SectionTypeSettings2 setIntersectionBoundaryColor(int intersectionBoundaryColor) {
        Disp.put(getClass(), dispatch, "IntersectionBoundaryColor", intersectionBoundaryColor);
        return this;
    }

    public Variant getIntersectionBoundaryDivisionLines() {
        return Disp.get(getClass(), dispatch, "IntersectionBoundaryDivisionLines");
    }

    public SectionTypeSettings2 setIntersectionBoundaryDivisionLines(Variant intersectionBoundaryDivisionLines) {
        Disp.put(getClass(), dispatch, "IntersectionBoundaryDivisionLines", intersectionBoundaryDivisionLines);
        return this;
    }

    public Variant getIntersectionBoundaryLayer() {
        return Disp.get(getClass(), dispatch, "IntersectionBoundaryLayer");
    }

    public SectionTypeSettings2 setIntersectionBoundaryLayer(Variant intersectionBoundaryLayer) {
        Disp.put(getClass(), dispatch, "IntersectionBoundaryLayer", intersectionBoundaryLayer);
        return this;
    }

    public Variant getIntersectionBoundaryLinetype() {
        return Disp.get(getClass(), dispatch, "IntersectionBoundaryLinetype");
    }

    public SectionTypeSettings2 setIntersectionBoundaryLinetype(Variant intersectionBoundaryLinetype) {
        Disp.put(getClass(), dispatch, "IntersectionBoundaryLinetype", intersectionBoundaryLinetype);
        return this;
    }

    public int getIntersectionBoundaryLinetypeScale() {
        return Disp.get(getClass(), dispatch, "IntersectionBoundaryLinetypeScale").getInt();
    }

    public SectionTypeSettings2 setIntersectionBoundaryLinetypeScale(int intersectionBoundaryLinetypeScale) {
        Disp.put(getClass(), dispatch, "IntersectionBoundaryLinetypeScale", intersectionBoundaryLinetypeScale);
        return this;
    }

    public int getIntersectionBoundaryLineweight() {
        return Disp.get(getClass(), dispatch, "IntersectionBoundaryLineweight").getInt();
    }

    public SectionTypeSettings2 setIntersectionBoundaryLineweight(int intersectionBoundaryLineweight) {
        Disp.put(getClass(), dispatch, "IntersectionBoundaryLineweight", intersectionBoundaryLineweight);
        return this;
    }

    public Variant getIntersectionBoundaryPlotStyleName() {
        return Disp.get(getClass(), dispatch, "IntersectionBoundaryPlotStyleName");
    }

    public SectionTypeSettings2 setIntersectionBoundaryPlotStyleName(Variant intersectionBoundaryPlotStyleName) {
        Disp.put(getClass(), dispatch, "IntersectionBoundaryPlotStyleName", intersectionBoundaryPlotStyleName);
        return this;
    }

    public Variant getIntersectionBoundaryVisible() {
        return Disp.get(getClass(), dispatch, "IntersectionBoundaryVisible");
    }

    public SectionTypeSettings2 setIntersectionBoundaryVisible(Variant intersectionBoundaryVisible) {
        Disp.put(getClass(), dispatch, "IntersectionBoundaryVisible", intersectionBoundaryVisible);
        return this;
    }

    public int getIntersectionFillColor() {
        return Disp.get(getClass(), dispatch, "IntersectionFillColor").getInt();
    }

    public SectionTypeSettings2 setIntersectionFillColor(int intersectionFillColor) {
        Disp.put(getClass(), dispatch, "IntersectionFillColor", intersectionFillColor);
        return this;
    }

    public Variant getIntersectionFillFaceTransparency() {
        return Disp.get(getClass(), dispatch, "IntersectionFillFaceTransparency");
    }

    public SectionTypeSettings2 setIntersectionFillFaceTransparency(Variant intersectionFillFaceTransparency) {
        Disp.put(getClass(), dispatch, "IntersectionFillFaceTransparency", intersectionFillFaceTransparency);
        return this;
    }

    public int getIntersectionFillHatchAngle() {
        return Disp.get(getClass(), dispatch, "IntersectionFillHatchAngle").getInt();
    }

    public SectionTypeSettings2 setIntersectionFillHatchAngle(int intersectionFillHatchAngle) {
        Disp.put(getClass(), dispatch, "IntersectionFillHatchAngle", intersectionFillHatchAngle);
        return this;
    }

    public Variant getIntersectionFillHatchPatternName() {
        return Disp.get(getClass(), dispatch, "IntersectionFillHatchPatternName");
    }

    public SectionTypeSettings2 setIntersectionFillHatchPatternName(Variant intersectionFillHatchPatternName) {
        Disp.put(getClass(), dispatch, "IntersectionFillHatchPatternName", intersectionFillHatchPatternName);
        return this;
    }

    public int getIntersectionFillHatchPatternType() {
        return Disp.get(getClass(), dispatch, "IntersectionFillHatchPatternType").getInt();
    }

    public SectionTypeSettings2 setIntersectionFillHatchPatternType(int intersectionFillHatchPatternType) {
        Disp.put(getClass(), dispatch, "IntersectionFillHatchPatternType", intersectionFillHatchPatternType);
        return this;
    }

    public int getIntersectionFillHatchScale() {
        return Disp.get(getClass(), dispatch, "IntersectionFillHatchScale").getInt();
    }

    public SectionTypeSettings2 setIntersectionFillHatchScale(int intersectionFillHatchScale) {
        Disp.put(getClass(), dispatch, "IntersectionFillHatchScale", intersectionFillHatchScale);
        return this;
    }

    public Variant getIntersectionFillHatchSpacing() {
        return Disp.get(getClass(), dispatch, "IntersectionFillHatchSpacing");
    }

    public SectionTypeSettings2 setIntersectionFillHatchSpacing(Variant intersectionFillHatchSpacing) {
        Disp.put(getClass(), dispatch, "IntersectionFillHatchSpacing", intersectionFillHatchSpacing);
        return this;
    }

    public Variant getIntersectionFillLayer() {
        return Disp.get(getClass(), dispatch, "IntersectionFillLayer");
    }

    public SectionTypeSettings2 setIntersectionFillLayer(Variant intersectionFillLayer) {
        Disp.put(getClass(), dispatch, "IntersectionFillLayer", intersectionFillLayer);
        return this;
    }

    public Variant getIntersectionFillLinetype() {
        return Disp.get(getClass(), dispatch, "IntersectionFillLinetype");
    }

    public SectionTypeSettings2 setIntersectionFillLinetype(Variant intersectionFillLinetype) {
        Disp.put(getClass(), dispatch, "IntersectionFillLinetype", intersectionFillLinetype);
        return this;
    }

    public int getIntersectionFillLinetypeScale() {
        return Disp.get(getClass(), dispatch, "IntersectionFillLinetypeScale").getInt();
    }

    public SectionTypeSettings2 setIntersectionFillLinetypeScale(int intersectionFillLinetypeScale) {
        Disp.put(getClass(), dispatch, "IntersectionFillLinetypeScale", intersectionFillLinetypeScale);
        return this;
    }

    public int getIntersectionFillLineweight() {
        return Disp.get(getClass(), dispatch, "IntersectionFillLineweight").getInt();
    }

    public SectionTypeSettings2 setIntersectionFillLineweight(int intersectionFillLineweight) {
        Disp.put(getClass(), dispatch, "IntersectionFillLineweight", intersectionFillLineweight);
        return this;
    }

    public Variant getIntersectionFillPlotStyleName() {
        return Disp.get(getClass(), dispatch, "IntersectionFillPlotStyleName");
    }

    public SectionTypeSettings2 setIntersectionFillPlotStyleName(Variant intersectionFillPlotStyleName) {
        Disp.put(getClass(), dispatch, "IntersectionFillPlotStyleName", intersectionFillPlotStyleName);
        return this;
    }

    public Variant getIntersectionFillVisible() {
        return Disp.get(getClass(), dispatch, "IntersectionFillVisible");
    }

    public SectionTypeSettings2 setIntersectionFillVisible(Variant intersectionFillVisible) {
        Disp.put(getClass(), dispatch, "IntersectionFillVisible", intersectionFillVisible);
        return this;
    }

    public Variant getSourceObjects() {
        return Disp.get(getClass(), dispatch, "SourceObjects");
    }

    public SectionTypeSettings2 setSourceObjects(Variant sourceObjects) {
        Disp.put(getClass(), dispatch, "SourceObjects", sourceObjects);
        return this;
    }

}

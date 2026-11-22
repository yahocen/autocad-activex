package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.Surface;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * LoftedSurface - AutoCAD LoftedSurface 实体
 */
@AcadEntityName("AcDbLoftedSurface")
public class LoftedSurface extends Surface {

    public LoftedSurface(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getClosed() {
        return getProperties("Closed");
    }

    public LoftedSurface setClosed(Variant closed) {
        setProperties("Closed", closed);
        return this;
    }

    public double getEndDraftAngle() {
        return getProperties("EndDraftAngle").getDouble();
    }

    public LoftedSurface setEndDraftAngle(double endDraftAngle) {
        setProperties("EndDraftAngle", endDraftAngle);
        return this;
    }

    public Variant getEndDraftMagnitude() {
        return getProperties("EndDraftMagnitude");
    }

    public LoftedSurface setEndDraftMagnitude(Variant endDraftMagnitude) {
        setProperties("EndDraftMagnitude", endDraftMagnitude);
        return this;
    }

    public Variant getEndSmoothContinuity() {
        return getProperties("EndSmoothContinuity");
    }

    public LoftedSurface setEndSmoothContinuity(Variant endSmoothContinuity) {
        setProperties("EndSmoothContinuity", endSmoothContinuity);
        return this;
    }

    public Variant getEndSmoothMagnitude() {
        return getProperties("EndSmoothMagnitude");
    }

    public LoftedSurface setEndSmoothMagnitude(Variant endSmoothMagnitude) {
        setProperties("EndSmoothMagnitude", endSmoothMagnitude);
        return this;
    }

    public Variant getNumCrossSections() {
        return getProperties("NumCrossSections");
    }

    public Variant getNumGuidePaths() {
        return getProperties("NumGuidePaths");
    }

    public Variant getPeriodic() {
        return getProperties("Periodic");
    }

    public LoftedSurface setPeriodic(Variant periodic) {
        setProperties("Periodic", periodic);
        return this;
    }

    public double getStartDraftAngle() {
        return getProperties("StartDraftAngle").getDouble();
    }

    public LoftedSurface setStartDraftAngle(double startDraftAngle) {
        setProperties("StartDraftAngle", startDraftAngle);
        return this;
    }

    public Variant getStartDraftMagnitude() {
        return getProperties("StartDraftMagnitude");
    }

    public LoftedSurface setStartDraftMagnitude(Variant startDraftMagnitude) {
        setProperties("StartDraftMagnitude", startDraftMagnitude);
        return this;
    }

    public Variant getStartSmoothContinuity() {
        return getProperties("StartSmoothContinuity");
    }

    public LoftedSurface setStartSmoothContinuity(Variant startSmoothContinuity) {
        setProperties("StartSmoothContinuity", startSmoothContinuity);
        return this;
    }

    public Variant getStartSmoothMagnitude() {
        return getProperties("StartSmoothMagnitude");
    }

    public LoftedSurface setStartSmoothMagnitude(Variant startSmoothMagnitude) {
        setProperties("StartSmoothMagnitude", startSmoothMagnitude);
        return this;
    }

    public int getSurfaceNormals() {
        return getProperties("SurfaceNormals").getInt();
    }

    public LoftedSurface setSurfaceNormals(int surfaceNormals) {
        setProperties("SurfaceNormals", surfaceNormals);
        return this;
    }

}
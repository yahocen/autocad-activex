package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Spline - AutoCAD Spline 实体
 */
@AcadEntityName("AcDbSpline")
public class Spline extends AcadEntity {

    public Spline(Dispatch dispatch) {
        super(dispatch);
    }

    public double getArea() {
        return getProperties("Area").getDouble();
    }

    public Variant getClosed() {
        return getProperties("Closed");
    }

    public Variant getClosed2() {
        return getProperties("Closed2");
    }

    public Spline setClosed2(Variant closed2) {
        setProperties("Closed2", closed2);
        return this;
    }

    public Variant getControlPoints() {
        return getProperties("ControlPoints");
    }

    public Spline setControlPoints(Variant controlPoints) {
        setProperties("ControlPoints", controlPoints);
        return this;
    }

    public Variant getDegree() {
        return getProperties("Degree");
    }

    public Variant getDegree2() {
        return getProperties("Degree2");
    }

    public Spline setDegree2(Variant degree2) {
        setProperties("Degree2", degree2);
        return this;
    }

    public Variant getEndTangent() {
        return getProperties("EndTangent");
    }

    public Spline setEndTangent(Variant endTangent) {
        setProperties("EndTangent", endTangent);
        return this;
    }

    public Variant getFitPoints() {
        return getProperties("FitPoints");
    }

    public Spline setFitPoints(Variant fitPoints) {
        setProperties("FitPoints", fitPoints);
        return this;
    }

    public Variant getFitTolerance() {
        return getProperties("FitTolerance");
    }

    public Spline setFitTolerance(Variant fitTolerance) {
        setProperties("FitTolerance", fitTolerance);
        return this;
    }

    public Variant getIsPeriodic() {
        return getProperties("IsPeriodic");
    }

    public Variant getIsPlanar() {
        return getProperties("IsPlanar");
    }

    public Variant getIsRational() {
        return getProperties("IsRational");
    }

    public int getKnotParameterization() {
        return getProperties("KnotParameterization").getInt();
    }

    public Spline setKnotParameterization(int knotParameterization) {
        setProperties("KnotParameterization", knotParameterization);
        return this;
    }

    public Variant getKnots() {
        return getProperties("Knots");
    }

    public Spline setKnots(Variant knots) {
        setProperties("Knots", knots);
        return this;
    }

    public Variant getNumberOfControlPoints() {
        return getProperties("NumberOfControlPoints");
    }

    public Variant getNumberOfFitPoints() {
        return getProperties("NumberOfFitPoints");
    }

    public int getSplineFrame() {
        return getProperties("SplineFrame").getInt();
    }

    public Spline setSplineFrame(int splineFrame) {
        setProperties("SplineFrame", splineFrame);
        return this;
    }

    public int getSplineMethod() {
        return getProperties("SplineMethod").getInt();
    }

    public Spline setSplineMethod(int splineMethod) {
        setProperties("SplineMethod", splineMethod);
        return this;
    }

    public Variant getStartTangent() {
        return getProperties("StartTangent");
    }

    public Spline setStartTangent(Variant startTangent) {
        setProperties("StartTangent", startTangent);
        return this;
    }

    public Variant getWeights() {
        return getProperties("Weights");
    }

    public Spline setWeights(Variant weights) {
        setProperties("Weights", weights);
        return this;
    }

    public Variant setControlPoint(Variant index, Variant controlPoint) {
        return call("SetControlPoint", index, controlPoint);
    }

    public Variant getControlPoint(Variant index) {
        return call("GetControlPoint", index);
    }

    public Variant setFitPoint(Variant index, Variant fitPoint) {
        return call("SetFitPoint", index, fitPoint);
    }

    public Variant getFitPoint(Variant index) {
        return call("GetFitPoint", index);
    }

    public Variant setWeight(Variant index, Variant weight) {
        return call("SetWeight", index, weight);
    }

    public Variant getWeight(Variant index) {
        return call("GetWeight", index);
    }

    public Variant addFitPoint(Variant index, Variant fitPoint) {
        return call("AddFitPoint", index, fitPoint);
    }

    public Variant deleteFitPoint(Variant index) {
        return call("DeleteFitPoint", index);
    }

    public Variant elevateOrder(Variant order) {
        return call("ElevateOrder", order);
    }

    public Variant offset(Variant distance) {
        return call("Offset", distance);
    }

    public Variant purgeFitData() {
        return call("PurgeFitData");
    }

    public Variant reverse() {
        return call("Reverse");
    }

}
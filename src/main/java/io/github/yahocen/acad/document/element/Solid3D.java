package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Solid3D - AutoCAD Solid3D 实体
 */
@AcadEntityName("AcDb3dSolid")
public class Solid3D extends AcadEntity {

    public Solid3D(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getCentroid() {
        return getProperties("Centroid");
    }

    public Variant getHistory() {
        return getProperties("History");
    }

    public Solid3D setHistory(Variant history) {
        setProperties("History", history);
        return this;
    }

    public Variant getMomentOfInertia() {
        return getProperties("MomentOfInertia");
    }

    public Variant getPosition() {
        return getProperties("Position");
    }

    public Solid3D setPosition(Variant position) {
        setProperties("Position", position);
        return this;
    }

    public Variant getPrincipalDirections() {
        return getProperties("PrincipalDirections");
    }

    public Variant getPrincipalMoments() {
        return getProperties("PrincipalMoments");
    }

    public Variant getProductOfInertia() {
        return getProperties("ProductOfInertia");
    }

    public Variant getRadiiOfGyration() {
        return getProperties("RadiiOfGyration");
    }

    public Variant getShowHistory() {
        return getProperties("ShowHistory");
    }

    public Solid3D setShowHistory(Variant showHistory) {
        setProperties("ShowHistory", showHistory);
        return this;
    }

    public Variant getSolidType() {
        return getProperties("SolidType");
    }

    public Variant getVolume() {
        return getProperties("Volume");
    }

    public Variant booleanOp(int operation, int solidObject) {
        return call("Boolean", operation, solidObject);
    }

    public int checkInterference(int object_, Variant createInterferenceSolid, Variant solidsInterfere) {
        return call("CheckInterference", object_, createInterferenceSolid, solidsInterfere).getInt();
    }

    public int sectionSolid(Variant point1, Variant point2, Variant point3) {
        return call("SectionSolid", point1, point2, point3).getInt();
    }

    public int sliceSolid(Variant point1, Variant point2, Variant point3, Variant negative) {
        return call("SliceSolid", point1, point2, point3, negative).getInt();
    }

}
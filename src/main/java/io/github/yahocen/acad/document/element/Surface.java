package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Surface - AutoCAD Surface 实体
 */
@AcadEntityName("AcDbSurface")
public class Surface extends AcadEntity {

    public Surface(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getEdgeExtensionDistances() {
        return getProperties("EdgeExtensionDistances");
    }

    public Surface setEdgeExtensionDistances(Variant edgeExtensionDistances) {
        setProperties("EdgeExtensionDistances", edgeExtensionDistances);
        return this;
    }

    public Variant getMaintainAssociativity() {
        return getProperties("MaintainAssociativity");
    }

    public Surface setMaintainAssociativity(Variant maintainAssociativity) {
        setProperties("MaintainAssociativity", maintainAssociativity);
        return this;
    }

    public Variant getShowAssociativity() {
        return getProperties("ShowAssociativity");
    }

    public Surface setShowAssociativity(Variant showAssociativity) {
        setProperties("ShowAssociativity", showAssociativity);
        return this;
    }

    public Variant getSurfTrimAssociativity() {
        return getProperties("SurfTrimAssociativity");
    }

    public Surface setSurfTrimAssociativity(Variant surfTrimAssociativity) {
        setProperties("SurfTrimAssociativity", surfTrimAssociativity);
        return this;
    }

    public Variant getSurfaceType() {
        return getProperties("SurfaceType");
    }

    public Variant getUIsolineDensity() {
        return getProperties("UIsolineDensity");
    }

    public Surface setUIsolineDensity(Variant uIsolineDensity) {
        setProperties("UIsolineDensity", uIsolineDensity);
        return this;
    }

    public Variant getVIsolineDensity() {
        return getProperties("VIsolineDensity");
    }

    public Surface setVIsolineDensity(Variant vIsolineDensity) {
        setProperties("VIsolineDensity", vIsolineDensity);
        return this;
    }

    public int getWireframeType() {
        return getProperties("WireframeType").getInt();
    }

    public Surface setWireframeType(int wireframeType) {
        setProperties("WireframeType", wireframeType);
        return this;
    }

}
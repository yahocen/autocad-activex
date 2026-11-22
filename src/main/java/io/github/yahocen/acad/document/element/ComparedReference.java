package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.BlockReference;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * ComparedReference - AutoCAD ComparedReference 实体
 */
public class ComparedReference extends BlockReference {

    public ComparedReference(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getLayerPropertyOverrides() {
        return getProperties("LayerPropertyOverrides");
    }

    public Variant getPath() {
        return getProperties("Path");
    }

    public ComparedReference setPath(Variant path) {
        setProperties("Path", path);
        return this;
    }

}
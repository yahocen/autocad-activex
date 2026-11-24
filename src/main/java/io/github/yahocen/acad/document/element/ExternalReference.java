// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;

/**
 * ExternalReference - AutoCAD ExternalReference 实体
 */
public class ExternalReference extends BlockReference {

    public ExternalReference(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getLayerPropertyOverrides() {
        return getProperties("LayerPropertyOverrides");
    }

    public Variant getPath() {
        return getProperties("Path");
    }

    public ExternalReference setPath(Variant path) {
        setProperties("Path", path);
        return this;
    }

}
// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Region - AutoCAD Region 实体
 */
@AcadEntityName("AcDbRegion")
public class Region extends AcadEntity {

    public Region(Dispatch dispatch) {
        super(dispatch);
    }

    public double getArea() {
        return getProperties("Area").getDouble();
    }

    public Variant getCentroid() {
        return getProperties("Centroid");
    }

    public Variant getMomentOfInertia() {
        return getProperties("MomentOfInertia");
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Variant getPerimeter() {
        return getProperties("Perimeter");
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

    public Variant booleanOp(int operation, int object_) {
        return call("Boolean", operation, object_);
    }

    public Variant explode() {
        return call("Explode");
    }

}
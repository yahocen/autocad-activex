// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadHyperlinks — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Hyperlinks extends ComCollection<Hyperlink> {

    public Hyperlinks(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Hyperlink wrap(Dispatch dispatch) {
        return new Hyperlink(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant name, Variant description, Variant namedLocation) {
        return Disp.call(getClass(), dispatch, "Add", name, description, namedLocation).getInt();
    }

}

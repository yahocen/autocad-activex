// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AcadPlotConfigurations — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PlotConfigurations extends ComCollection<PlotConfiguration> {

    public PlotConfigurations(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected PlotConfiguration wrap(Dispatch dispatch) {
        return new PlotConfiguration(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant name, Variant modelType) {
        return Disp.call(getClass(), dispatch, "Add", name, modelType).getInt();
    }

}

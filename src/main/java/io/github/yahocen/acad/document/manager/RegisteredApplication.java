// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadRegisteredApplication — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class RegisteredApplication extends AcadObject {

    public RegisteredApplication(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getName() {
        return getProperties("Name");
    }

    public RegisteredApplication setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

}

// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AutoCAD字典对象
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Dictionary AutoCAD字典",
    description = "AutoCAD中字典对象，用于组织和管理其他对象"
)
public class Dictionary extends AcadObject {

    public Dictionary(Dispatch acadElement) {
        super(acadElement);
    }

}

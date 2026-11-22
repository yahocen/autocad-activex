// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.ComFailException;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.common.ComCollection;

/**
 * AutoCAD选择集集合
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "SelectionSets AutoCAD选择集集合",
    description = "AutoCAD中所有选择集的集合"
)
public class SelectionSets extends ComCollection<SelectionSet> {

    public SelectionSets(Dispatch collection) {
        super(collection);
    }

    @Override
    protected SelectionSet wrap(Dispatch dispatch) {
        return new SelectionSet(dispatch);
    }

    @Operation(summary = "获取或添加选择集", description = "获取或添加选择集")
    public SelectionSet getOrAddItem(@Parameter(description = "选择集名称") String name) {
        try {
            return item(name);
        } catch (ComFailException e) {
            if((e.getMessage() != null && e.getMessage().contains("未找到主键"))) {
                return new SelectionSet(Disp.call(getClass(), dispatch, "Add", new Variant(name)).getDispatch());
            }
            throw e;
        }
    }

}

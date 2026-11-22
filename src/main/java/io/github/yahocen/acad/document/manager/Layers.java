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
 * AutoCAD图层集合
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-D9F62B4B-401E-4AE6-969F-3F7756819EB6">Layers Collection (ActiveX)</a>
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Layers AutoCAD图层集合",
    description = "AutoCAD中所有图层的集合"
)
public class Layers extends ComCollection<Layer> {

    public Layers(Dispatch acadElement) {
        super(acadElement);
    }

    @Override
    protected Layer wrap(Dispatch dispatch) {
        return new Layer(dispatch);
    }

    /**
     * 向集合中添加新图层
     * 
     * @param layerName 图层名称
     * @return 新添加的图层对象
     */
    @Operation(summary = "向集合中添加新图层", description = "向集合中添加新图层")
    public Layer add(@Parameter(description = "图层名称") String layerName) {
        return new Layer(Disp.call(getClass(), dispatch, "Add", new Variant(layerName)).getDispatch());
    }

    /**
     * 获取或添加图层
     * 
     * @param layerName 图层名称
     * @return 图层对象
     */
    @Operation(summary = "获取或添加图层", description = "获取或添加图层")
    public Layer getOrAdd(@Parameter(description = "图层名称") String layerName) {
        try {
            return item(layerName);
        }catch (ComFailException e) {
            if((e.getMessage() != null && e.getMessage().contains("未找到主键"))) {
                return add(layerName);
            }
            throw e;
        }
    }

}

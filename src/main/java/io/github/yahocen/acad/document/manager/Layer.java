// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AutoCAD图层对象
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Layer AutoCAD图层",
    description = "表示AutoCAD中的图层对象"
)
public class Layer extends AcadObject {

    public Layer(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 获取图层名称
     *
     * @return 图层名称
     */
    @Operation(summary = "获取图层名称", description = "获取图层名称")
    public String getName() {
        return getProperties("Name").toString();
    }

    /**
     * 获取图层的线型
     *
     * @return 线型名称
     */
    @Operation(summary = "获取图层的线型", description = "获取图层的线型")
    public String getLinetype() {
        return getProperties("Linetype").getString();
    }

    @Operation(summary = "设置图层的线型", description = "设置图层的线型")
    public void setLinetype(@Parameter(description = "线型名称") String lineType) {
        setProperties("Linetype", lineType);
    }

    /**
     * 获取图层的线宽
     *
     * @return 线宽
     */
    @Operation(summary = "获取图层的线宽", description = "获取图层的线宽")
    public int getLineweight() {
        return getProperties("Lineweight").getInt();
    }

    @Operation(summary = "设置图层的线宽", description = "设置图层的线宽")
    public void setLineweight(@Parameter(description = "线宽值") int scale) {
        setProperties("Lineweight", scale);
    }

    /**
     * 获取图层的真彩色对象
     *
     * @return AcCmColor 真彩色对象
     */
    @Operation(summary = "获取图层的真彩色", description = "获取图层的真彩色对象(AcCmColor)")
    public AcCmColor getTrueColor() {
        return new AcCmColor(getProperties("TrueColor").toDispatch());
    }

    @Operation(summary = "设置图层的真彩色", description = "设置图层的真彩色")
    public void setTrueColor(@Parameter(description = "真彩色对象") AcCmColor color) {
        setProperties("TrueColor", color);
    }

}

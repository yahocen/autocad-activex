// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComCollection;
import io.github.yahocen.acad.document.Block;

/**
 * AcadBlocks — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Blocks extends ComCollection<Block> {

    public Blocks(Dispatch dispatch) {
        super(dispatch);
    }

    @Override
    protected Block wrap(Dispatch dispatch) {
        return new Block(dispatch);
    }
    public Variant get_NewEnum() {
        return getProperties("_NewEnum");
    }

    public int add(Variant insertionPoint, Variant name) {
        return Disp.call(getClass(), dispatch, "Add", insertionPoint, name).getInt();
    }

    /**
     * 新建块定义。
     * <p>
     * 建议使用本重载而非 {@link #add(Variant, Variant)}：后者返回 int（按类型库生成的遗留签名），
     * 本方法返回可继续添加图元的 {@link Block} 包装。
     * </p>
     *
     * @param origin 块基点（世界坐标）
     * @param name   块名（唯一）
     * @return 新建的块定义
     */
    public Block add(io.github.yahocen.acad.document.base.Point origin, String name) {
        return new Block(Disp.call(getClass(), dispatch, "Add", origin.toVariant(), name).getDispatch());
    }

}

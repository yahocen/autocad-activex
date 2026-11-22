package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.acad.common.AcadEntityName;

/**
 * DgnUnderlay - DGN 底图（DGNATTACH 附着），ObjectName 与 DWF/PDF 底图同一命名规则（AcDb*Reference）。
 * <p>
 * AutoCAD COM 对所有底图只提供 IAcadUnderlay 接口，属性与方法全部继承自 {@link Underlay}。
 */
@AcadEntityName("AcDbDgnReference")
public class DgnUnderlay extends Underlay {

    public DgnUnderlay(Dispatch dispatch) {
        super(dispatch);
    }

}

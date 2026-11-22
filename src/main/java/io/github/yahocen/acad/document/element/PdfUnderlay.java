package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.acad.common.AcadEntityName;

/**
 * PdfUnderlay - PDF 底图（PDFATTACH 附着），ObjectName 已在 AutoCAD 2024 实测。
 * <p>
 * AutoCAD COM 对所有底图只提供 IAcadUnderlay 接口，属性与方法全部继承自 {@link Underlay}。
 */
@AcadEntityName("AcDbPdfReference")
public class PdfUnderlay extends Underlay {

    public PdfUnderlay(Dispatch dispatch) {
        super(dispatch);
    }

}

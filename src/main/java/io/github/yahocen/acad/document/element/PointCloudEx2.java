package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.PointCloudEx;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * PointCloudEx2 - AutoCAD PointCloudEx2 实体
 * <p>
 * AutoCAD 2024 的点云对象 ObjectName 为 AcDbPointCloudEx，COM 上实现 IAcadPointCloudEx2，
 * 映射到功能最全的本类（PointCloudEx 的方法都可用）。
 */
@AcadEntityName("AcDbPointCloudEx")
public class PointCloudEx2 extends PointCloudEx {

    public PointCloudEx2(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getSegmentation() {
        return getProperties("Segmentation");
    }

}
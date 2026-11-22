// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.base;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.common.Internal;
import io.github.yahocen.jacob.com.ComFailException;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.document.manager.AcCmColor;
import io.github.yahocen.acad.document.manager.Hyperlinks;
import io.github.yahocen.acad.document.manager.Layer;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-FCA7867D-E354-4F48-9C47-DB22C53A3460">IAcadEntity Interface (ActiveX)</a>
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "AcadEntity AutoCAD实体基类",
    description = "AutoCAD ActiveX实体的基类，提供实体的基本操作"
)
public class AcadEntity extends AcadObject {

    public AcadEntity(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 已确认存在的图层名缓存（跨实体共享）。
     * <p>setLayer(String) 命中时直接 put 图层名，跳过 getDocument/getLayers/Item/getName
     * 这条每次约 5 次跨进程往返的解析链；图层被外部删除时 put 失败会移出缓存并回退重建。</p>
     */
    private static final Set<String> KNOWN_LAYERS = ConcurrentHashMap.newKeySet();

    /**
     * 获取实体所在的图层
     * 
     * @return 图层名称
     */
    @Operation(summary = "获取实体所在的图层", description = "获取实体所在的图层")
    public String getLayer() {
        return this.getProperties("Layer").getString();
    }

    @Operation(summary = "设置实体所在的图层", description = "设置实体所在的图层")
    public AcadEntity setLayer(@Parameter(description = "图层名称") String layerName) {
        if (layerName != null && KNOWN_LAYERS.contains(layerName)) {
            try {
                setProperties("Layer", layerName);
                return this;
            } catch (ComFailException e) {
                // 图层可能被外部删除/改名：移出缓存，走完整路径重建
                KNOWN_LAYERS.remove(layerName);
            }
        }
        getDocument().getLayers().getOrAdd(layerName);
        KNOWN_LAYERS.add(layerName);
        setProperties("Layer", layerName);
        return this;
    }

    @Operation(summary = "设置实体所在的图层", description = "设置实体所在的图层")
    public AcadEntity setLayer(@Parameter(description = "图层对象") Layer layer) {
        this.setProperties("Layer", layer.getName());
        return this;
    }

    /**
     * 获取实体的线型
     * 
     * @return 线型名称
     */
    @Operation(summary = "获取实体的线型", description = "获取实体的线型")
    public String getLinetype() {
        return getProperties("Linetype").getString();
    }

    @Operation(summary = "设置实体的线型", description = "设置实体的线型")
    public AcadEntity setLinetype(@Parameter(description = "线型名称") String lineType) {
        this.setProperties("Linetype", lineType);
        return this;
    }

    /**
     * 获取实体的线型比例
     * 
     * @return 线型比例
     */
    @Operation(summary = "获取实体的线型比例", description = "获取实体的线型比例")
    public double getLinetypeScale() {
        return this.getProperties("LinetypeScale").getDouble();
    }

    @Operation(summary = "设置实体的线型比例", description = "设置实体的线型比例")
    public AcadEntity setLinetypeScale(@Parameter(description = "线型比例") double scale) {
        this.setProperties("LinetypeScale", scale);
        return this;
    }

    /**
     * 获取实体的线宽
     * 
     * @return 线宽
     */
    @Operation(summary = "获取实体的线宽", description = "获取实体的线宽")
    public int getLineweight() {
        return this.getProperties("Lineweight").getInt();
    }

    @Operation(summary = "设置实体的线宽", description = "设置实体的线宽")
    public AcadEntity setLineweight(@Parameter(description = "线宽值，单位 0.01mm：如 30=0.30mm，-1=随层") int scale) {
        this.setProperties("Lineweight", scale);
        return this;
    }

    /**
     * 复制实体
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-B036C50B-F504-486F-8179-85655206916E">Copy Method (ActiveX)</a>
     * 
     * @return 复制的实体对象
     */
    @Operation(summary = "复制实体", description = "复制实体")
    public AcadEntity copy() {
        return new AcadEntity(call("Copy").getDispatch());
    }

    @Operation(summary = "复制实体", description = "复制实体为指定类型")
    public <T extends AcadEntity> T copy(@Parameter(description = "实体类型") Class<T> clazz) {
        return Internal.newInstance(clazz, call("Copy").getDispatch());
    }

    /**
     * 沿向量移动对象。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-FF65DF24-D646-4DC2-894D-095E426DC2C1">Move Method (ActiveX) </a>
     * 
     * @param point1 指定移动向量第一个点的三维世界坐标。
     * @param point2 指定移动向量第二个点的三维世界坐标。
     */
    @Operation(summary = "沿向量移动对象", description = "沿向量移动对象")
    public void move(@Parameter(description = "移动向量第一个点的三维世界坐标") Point point1, @Parameter(description = "移动向量第二个点的三维世界坐标") Point point2) {
        this.call("Move", point1.toVariant(), point2.toVariant());
    }

    @Operation(summary = "转换实体类型", description = "转换实体类型")
    public <T extends AcadEntity> T convert(@Parameter(description = "目标实体类型") Class<T> clazz) {
        return Internal.newInstance(clazz, this.dispatch);
    }

    /**
     * 获取实体的名称。
     *
     * @return 实体名称
     */
    @Operation(summary = "获取实体名称", description = "获取实体名称")
    public String getEntityName() {
        return getProperties("EntityName").getString();
    }

    /**
     * 获取实体的类型。
     *
     * @return 实体类型
     */
    @Operation(summary = "获取实体类型", description = "获取实体类型")
    public int getEntityType() {
        return getProperties("EntityType").getInt();
    }

    /**
     * 获取实体颜色。
     *
     * @return 颜色索引（ACAD_COLOR）
     */
    @Operation(summary = "获取实体颜色", description = "获取实体颜色")
    public int getColor() {
        return getProperties("color").getInt();
    }

    /**
     * 设置实体颜色。
     *
     * @param colorIndex 颜色索引（ACAD_COLOR）
     * @return 当前实体
     */
    @Operation(summary = "设置实体颜色", description = "设置实体颜色")
    public AcadEntity setColor(@Parameter(description = "颜色索引") int colorIndex) {
        setProperties("color", colorIndex);
        return this;
    }

    /**
     * 获取实体真彩色。
     *
     * @return 真彩色对象
     */
    @Operation(summary = "获取实体真彩色", description = "获取实体真彩色")
    public AcCmColor getTrueColor() {
        return new AcCmColor(getProperties("TrueColor").getDispatch());
    }

    /**
     * 设置实体真彩色。
     *
     * @param trueColor 真彩色对象
     * @return 当前实体
     */
    @Operation(summary = "设置实体真彩色", description = "设置实体真彩色")
    public AcadEntity setTrueColor(@Parameter(description = "真彩色对象") AcCmColor trueColor) {
        setProperties("TrueColor", trueColor.getDispatch());
        return this;
    }

    /**
     * 获取实体可见性。
     *
     * @return 是否可见
     */
    @Operation(summary = "获取实体可见性", description = "获取实体可见性")
    public boolean getVisible() {
        return getProperties("Visible").getBoolean();
    }

    /**
     * 设置实体可见性。
     *
     * @param visible 是否可见
     * @return 当前实体
     */
    @Operation(summary = "设置实体可见性", description = "设置实体可见性")
    public AcadEntity setVisible(@Parameter(description = "是否可见") boolean visible) {
        setProperties("Visible", visible);
        return this;
    }

    /**
     * 环形阵列复制实体。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-EF1CF2F0-12C5-4C32-9B1B-7D5D5D5D5D5D">ArrayPolar Method (ActiveX)</a>
     *
     * @param numberOfObjects 阵列对象数量
     * @param angleToFill 填充角度（弧度）
     * @param centerPoint 阵列中心点
     * @return 阵列结果 Variant
     */
    @Operation(summary = "环形阵列复制实体", description = "环形阵列复制实体")
    public Variant arrayPolar(@Parameter(description = "阵列对象数量") int numberOfObjects, @Parameter(description = "填充角度（弧度）") double angleToFill, @Parameter(description = "阵列中心点") Point centerPoint) {
        return call("ArrayPolar", numberOfObjects, angleToFill, centerPoint.toVariant());
    }

    /**
     * 矩形阵列复制实体。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-8C9C4C9C-8C9C-4C9C-8C9C-8C9C8C9C8C9C">ArrayRectangular Method (ActiveX)</a>
     *
     * @param numberOfRows 行数
     * @param numberOfColumns 列数
     * @param numberOfLevels 层数
     * @param distBetweenRows 行间距
     * @param distBetweenCols 列间距
     * @param distBetweenLevels 层间距
     * @return 阵列结果 Variant
     */
    @Operation(summary = "矩形阵列复制实体", description = "矩形阵列复制实体")
    public Variant arrayRectangular(@Parameter(description = "行数") int numberOfRows, @Parameter(description = "列数") int numberOfColumns, @Parameter(description = "层数") int numberOfLevels, @Parameter(description = "行间距") double distBetweenRows, @Parameter(description = "列间距") double distBetweenCols, @Parameter(description = "层间距") double distBetweenLevels) {
        return call("ArrayRectangular", numberOfRows, numberOfColumns, numberOfLevels, distBetweenRows, distBetweenCols, distBetweenLevels);
    }

    /**
     * 高亮显示实体。
     *
     * @param highlightFlag 是否高亮
     */
    @Operation(summary = "高亮显示实体", description = "高亮显示实体")
    public void highlight(@Parameter(description = "是否高亮") boolean highlightFlag) {
        call("Highlight", highlightFlag);
    }

    /**
     * 绕基点旋转实体。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-5E9C2D3D-5E9C-4D3D-8E9C-5E9C8E9C8E9C">Rotate Method (ActiveX)</a>
     *
     * @param basePoint 旋转基点
     * @param rotationAngle 旋转角度（弧度）
     */
    @Operation(summary = "绕基点旋转实体", description = "绕基点旋转实体")
    public void rotate(@Parameter(description = "旋转基点") Point basePoint, @Parameter(description = "旋转角度（弧度）") double rotationAngle) {
        call("Rotate", basePoint.toVariant(), rotationAngle);
    }

    /**
     * 绕三维轴旋转实体。
     *
     * @param point1 轴上第一个点
     * @param point2 轴上第二个点
     * @param rotationAngle 旋转角度（弧度）
     */
    @Operation(summary = "绕三维轴旋转实体", description = "绕三维轴旋转实体")
    public void rotate3D(@Parameter(description = "轴上第一个点") Point point1, @Parameter(description = "轴上第二个点") Point point2, @Parameter(description = "旋转角度（弧度）") double rotationAngle) {
        call("Rotate3D", point1.toVariant(), point2.toVariant(), rotationAngle);
    }

    /**
     * 镜像复制实体（返回镜像后的新实体）。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-9C8C8C9C-8C9C-4C9C-8C9C-8C9C8C9C8C9C">Mirror Method (ActiveX)</a>
     *
     * @param point1 镜像轴第一个点
     * @param point2 镜像轴第二个点
     * @return 镜像后的实体
     */
    @Operation(summary = "镜像复制实体", description = "镜像复制实体")
    public AcadEntity mirror(@Parameter(description = "镜像轴第一个点") Point point1, @Parameter(description = "镜像轴第二个点") Point point2) {
        return new AcadEntity(call("Mirror", point1.toVariant(), point2.toVariant()).getDispatch());
    }

    /**
     * 绕三维平面镜像复制实体。
     *
     * @param point1 镜像平面第一个点
     * @param point2 镜像平面第二个点
     * @param point3 镜像平面第三个点
     * @return 镜像后的实体
     */
    @Operation(summary = "绕三维平面镜像复制实体", description = "绕三维平面镜像复制实体")
    public AcadEntity mirror3D(@Parameter(description = "镜像平面第一个点") Point point1, @Parameter(description = "镜像平面第二个点") Point point2, @Parameter(description = "镜像平面第三个点") Point point3) {
        return new AcadEntity(call("Mirror3D", point1.toVariant(), point2.toVariant(), point3.toVariant()).getDispatch());
    }

    /**
     * 缩放实体。
     *
     * @param basePoint 缩放基点
     * @param scaleFactor 缩放因子
     */
    @Operation(summary = "缩放实体", description = "缩放实体")
    public void scaleEntity(@Parameter(description = "缩放基点") Point basePoint, @Parameter(description = "缩放因子") double scaleFactor) {
        call("ScaleEntity", basePoint.toVariant(), scaleFactor);
    }

    /**
     * 用变换矩阵变换实体。
     *
     * @param transformationMatrix 4x4 变换矩阵（Variant 形式）
     */
    @Operation(summary = "用变换矩阵变换实体", description = "用变换矩阵变换实体")
    public void transformBy(@Parameter(description = "4x4 变换矩阵") Variant transformationMatrix) {
        call("TransformBy", transformationMatrix);
    }

    /**
     * 更新实体。
     */
    @Operation(summary = "更新实体", description = "更新实体")
    public void update() {
        call("Update");
    }

    /**
     * 获取实体的包围盒。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-2E6B34A3-4E4A-4E7A-8E2B-5E6B8E6B8E6B">GetBoundingBox Method (ActiveX)</a>
     *
     * @return 两个点组成的数组：[0] 最小点 (MinPoint)，[1] 最大点 (MaxPoint)
     */
    @Operation(summary = "获取实体的包围盒", description = "获取实体的包围盒，返回 [最小点, 最大点]")
    public Point[] getBoundingBox() {
        var minHolder = new Variant();
        var minVar = new Variant();
        minVar.putVariantRef(minHolder);
        var maxHolder = new Variant();
        var maxVar = new Variant();
        maxVar.putVariantRef(maxHolder);

        Disp.invokev(getClass(), dispatch, "GetBoundingBox", Dispatch.Method,
                new Variant[]{minVar, maxVar}, new int[2]);

        var min = Point.of(minHolder.toSafeArray());
        var max = Point.of(maxHolder.toSafeArray());
        minVar.safeRelease();
        maxVar.safeRelease();
        return new Point[]{min, max};
    }

    /**
     * 求实体与另一实体的交点。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-9B7B7B9B-9B7B-4B7B-9B7B-9B7B9B7B9B7B">IntersectWith Method (ActiveX)</a>
     *
     * @param intersectObject 相交对象
     * @param option 延伸选项（AcExtendOption：0=都不延伸，1=延伸本实体，2=延伸对方，3=两者都延伸）
     * @return 交点坐标 Variant
     */
    @Operation(summary = "求实体与另一实体的交点", description = "求实体与另一实体的交点")
    public Variant intersectWith(@Parameter(description = "相交对象") AcadEntity intersectObject, @Parameter(description = "延伸选项") int option) {
        return call("IntersectWith", intersectObject.getDispatch(), option);
    }

    /**
     * 获取实体打印样式名称。
     *
     * @return 打印样式名称
     */
    @Operation(summary = "获取实体打印样式名称", description = "获取实体打印样式名称")
    public String getPlotStyleName() {
        return getProperties("PlotStyleName").getString();
    }

    /**
     * 设置实体打印样式名称。
     *
     * @param plotStyleName 打印样式名称
     * @return 当前实体
     */
    @Operation(summary = "设置实体打印样式名称", description = "设置实体打印样式名称")
    public AcadEntity setPlotStyleName(@Parameter(description = "打印样式名称") String plotStyleName) {
        setProperties("PlotStyleName", plotStyleName);
        return this;
    }

    /**
     * 获取实体透明度。
     *
     * @return 透明度（字符串形式，如 "ByLayer" 或百分比数值）
     */
    @Operation(summary = "获取实体透明度", description = "获取实体透明度")
    public String getEntityTransparency() {
        return getProperties("EntityTransparency").getString();
    }

    /**
     * 设置实体透明度。
     *
     * @param transparency 透明度
     * @return 当前实体
     */
    @Operation(summary = "设置实体透明度", description = "设置实体透明度")
    public AcadEntity setEntityTransparency(@Parameter(description = "透明度") String transparency) {
        setProperties("EntityTransparency", transparency);
        return this;
    }

    /**
     * 获取实体关联的超链接集合。
     *
     * @return 超链接集合
     */
    @Operation(summary = "获取实体关联的超链接集合", description = "获取实体关联的超链接集合")
    public Hyperlinks getHyperlinks() {
        return new Hyperlinks(getProperties("Hyperlinks").getDispatch());
    }

    /**
     * 获取实体材质。
     *
     * @return 材质名称
     */
    @Operation(summary = "获取实体材质", description = "获取实体材质")
    public String getMaterial() {
        return getProperties("Material").getString();
    }

    /**
     * 设置实体材质。
     *
     * @param material 材质名称
     * @return 当前实体
     */
    @Operation(summary = "设置实体材质", description = "设置实体材质")
    public AcadEntity setMaterial(@Parameter(description = "材质名称") String material) {
        setProperties("Material", material);
        return this;
    }

}

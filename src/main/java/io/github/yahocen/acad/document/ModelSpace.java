// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.SafeArray;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.common.ComCollection;
import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.constant.AlignmentEnum;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.base.AcadObject;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.document.element.*;
import io.github.yahocen.acad.util.EntityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含所有模型空间实体的特殊 Block 对象。
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-C541B6F2-1279-4D1C-8DC0-788F27F644EF">dispatch Collection (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "ModelSpace 模型空间",
    description = "AutoCAD 模型空间对象，用于操作模型空间中的实体"
)
public class ModelSpace extends ComCollection<AcadEntity> {

    /**
     * 构造函数，初始化模型空间对象
     * @param dispatch AutoCAD 的模型空间 Dispatch 对象
     */
    public ModelSpace(Dispatch dispatch) {
        super(dispatch);
    }

    /**
     * 将 Dispatch 包装为对应的实体类型。
     * 使用 {@link EntityUtil#parse(Dispatch)} 根据 ObjectName 自动分派。
     *
     * @param dispatch 底层 Dispatch 对象
     * @return 包装后的 AcadEntity 子类
     */
    @Override
    protected AcadEntity wrap(Dispatch dispatch) {
        return EntityUtil.parse(dispatch);
    }

    /**
     * 在模型空间中添加一条直线
     * @param p1 起点坐标
     * @param p2 终点坐标
     * @return 新创建的 Line 对象
     */
    @Operation(summary = "在模型空间中添加一条直线", description = "在模型空间中添加一条直线")
    @Parameter(name = "p1", description = "起点坐标")
    @Parameter(name = "p2", description = "终点坐标")
    public Line addLine(final Point p1, final Point p2) {
        return new Line(Disp.call(getClass(), dispatch,"AddLine", p1.toVariant(), p2.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个圆
     * @param center 圆心坐标
     * @param radius 半径
     * @return 新创建的 Circle 对象
     */
    @Operation(summary = "在模型空间中添加一个圆", description = "在模型空间中添加一个圆")
    @Parameter(name = "center", description = "圆心坐标")
    @Parameter(name = "radius", description = "半径")
    public Circle addCircle(final Point center, double radius) {
        return new Circle(Disp.call(getClass(), dispatch,"AddCircle", center.toVariant(), new Variant(radius)).toDispatch());
    }

    /**
     * 在模型空间中添加一个矩形
     * @param firstCorner 第一个角点坐标
     * @param secondCorner 第二个角点坐标
     * @return 新创建的 Rectangle 对象
     */
    @Operation(summary = "在模型空间中添加一个矩形", description = "在模型空间中添加一个矩形")
    @Parameter(name = "firstCorner", description = "第一个角点坐标")
    @Parameter(name = "secondCorner", description = "第二个角点坐标")
    public Rectangle addRectangle(final Point firstCorner, final Point secondCorner) {
        // AutoCAD COM Automation 没有 AddRectangle 方法，用 4 条线 + 面域替代
        double x1 = Math.min(firstCorner.getX(), secondCorner.getX());
        double y1 = Math.min(firstCorner.getY(), secondCorner.getY());
        double x2 = Math.max(firstCorner.getX(), secondCorner.getX());
        double y2 = Math.max(firstCorner.getY(), secondCorner.getY());
        // 用 4 条线画矩形，返回的 Rectangle 封装底边线作为代表实体
        var bottom = Disp.call(getClass(), dispatch,"AddLine", Point.of(x1, y1).toVariant(), Point.of(x2, y1).toVariant()).toDispatch();
        Disp.call(getClass(), dispatch,"AddLine", Point.of(x2, y1).toVariant(), Point.of(x2, y2).toVariant());
        Disp.call(getClass(), dispatch,"AddLine", Point.of(x2, y2).toVariant(), Point.of(x1, y2).toVariant());
        Disp.call(getClass(), dispatch,"AddLine", Point.of(x1, y2).toVariant(), Point.of(x1, y1).toVariant());
        return new Rectangle(bottom);
    }

    /**
     * 在模型空间中添加一段文本
     * @param text 文本内容
     * @param insertionPoint 插入点坐标
     * @param height 文本高度
     * @return 新创建的 Text 对象
     */
    @Operation(summary = "在模型空间中添加一段文本", description = "在模型空间中添加一段文本")
    @Parameter(name = "text", description = "文本内容")
    @Parameter(name = "insertionPoint", description = "插入点坐标")
    @Parameter(name = "height", description = "文本高度")
    public Text addText(String text, final Point insertionPoint, double height) {
        return new Text(Disp.call(getClass(), dispatch,"AddText", text, insertionPoint.toVariant(), new Variant(height)).toDispatch());
    }

    /**
     * 在模型空间中添加一段文本
     * @param text 文本内容
     * @param centerPoint 插入点坐标
     * @param height 文本高度
     * @return 新创建的 Text 对象
     */
    @Operation(summary = "在模型空间中添加一段居中对齐文本", description = "在模型空间中添加一段居中对齐的文本")
    @Parameter(name = "text", description = "文本内容")
    @Parameter(name = "centerPoint", description = "插入点坐标")
    @Parameter(name = "height", description = "文本高度")
    public Text addTextAlignmentMiddleCenter(String text, final Point centerPoint, double height) {
        return addText(text, centerPoint, height)
                .setAlignment(AlignmentEnum.MiddleCenter.getAlignment())
                .setTextAlignmentPoint(centerPoint);
    }

    /**
     * 在模型空间中添加一条多段线
     * @param points 多段线的顶点坐标数组
     * @return 新创建的 Polyline 对象
     */
    @Operation(summary = "在模型空间中添加一条多段线", description = "在模型空间中添加一条多段线")
    @Parameter(name = "points", description = "多段线的顶点坐标数组")
    public LWPolyline addPolyline(Point... points) {
        // AutoCAD COM 的 AddPolyline 需要 Variant(SafeArray(Variant)) 格式，
        // 在某些 AutoCAD 版本上 Invoke 失败；改用 AddLightWeightPolyline（double 数组）更稳定。
        // 实测 AutoCAD 2024 创建出的对象是 AcDbPolyline（轻量多段线），所以返回 LWPolyline 而不是 Polyline（AcDb2dPolyline）
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("多段线至少需要 2 个顶点");
        }
        return addLightWeightPolyline(points);
    }

    @Operation(summary = "在模型空间中添加一条多段线", description = "在模型空间中添加一条多段线")
    @Parameter(name = "points", description = "多段线的顶点坐标列表")
    public LWPolyline addPolyline(List<Point> points) {
        return addPolyline(points.toArray(new Point[]{}));
    }

    /**
     * 在模型空间中添加一个圆弧
     * @param center 圆心坐标
     * @param radius 半径
     * @param startAngle 起始角度（弧度）
     * @param endAngle 结束角度（弧度）
     * @return 新创建的 Arc 对象
     */
    @Operation(summary = "在模型空间中添加一个圆弧", description = "在模型空间中添加一个圆弧")
    @Parameter(name = "center", description = "圆心坐标")
    @Parameter(name = "radius", description = "半径")
    @Parameter(name = "startAngle", description = "起始角度（弧度）")
    @Parameter(name = "endAngle", description = "结束角度（弧度）")
    public Arc addArc(Point center, double radius, double startAngle, double endAngle) {
        return new Arc(Disp.call(getClass(), dispatch,"AddArc", center.toVariant(), new Variant(radius), new Variant(startAngle), new Variant(endAngle)).toDispatch());
    }

    /**
     * 在模型空间中添加一个长方体
     * @param origin 底面第一个角点坐标（与 WCS 对齐）
     * @param length X 方向长度
     * @param width Y 方向宽度
     * @param height Z 方向高度
     * @return 新创建的 Box 对象
     */
    @Operation(summary = "在模型空间中添加一个长方体", description = "在模型空间中添加一个长方体（Origin + 长宽高，与 WCS 对齐）")
    @Parameter(name = "origin", description = "底面第一个角点坐标（与 WCS 对齐）")
    @Parameter(name = "length", description = "X 方向长度")
    @Parameter(name = "width", description = "Y 方向宽度")
    @Parameter(name = "height", description = "Z 方向高度")
    public Box addBox(Point origin, double length, double width, double height) {
        return new Box(Disp.call(getClass(), dispatch,"AddBox",
                origin.toVariant(), new Variant(length), new Variant(width), new Variant(height)).toDispatch());
    }

    /**
     * 在模型空间中添加一个圆锥
     * @param center 圆锥底面中心坐标
     * @param radius 底面半径
     * @param height 圆锥高度
     * @return 新创建的 Cone 对象
     */
    @Operation(summary = "在模型空间中添加一个圆锥", description = "在模型空间中添加一个圆锥")
    @Parameter(name = "center", description = "圆锥底面中心坐标")
    @Parameter(name = "radius", description = "底面半径")
    @Parameter(name = "height", description = "圆锥高度")
    public Cone addCone(Point center, double radius, double height) {
        return new Cone(Disp.call(getClass(), dispatch,"AddCone", center.toVariant(), new Variant(radius), new Variant(height)).toDispatch());
    }

    /**
     * 在模型空间中添加一个圆柱
     * @param center 圆柱底面中心坐标
     * @param radius 底面半径
     * @param height 圆柱高度
     * @return 新创建的 Cylinder 对象
     */
    @Operation(summary = "在模型空间中添加一个圆柱", description = "在模型空间中添加一个圆柱")
    @Parameter(name = "center", description = "圆柱底面中心坐标")
    @Parameter(name = "radius", description = "底面半径")
    @Parameter(name = "height", description = "圆柱高度")
    public Cylinder addCylinder(Point center, double radius, double height) {
        return new Cylinder(Disp.call(getClass(), dispatch,"AddCylinder", center.toVariant(), new Variant(radius), new Variant(height)).toDispatch());
    }

    /**
     * 在模型空间中添加一个椭圆
     * @param center 椭圆中心坐标
     * @param majorAxis 主轴向量（从中心指向主轴端点的 3D 向量，如 (20,0,0)）
     * @param ratio 长轴与短轴的比例
     * @return 新创建的 Ellipse 对象
     */
    @Operation(summary = "在模型空间中添加一个椭圆", description = "在模型空间中添加一个椭圆")
    @Parameter(name = "center", description = "椭圆中心坐标")
    @Parameter(name = "majorAxis", description = "主轴向量（从中心指向主轴端点的 3D 向量）")
    @Parameter(name = "ratio", description = "长轴与短轴的比例")
    public Ellipse addEllipse(Point center, Point majorAxis, double ratio) {
        return new Ellipse(Disp.call(getClass(), dispatch,"AddEllipse",
                center.toVariant(), majorAxis.toVariant(), new Variant(ratio)).toDispatch());
    }

    /**
     * 在模型空间中添加一条轻量级多段线
     * @param points 指定多段线顶点的 2D OCS 坐标数组。构建轻量级多段线至少需要两个点（四个元素）。数组大小必须是 2 的倍数
     * @return 新创建的 Polyline 对象
     */
    @Operation(summary = "在模型空间中添加一条轻量级多段线", description = "在模型空间中添加一条轻量级多段线")
    @Parameter(name = "points", description = "指定多段线顶点的 2D OCS 坐标数组。构建轻量级多段线至少需要两个点（四个元素）。数组大小必须是 2 的倍数")
    public LWPolyline addLightWeightPolyline(Point... points) {
        SafeArray safeArray = new SafeArray(Variant.VariantDouble, points.length * 2);
        // 填充坐标数据
        for (int i = 0; i < points.length; i++) {
            // 偶数索引存 X
            safeArray.setDouble(i * 2, points[i].getX());
            // 奇数索引存 Y
            safeArray.setDouble(i * 2 + 1, points[i].getY());
        }
        return new LWPolyline(Disp.call(getClass(), dispatch,"AddLightWeightPolyline", new Variant(safeArray)).toDispatch());
    }

    @Operation(summary = "在模型空间中添加一条轻量级多段线", description = "在模型空间中添加一条轻量级多段线")
    @Parameter(name = "points", description = "多段线的顶点坐标列表")
    public LWPolyline addLightWeightPolyline(List<Point> points) {
        return addLightWeightPolyline(points.toArray(new Point[]{}));
    }

    /**
     * 在模型空间中添加一条射线
     * @param basePoint 射线起点坐标
     * @param secondPoint 射线方向上的另一个点坐标
     * @return 新创建的 Ray 对象
     */
    @Operation(summary = "在模型空间中添加一条射线", description = "在模型空间中添加一条射线")
    @Parameter(name = "basePoint", description = "射线起点坐标")
    @Parameter(name = "secondPoint", description = "射线方向上的另一个点坐标")
    public Ray addRay(Point basePoint, Point secondPoint) {
        return new Ray(Disp.call(getClass(), dispatch,"AddRay", basePoint.toVariant(), secondPoint.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个球体
     * @param center 球心坐标
     * @param radius 半径
     * @return 新创建的 Sphere 对象
     */
    @Operation(summary = "在模型空间中添加一个球体", description = "在模型空间中添加一个球体")
    @Parameter(name = "center", description = "球心坐标")
    @Parameter(name = "radius", description = "半径")
    public Sphere addSphere(Point center, double radius) {
        return new Sphere(Disp.call(getClass(), dispatch,"AddSphere", center.toVariant(), new Variant(radius)).toDispatch());
    }

    /**
     * 在模型空间中添加一条样条曲线（起止切向量取零向量）
     * @param points 样条曲线的控制点坐标数组（至少 2 个）
     * @return 新创建的 Spline 对象
     */
    @Operation(summary = "在模型空间中添加一条样条曲线", description = "在模型空间中添加一条样条曲线")
    @Parameter(name = "points", description = "样条曲线的控制点坐标数组")
    public Spline addSpline(Point... points) {
        return addSpline(points, Point.ORIGIN, Point.ORIGIN);
    }

    /**
     * 在模型空间中添加一条样条曲线
     * @param points 样条曲线的控制点坐标数组（至少 2 个）
     * @param startTangent 起点切向量
     * @param endTangent 终点切向量
     * @return 新创建的 Spline 对象
     */
    @Operation(summary = "在模型空间中添加一条样条曲线", description = "在模型空间中添加一条样条曲线（带起止切向量）")
    @Parameter(name = "points", description = "样条曲线的控制点坐标数组")
    @Parameter(name = "startTangent", description = "起点切向量")
    @Parameter(name = "endTangent", description = "终点切向量")
    public Spline addSpline(Point[] points, Point startTangent, Point endTangent) {
        // AddSpline(PointsArray, StartTangent, EndTangent) 三参必传；
        // 控制点用平铺 double 数组（与 Add3DPoly 同形态），切向量用 3D 向量
        return new Spline(Disp.call(getClass(), dispatch, "AddSpline",
                points3DArray(points), startTangent.toVariant(), endTangent.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个环面
     * @param center 环面中心坐标
     * @param majorRadius 主半径
     * @param minorRadius 次半径
     * @return 新创建的 Torus 对象
     */
    @Operation(summary = "在模型空间中添加一个环面", description = "在模型空间中添加一个环面")
    @Parameter(name = "center", description = "环面中心坐标")
    @Parameter(name = "majorRadius", description = "主半径")
    @Parameter(name = "minorRadius", description = "次半径")
    public Torus addTorus(Point center, double majorRadius, double minorRadius) {
        return new Torus(Disp.call(getClass(), dispatch,"AddTorus", center.toVariant(), new Variant(majorRadius), new Variant(minorRadius)).toDispatch());
    }

    /**
     * 在模型空间中添加一个对齐尺寸标注
     * @param point1 起点坐标
     * @param point2 终点坐标
     * @param textPosition 文本位置坐标
     * @return 新创建的 DimAligned 对象
     */
    @Operation(summary = "在模型空间中添加一个对齐尺寸标注", description = "在模型空间中添加一个对齐尺寸标注")
    @Parameter(name = "point1", description = "起点坐标")
    @Parameter(name = "point2", description = "终点坐标")
    @Parameter(name = "textPosition", description = "文本位置坐标")
    public DimAligned addDimAligned(final Point point1, final Point point2, final Point textPosition) {
        return new DimAligned(Disp.call(getClass(), dispatch,"AddDimAligned", point1.toVariant(), point2.toVariant(), textPosition.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个带有自定义文本的对齐尺寸标注
     * @param point1 起点坐标
     * @param point2 终点坐标
     * @param textPosition 文本位置坐标
     * @param text 自定义文本内容
     * @return 新创建的 DimAligned 对象
     */
    @Operation(summary = "在模型空间中添加一个带有自定义文本的对齐尺寸标注", description = "在模型空间中添加一个带有自定义文本的对齐尺寸标注")
    @Parameter(name = "point1", description = "起点坐标")
    @Parameter(name = "point2", description = "终点坐标")
    @Parameter(name = "textPosition", description = "文本位置坐标")
    @Parameter(name = "text", description = "自定义文本内容")
    public DimAligned addDimAlignedWithText(final Point point1, final Point point2, final Point textPosition, String text) {
        DimAligned dimAligned = new DimAligned(Disp.call(getClass(), dispatch,"AddDimAligned", point1.toVariant(), point2.toVariant(), textPosition.toVariant()).toDispatch());
        dimAligned.setProperties("Text", text);
        return dimAligned;
    }

    /**
     * 在模型空间中添加一个旋转尺寸标注
     * @param point1 起点坐标
     * @param point2 终点坐标
     * @param textPosition 文本位置坐标
     * @param rotationAngle 旋转角度（弧度）
     * @return 新创建的 DimRotated 对象
     */
    @Operation(summary = "在模型空间中添加一个旋转尺寸标注", description = "在模型空间中添加一个旋转尺寸标注")
    @Parameter(name = "point1", description = "起点坐标")
    @Parameter(name = "point2", description = "终点坐标")
    @Parameter(name = "textPosition", description = "文本位置坐标")
    @Parameter(name = "rotationAngle", description = "旋转角度（弧度）")
    public DimRotated addDimRotated(final Point point1, final Point point2, final Point textPosition, double rotationAngle) {
        return new DimRotated(Disp.call(getClass(), dispatch,"AddDimRotated", point1.toVariant(), point2.toVariant(), textPosition.toVariant(), new Variant(rotationAngle)).toDispatch());
    }

    /**
     * 在模型空间中添加一个半径尺寸标注
     * @param center 圆心坐标
     * @param chordPoint 引出点坐标
     * @param leaderLength 引线长度
     * @return 新创建的 DimRadial 对象
     */
    @Operation(summary = "在模型空间中添加一个半径尺寸标注", description = "在模型空间中添加一个半径尺寸标注")
    @Parameter(name = "center", description = "圆心坐标")
    @Parameter(name = "chordPoint", description = "引出点坐标")
    @Parameter(name = "leaderLength", description = "引线长度")
    public DimRadial addDimRadial(Point center, Point chordPoint, double leaderLength) {
        return new DimRadial(Disp.call(getClass(), dispatch,"AddDimRadial", center.toVariant(), chordPoint.toVariant(), new Variant(leaderLength)).toDispatch());
    }

    /**
     * 在模型空间中添加一个直径尺寸标注
     * @param chordPoint 第一个引出点坐标
     * @param farChordPoint 第二个引出点坐标（对径点）
     * @param leaderLength 引线长度
     * @return 新创建的 DimDiametric 对象
     */
    @Operation(summary = "在模型空间中添加一个直径尺寸标注", description = "在模型空间中添加一个直径尺寸标注")
    @Parameter(name = "chordPoint", description = "第一个引出点坐标")
    @Parameter(name = "farChordPoint", description = "第二个引出点坐标（对径点）")
    @Parameter(name = "leaderLength", description = "引线长度")
    public DimDiametric addDimDiametric(Point chordPoint, Point farChordPoint, double leaderLength) {
        return new DimDiametric(Disp.call(getClass(), dispatch,"AddDimDiametric", chordPoint.toVariant(), farChordPoint.toVariant(), new Variant(leaderLength)).toDispatch());
    }

    /**
     * 在模型空间中添加一个角度尺寸标注（三点法）
     * @param angleVertex 角度顶点坐标
     * @param firstEndPoint 第一条边的端点坐标
     * @param secondEndPoint 第二条边的端点坐标
     * @param textPosition 文本位置坐标
     * @return 新创建的 DimAngular 对象
     */
    @Operation(summary = "在模型空间中添加一个角度尺寸标注", description = "在模型空间中添加一个角度尺寸标注（三点法）")
    @Parameter(name = "angleVertex", description = "角度顶点坐标")
    @Parameter(name = "firstEndPoint", description = "第一条边的端点坐标")
    @Parameter(name = "secondEndPoint", description = "第二条边的端点坐标")
    @Parameter(name = "textPosition", description = "文本位置坐标")
    public DimAngular addDimAngular(Point angleVertex, Point firstEndPoint, Point secondEndPoint, Point textPosition) {
        return new DimAngular(Disp.call(getClass(), dispatch,"AddDimAngular", angleVertex.toVariant(), firstEndPoint.toVariant(), secondEndPoint.toVariant(), textPosition.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个坐标尺寸标注
     * @param definitionPoint 定义点（测量点）坐标
     * @param leaderPoint 引出点坐标
     * @param useXAxis 是否为 X 轴坐标标注（true 为 X 轴，false 为 Y 轴）
     * @return 新创建的 DimOrdinate 对象
     */
    @Operation(summary = "在模型空间中添加一个坐标尺寸标注", description = "在模型空间中添加一个坐标尺寸标注")
    @Parameter(name = "definitionPoint", description = "定义点（测量点）坐标")
    @Parameter(name = "leaderPoint", description = "引出点坐标")
    @Parameter(name = "useXAxis", description = "是否为 X 轴坐标标注（true 为 X 轴，false 为 Y 轴）")
    public DimOrdinate addDimOrdinate(Point definitionPoint, Point leaderPoint, boolean useXAxis) {
        return new DimOrdinate(Disp.call(getClass(), dispatch,"AddDimOrdinate", definitionPoint.toVariant(), leaderPoint.toVariant(), new Variant(useXAxis)).toDispatch());
    }

    /**
     * 在模型空间中添加一个多行文字对象
     * @param insertionPoint 插入点坐标
     * @param width 文字宽度（0 表示无宽度限制）
     * @param text 文本内容
     * @return 新创建的 MText 对象
     */
    @Operation(summary = "在模型空间中添加一个多行文字对象", description = "在模型空间中添加一个多行文字对象")
    @Parameter(name = "insertionPoint", description = "插入点坐标")
    @Parameter(name = "width", description = "文字宽度（0 表示无宽度限制）")
    @Parameter(name = "text", description = "文本内容")
    public MText addMText(Point insertionPoint, double width, String text) {
        return new MText(Disp.call(getClass(), dispatch,"AddMText", insertionPoint.toVariant(), new Variant(width), text).toDispatch());
    }

    /**
     * 在模型空间中添加一个点实体
     * @param point 点坐标
     * @return 新创建的 PointEntity 对象
     */
    @Operation(summary = "在模型空间中添加一个点实体", description = "在模型空间中添加一个点实体")
    @Parameter(name = "point", description = "点坐标")
    public PointEntity addPoint(Point point) {
        return new PointEntity(Disp.call(getClass(), dispatch,"AddPoint", point.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个公差标注
     * @param insertionPoint 插入点坐标
     * @param direction 方向向量
     * @param text 公差文本内容
     * @return 新创建的 Tolerance 对象
     */
    @Operation(summary = "在模型空间中添加一个公差标注", description = "在模型空间中添加一个公差标注")
    @Parameter(name = "insertionPoint", description = "插入点坐标")
    @Parameter(name = "direction", description = "方向向量")
    @Parameter(name = "text", description = "公差文本内容")
    public Tolerance addTolerance(Point insertionPoint, Point direction, String text) {
        return new Tolerance(Disp.call(getClass(), dispatch,"AddTolerance", text, insertionPoint.toVariant(), direction.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个 3D 面
     * @param point1 第一个顶点坐标
     * @param point2 第二个顶点坐标
     * @param point3 第三个顶点坐标
     * @param point4 第四个顶点坐标（可与第三个顶点相同以创建三角形面）
     * @return 新创建的 Face3D 对象
     */
    @Operation(summary = "在模型空间中添加一个 3D 面", description = "在模型空间中添加一个 3D 面")
    @Parameter(name = "point1", description = "第一个顶点坐标")
    @Parameter(name = "point2", description = "第二个顶点坐标")
    @Parameter(name = "point3", description = "第三个顶点坐标")
    @Parameter(name = "point4", description = "第四个顶点坐标（可与第三个顶点相同以创建三角形面）")
    public Face3D addFace3D(Point point1, Point point2, Point point3, Point point4) {
        return new Face3D(Disp.call(getClass(), dispatch,"Add3DFace", point1.toVariant(), point2.toVariant(), point3.toVariant(), point4.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个 3D 多段线
     * @param points 多段线的顶点坐标数组
     * @return 新创建的 Polyline3D 对象
     */
    @Operation(summary = "在模型空间中添加一个 3D 多段线", description = "在模型空间中添加一个 3D 多段线")
    @Parameter(name = "points", description = "多段线的顶点坐标数组")
    public Polyline3D addPolyline3D(Point... points) {
        var safeArray = new SafeArray(Variant.VariantDouble, points.length * 3);
        for (int i = 0; i < points.length; i++) {
            safeArray.setDouble(i * 3, points[i].getX());
            safeArray.setDouble(i * 3 + 1, points[i].getY());
            safeArray.setDouble(i * 3 + 2, points[i].getZ());
        }
        return new Polyline3D(Disp.call(getClass(), dispatch,"Add3DPoly", new Variant(safeArray)).toDispatch());
    }

    /** 渐变填充（hatchObjectType=1）合法的图案名称集合 */
    private static final java.util.Set<String> GRADIENT_PATTERN_NAMES = java.util.Set.of(
            "LINEAR", "CYLINDER", "INVCYLINDER", "SPHERICAL", "HEMISPHERICAL",
            "CURVED", "INVSPHERICAL", "INVHEMISPHERICAL", "INVCURVED");

    /**
     * 校验渐变填充图案名称，非法时抛出带可用值列表的异常（避免 AutoCAD 含糊的"空句柄"报错）。
     */
    private static void validateGradientName(String patternName) {
        if (patternName == null || !GRADIENT_PATTERN_NAMES.contains(patternName.toUpperCase())) {
            throw new IllegalArgumentException(
                    "渐变填充图案名称非法：\"" + patternName + "\"。" +
                    "合法值为：" + GRADIENT_PATTERN_NAMES + "（大小写不敏感）");
        }
    }

    /**
     * 在模型空间中添加一个 Hatch 填充对象
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-0D2C36E4-9D1F-441B-A598-3432470A7526">AddHatch Method (ActiveX)</a>
     * @param patternType 图案类型：0=预定义，1=用户定义，2=自定义
     * @param patternName 图案名称（如 "SOLID"、"ANSI31"；渐变填充使用 "LINEAR"、"SPHERICAL" 等）
     * @param associativity 是否关联边界
     * @return 新创建的 Hatch 对象
     */
    @Operation(summary = "在模型空间中添加一个 Hatch 填充对象", description = "在模型空间中添加一个 Hatch 填充对象")
    @Parameter(name = "patternType", description = "图案类型：0=预定义（含渐变填充），1=用户定义，2=自定义")
    @Parameter(name = "patternName", description = "图案名称（如 SOLID、ANSI31；渐变填充仅限：LINEAR/CYLINDER/INVCYLINDER/SPHERICAL/HEMISPHERICAL/CURVED/INVSPHERICAL/INVHEMISPHERICAL/INVCURVED）")
    @Parameter(name = "associativity", description = "是否关联边界")
    public Hatch addHatch(int patternType, String patternName, boolean associativity) {
        return new Hatch(Disp.call(getClass(), dispatch,"AddHatch", new Variant(patternType), patternName, new Variant(associativity)).toDispatch());
    }

    /**
     * 在模型空间中添加一个 Hatch 填充对象（指定 HatchObjectType，可创建渐变填充）
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-0D2C36E4-9D1F-441B-A598-3432470A7526">AddHatch Method (ActiveX)</a>
     * @param patternType 图案类型：0=预定义，1=用户定义，2=自定义。渐变填充时必须用 0
     * @param patternName 图案名称（如 "SOLID"、"ANSI31"；渐变填充使用 "LINEAR"、"SPHERICAL" 等）
     * @param associativity 是否关联边界
     * @param hatchObjectType 填充对象类型：0=普通填充，1=渐变填充
     * @return 新创建的 Hatch 对象
     */
    @Operation(summary = "在模型空间中添加一个 Hatch 填充对象", description = "在模型空间中添加一个 Hatch 填充对象（可指定渐变填充）")
    @Parameter(name = "patternType", description = "图案类型：0=预定义，1=用户定义，2=自定义。创建渐变填充（hatchObjectType=1）时必须用 0")
    @Parameter(name = "patternName", description = "图案名称。普通填充用 SOLID、ANSI31 等；渐变填充（hatchObjectType=1）仅限：LINEAR/CYLINDER/INVCYLINDER/SPHERICAL/HEMISPHERICAL/CURVED/INVSPHERICAL/INVHEMISPHERICAL/INVCURVED")
    @Parameter(name = "associativity", description = "是否关联边界")
    @Parameter(name = "hatchObjectType", description = "填充对象类型：0=普通填充，1=渐变填充")
    public Hatch addHatch(int patternType, String patternName, boolean associativity, int hatchObjectType) {
        if (hatchObjectType == 1) {
            validateGradientName(patternName);
        }
        return new Hatch(Disp.call(getClass(), dispatch,"AddHatch", new Variant(patternType), patternName, new Variant(associativity), new Variant(hatchObjectType)).toDispatch());
    }

    /**
     * 在模型空间中添加一条构造线（无限长直线）
     * @param point1 基点坐标
     * @param point2 方向上的另一个点坐标
     * @return 新创建的 XLine 对象
     */
    @Operation(summary = "添加一条构造线", description = "在模型空间中添加一条构造线（无限长直线）")
    @Parameter(name = "point1", description = "基点坐标")
    @Parameter(name = "point2", description = "方向上的另一个点坐标")
    public XLine addXLine(Point point1, Point point2) {
        return new XLine(Disp.call(getClass(), dispatch, "AddXline", point1.toVariant(), point2.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一条二维实心填充四边形（Trace 实线带宽实体）
     * @param points 四个顶点坐标
     * @return 新创建的 Trace 对象
     */
    @Operation(summary = "添加二维填充实体", description = "添加一条二维实心填充四边形（Trace）")
    @Parameter(name = "points", description = "四个顶点坐标")
    public Trace addTrace(Point... points) {
        return new Trace(Disp.call(getClass(), dispatch, "AddTrace", points3DArray(points)).toDispatch());
    }

    /**
     * 在模型空间中添加一个二维实心多边形（Solid 实体）
     * @param point1 第一个顶点
     * @param point2 第二个顶点
     * @param point3 第三个顶点
     * @param point4 第四个顶点
     * @return 新创建的 Solid2D 对象
     */
    @Operation(summary = "添加二维实心多边形", description = "添加一个二维实心多边形（Solid）")
    public Solid2D addSolid2D(Point point1, Point point2, Point point3, Point point4) {
        return new Solid2D(Disp.call(getClass(), dispatch, "AddSolid",
                point1.toVariant(), point2.toVariant(), point3.toVariant(), point4.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加形（Shape，需形名在已加载的 SHX 形文件中存在）
     * @param name 形名称
     * @param insertionPoint 插入点
     * @param scaleFactor 缩放比例
     * @param rotationAngle 旋转角度（弧度）
     * @return 新创建的 Shape 对象
     */
    @Operation(summary = "添加形", description = "添加一个形（Shape）实体")
    public Shape addShape(String name, Point insertionPoint, double scaleFactor, double rotationAngle) {
        return new Shape(Disp.call(getClass(), dispatch, "AddShape",
                name, insertionPoint.toVariant(), new Variant(scaleFactor), new Variant(rotationAngle)).toDispatch());
    }

    /**
     * 在模型空间中添加一条多重平行线（MLine）
     * @param points 顶点坐标数组
     * @return 新创建的 MLine 对象
     */
    @Operation(summary = "添加多重平行线", description = "添加一条多重平行线（MLine）")
    public MLine addMLine(Point... points) {
        return new MLine(Disp.call(getClass(), dispatch, "AddMLine", points3DArray(points)).toDispatch());
    }

    /**
     * 在模型空间中添加一个三维多边形网格（3DMesh）
     * @param m M 方向顶点数
     * @param n N 方向顶点数
     * @param points m*n 个顶点坐标（按行填充）
     * @return 新创建的 PolygonMesh 对象
     */
    @Operation(summary = "添加三维多边形网格", description = "添加一个三维多边形网格（3DMesh）")
    public PolygonMesh add3DMesh(int m, int n, Point... points) {
        return new PolygonMesh(Disp.call(getClass(), dispatch, "Add3DMesh",
                new Variant(m), new Variant(n), points3DArray(points)).toDispatch());
    }

    /**
     * 在模型空间中添加一个多面网格（PolyfaceMesh）
     * @param vertices 顶点坐标数组
     * @param faces 每个面的顶点索引列表（索引从 1 开始，负值表示该边不可见）
     * @return 新创建的 PolyfaceMesh 对象
     */
    @Operation(summary = "添加多面网格", description = "添加一个多面网格（PolyfaceMesh）")
    public PolyfaceMesh addPolyfaceMesh(List<Point> vertices, List<int[]> faces) {
        // VertexList：平铺 double 数组（3D 坐标）；FaceList：VT_I2 平铺 int 数组（VBA Integer），
        // 各面的顶点索引依次排列，不加 0 终止符（2018–2026 五版本实测 VT_I2 无终止为唯一可用编码，
        // VT_I4 / VT_VARIANT 嵌套 / 带 0 终止均报"特性 Faces 的值无效"）
        SafeArray vertexArray = new SafeArray(Variant.VariantDouble, vertices.size() * 3);
        for (int i = 0; i < vertices.size(); i++) {
            vertexArray.setDouble(i * 3, vertices.get(i).getX());
            vertexArray.setDouble(i * 3 + 1, vertices.get(i).getY());
            vertexArray.setDouble(i * 3 + 2, vertices.get(i).getZ());
        }
        int total = faces.stream().mapToInt(f -> f.length).sum();
        SafeArray faceArray = new SafeArray(Variant.VariantShort, total);
        int idx = 0;
        for (int[] face : faces) {
            for (int v : face) {
                faceArray.setShort(idx++, (short) v);
            }
        }
        return new PolyfaceMesh(Disp.call(getClass(), dispatch, "AddPolyfaceMesh",
                new Variant(vertexArray), new Variant(faceArray)).toDispatch());
    }

    /**
     * 在模型空间中添加一个引线标注
     * @param points 引线顶点坐标数组
     * @param annotation 关联的注释实体（可为 null 表示无注释）
     * @param type 引线类型（AcLeaderType：0=直线，1=样条曲线）
     * @return 新创建的 Leader 对象
     */
    @Operation(summary = "添加引线标注", description = "添加一个引线标注（Leader）")
    public Leader addLeader(Point[] points, AcadEntity annotation, int type) {
        // Annotation 为 byref Dispatch：传实体或 VBA 的 Nothing（VT_DISPATCH 空指针），
        // VT_EMPTY 会报"类型不匹配"
        Variant annotationVariant;
        if (annotation == null) {
            annotationVariant = new Variant();
            annotationVariant.putNothing();
        } else {
            annotationVariant = new Variant(annotation.getDispatch());
        }
        return new Leader(Disp.call(getClass(), dispatch, "AddLeader",
                points3DArray(points), annotationVariant, new Variant(type)).toDispatch());
    }

    /**
     * 在模型空间中添加一个多重引线标注（MLeader）
     * @param points 引线顶点坐标数组
     * @return 新创建的 MLeader 对象
     */
    @Operation(summary = "添加多重引线", description = "添加一个多重引线标注（MLeader）")
    public MLeader addMLeader(Point[] points) {
        return new MLeader(Disp.call(getClass(), dispatch, "AddMLeader", points3DArray(points), new Variant(0)).toDispatch());
    }

    /**
     * 在模型空间中添加一个表格
     * @param insertionPoint 插入点
     * @param numRows 行数
     * @param numColumns 列数
     * @param rowHeight 行高
     * @param colWidth 列宽
     * @return 新创建的 Table 对象
     */
    @Operation(summary = "添加表格", description = "添加一个表格（Table）")
    public Table addTable(Point insertionPoint, int numRows, int numColumns, double rowHeight, double colWidth) {
        return new Table(Disp.call(getClass(), dispatch, "AddTable",
                insertionPoint.toVariant(), new Variant(numRows), new Variant(numColumns),
                new Variant(rowHeight), new Variant(colWidth)).toDispatch());
    }

    /**
     * 在模型空间中添加一个角度尺寸标注（三点法，Dim3PointAngular）
     * @param angleVertex 角度顶点
     * @param firstEndPoint 第一条边端点
     * @param secondEndPoint 第二条边端点
     * @param textPoint 文本位置
     * @return 新创建的 Dim3PointAngular 对象
     */
    @Operation(summary = "添加三点角度标注", description = "添加一个三点法角度尺寸标注")
    public Dim3PointAngular addDim3PointAngular(Point angleVertex, Point firstEndPoint, Point secondEndPoint, Point textPoint) {
        return new Dim3PointAngular(Disp.call(getClass(), dispatch, "AddDim3PointAngular",
                angleVertex.toVariant(), firstEndPoint.toVariant(), secondEndPoint.toVariant(), textPoint.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个弧长尺寸标注（DimArcLength）
     * @param arcCenter 弧心
     * @param firstEndPoint 弧起点
     * @param secondEndPoint 弧终点
     * @param arcPoint 弧上参考点
     * @return 新创建的 DimArcLength 对象
     */
    @Operation(summary = "添加弧长标注", description = "添加一个弧长尺寸标注")
    public DimArcLength addDimArcLength(Point arcCenter, Point firstEndPoint, Point secondEndPoint, Point arcPoint) {
        return new DimArcLength(Disp.call(getClass(), dispatch, "AddDimArc",
                arcCenter.toVariant(), firstEndPoint.toVariant(), secondEndPoint.toVariant(), arcPoint.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个折弯半径尺寸标注（DimRadialLarge）
     * @param center 圆心
     * @param chordPoint 引出点
     * @param overrideCenter 折弯中心替代点
     * @param jogPoint 折弯点
     * @param jogAngle 折弯角度（弧度）
     * @return 新创建的 DimRadialLarge 对象
     */
    @Operation(summary = "添加折弯半径标注", description = "添加一个折弯半径尺寸标注")
    public DimRadialLarge addDimRadialLarge(Point center, Point chordPoint, Point overrideCenter, Point jogPoint, double jogAngle) {
        return new DimRadialLarge(Disp.call(getClass(), dispatch, "AddDimRadialLarge",
                center.toVariant(), chordPoint.toVariant(), overrideCenter.toVariant(),
                jogPoint.toVariant(), new Variant(jogAngle)).toDispatch());
    }

    /**
     * 在模型空间中添加一个截面对象（Section）
     * @param fromPoint 起点
     * @param toPoint 终点
     * @param planeVector 截面法向量
     * @return 新创建的 Section 对象
     */
    @Operation(summary = "添加截面对象", description = "添加一个截面对象（Section）")
    public Section addSection(Point fromPoint, Point toPoint, Point planeVector) {
        return new Section(Disp.call(getClass(), dispatch, "AddSection",
                fromPoint.toVariant(), toPoint.toVariant(), planeVector.toVariant()).toDispatch());
    }

    /**
     * 在模型空间中添加一个楔体（Wedge）
     * @param center 底面中心
     * @param length 长度
     * @param width 宽度
     * @param height 高度
     * @return 新创建的 Solid3D 对象
     */
    @Operation(summary = "添加楔体", description = "添加一个楔体（Wedge）")
    public Solid3D addWedge(Point center, double length, double width, double height) {
        return new Solid3D(Disp.call(getClass(), dispatch, "AddWedge",
                center.toVariant(), new Variant(length), new Variant(width), new Variant(height)).toDispatch());
    }

    /**
     * 在模型空间中添加一个椭圆锥（EllipticalCone）
     * @param center 底面中心
     * @param majorRadius 长半轴
     * @param minorRadius 短半轴
     * @param height 高度
     * @return 新创建的 Solid3D 对象
     */
    @Operation(summary = "添加椭圆锥", description = "添加一个椭圆锥（EllipticalCone）")
    public Solid3D addEllipticalCone(Point center, double majorRadius, double minorRadius, double height) {
        return new Solid3D(Disp.call(getClass(), dispatch, "AddEllipticalCone",
                center.toVariant(), new Variant(majorRadius), new Variant(minorRadius), new Variant(height)).toDispatch());
    }

    /**
     * 在模型空间中添加一个椭圆柱（EllipticalCylinder）
     * @param center 底面中心
     * @param majorRadius 长半轴
     * @param minorRadius 短半轴
     * @param height 高度
     * @return 新创建的 Solid3D 对象
     */
    @Operation(summary = "添加椭圆柱", description = "添加一个椭圆柱（EllipticalCylinder）")
    public Solid3D addEllipticalCylinder(Point center, double majorRadius, double minorRadius, double height) {
        return new Solid3D(Disp.call(getClass(), dispatch, "AddEllipticalCylinder",
                center.toVariant(), new Variant(majorRadius), new Variant(minorRadius), new Variant(height)).toDispatch());
    }

    /**
     * 由一组封闭实体生成面域（Region）
     * @param entities 封闭实体（圆、闭合多段线等）
     * @return 生成的 Region 列表（AddRegion 返回数组）
     */
    @Operation(summary = "生成面域", description = "由一组封闭实体生成面域（Region）")
    public List<Region> addRegion(AcadEntity... entities) {
        // ObjectList 须为 VT_DISPATCH 数组（同 AppendOuterLoop 的 toObjectArrayVariant），
        // VT_VARIANT 嵌套数组会让 AutoCAD 崩溃（2024 实测 RPC 服务器不可用）
        SafeArray safeArray = new SafeArray(Variant.VariantDispatch, entities.length);
        for (int i = 0; i < entities.length; i++) {
            safeArray.setVariant(i, new Variant(entities[i].getDispatch()));
        }
        Variant result = Disp.call(getClass(), dispatch, "AddRegion", new Variant(safeArray));
        List<Region> regions = new ArrayList<>();
        for (Variant v : result.toSafeArray().toVariantArray()) {
            if (v.getvt() == Variant.VariantDispatch) {
                regions.add(new Region(v.toDispatch()));
            }
        }
        return regions;
    }

    /**
     * 在模型空间中插入块参照
     * @param insertionPoint 插入点
     * @param name 块名
     * @param xscale X 方向缩放
     * @param yscale Y 方向缩放
     * @param zscale Z 方向缩放
     * @param rotation 旋转角度（弧度）
     * @return 新创建的 BlockReference 对象
     */
    @Operation(summary = "插入块参照", description = "在模型空间中插入块参照（InsertBlock）")
    public BlockReference insertBlock(Point insertionPoint, String name, double xscale, double yscale, double zscale, double rotation) {
        return new BlockReference(Disp.call(getClass(), dispatch, "InsertBlock",
                insertionPoint.toVariant(), name, new Variant(xscale), new Variant(yscale),
                new Variant(zscale), new Variant(rotation), new Variant("")).toDispatch());
    }

    /**
     * 在模型空间中插入块阵列（MInsertBlock）
     * @param insertionPoint 插入点
     * @param name 块名
     * @param numRows 行数
     * @param numColumns 列数
     * @param rowSpacing 行间距
     * @param columnSpacing 列间距
     * @return 新创建的 MInsertBlock 对象
     */
    @Operation(summary = "插入块阵列", description = "在模型空间中插入块阵列（MInsertBlock）")
    public MInsertBlock addMInsertBlock(Point insertionPoint, String name, int numRows, int numColumns, double rowSpacing, double columnSpacing) {
        return new MInsertBlock(Disp.call(getClass(), dispatch, "AddMInsertBlock",
                insertionPoint.toVariant(), name, new Variant(1.0), new Variant(1.0), new Variant(1.0),
                new Variant(0.0), new Variant(numRows), new Variant(numColumns),
                new Variant(rowSpacing), new Variant(columnSpacing), new Variant("")).toDispatch());
    }

    /**
     * 把多个三维点打包成 double 数组 Variant（Add3DPoly / AddTrace / AddMLine / Add3DMesh 等使用）。
     */
    private static Variant points3DArray(Point... points) {
        SafeArray safeArray = new SafeArray(Variant.VariantDouble, points.length * 3);
        for (int i = 0; i < points.length; i++) {
            safeArray.setDouble(i * 3, points[i].getX());
            safeArray.setDouble(i * 3 + 1, points[i].getY());
            safeArray.setDouble(i * 3 + 2, points[i].getZ());
        }
        return new Variant(safeArray);
    }

}

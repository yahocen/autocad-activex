package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.document.manager.AcCmColor;
import cn.hutool.core.util.ArrayUtil;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.SafeArray;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.util.EntityUtil;

import java.util.List;

/**
 * Hatch - AutoCAD Hatch 实体
 */
@AcadEntityName("AcDbHatch")
public class Hatch extends AcadEntity {

    public Hatch(Dispatch dispatch) {
        super(dispatch);
    }

    public double getArea() {
        return getProperties("Area").getDouble();
    }

    public Variant getAssociativeHatch() {
        return getProperties("AssociativeHatch");
    }

    public Hatch setAssociativeHatch(Variant associativeHatch) {
        setProperties("AssociativeHatch", associativeHatch);
        return this;
    }

    public Dispatch getBackgroundColor() {
        return getProperties("BackgroundColor").getDispatch();
    }

    public Hatch setBackgroundColor(Dispatch backgroundColor) {
        setProperties("BackgroundColor", backgroundColor);
        return this;
    }

    public Variant getElevation() {
        return getProperties("Elevation");
    }

    public Hatch setElevation(Variant elevation) {
        setProperties("Elevation", elevation);
        return this;
    }

    public double getGradientAngle() {
        return getProperties("GradientAngle").getDouble();
    }

    public Hatch setGradientAngle(double gradientAngle) {
        setProperties("GradientAngle", gradientAngle);
        return this;
    }

    public Variant getGradientCentered() {
        return getProperties("GradientCentered");
    }

    public Hatch setGradientCentered(Variant gradientCentered) {
        setProperties("GradientCentered", gradientCentered);
        return this;
    }

    /**
     * 设置渐变是否居中。
     *
     * @param gradientCentered 是否居中
     * @return 当前 Hatch 对象
     */
    @Operation(summary = "设置渐变是否居中", description = "设置渐变填充是否居中显示")
    @Parameter(name = "gradientCentered", description = "是否居中")
    public Hatch setGradientCentered(boolean gradientCentered) {
        setProperties("GradientCentered", gradientCentered);
        return this;
    }

    public AcCmColor getGradientColor1() {
        return new AcCmColor(getProperties("GradientColor1").getDispatch());
    }

    public Hatch setGradientColor1(AcCmColor gradientColor1) {
        setProperties("GradientColor1", gradientColor1.getDispatch());
        return this;
    }

    public AcCmColor getGradientColor2() {
        return new AcCmColor(getProperties("GradientColor2").getDispatch());
    }

    public Hatch setGradientColor2(AcCmColor gradientColor2) {
        setProperties("GradientColor2", gradientColor2.getDispatch());
        return this;
    }

    public String getGradientName() {
        return getProperties("GradientName").getString();
    }

    public Hatch setGradientName(String gradientName) {
        setProperties("GradientName", gradientName);
        return this;
    }

    public int getHatchObjectType() {
        return getProperties("HatchObjectType").getInt();
    }

    public Hatch setHatchObjectType(int hatchObjectType) {
        setProperties("HatchObjectType", hatchObjectType);
        return this;
    }

    public int getHatchStyle() {
        return getProperties("HatchStyle").getInt();
    }

    public Hatch setHatchStyle(int hatchStyle) {
        setProperties("HatchStyle", hatchStyle);
        return this;
    }

    public int getISOPenWidth() {
        return getProperties("ISOPenWidth").getInt();
    }

    public Hatch setISOPenWidth(int iSOPenWidth) {
        setProperties("ISOPenWidth", iSOPenWidth);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Hatch setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public Variant getNumberOfLoops() {
        return getProperties("NumberOfLoops");
    }

    public Point getOrigin() {
        return Point.of(getProperties("Origin").toSafeArray());
    }

    public Hatch setOrigin(Point origin) {
        setProperties("Origin", origin.toVariant());
        return this;
    }

    public double getPatternAngle() {
        return getProperties("PatternAngle").getDouble();
    }

    public Hatch setPatternAngle(double patternAngle) {
        setProperties("PatternAngle", patternAngle);
        return this;
    }

    public Variant getPatternDouble() {
        return getProperties("PatternDouble");
    }

    public Hatch setPatternDouble(Variant patternDouble) {
        setProperties("PatternDouble", patternDouble);
        return this;
    }

    public String getPatternName() {
        return getProperties("PatternName").getString();
    }

    public double getPatternScale() {
        return getProperties("PatternScale").getDouble();
    }

    public Hatch setPatternScale(double patternScale) {
        setProperties("PatternScale", patternScale);
        return this;
    }

    public Variant getPatternSpace() {
        return getProperties("PatternSpace");
    }

    public Hatch setPatternSpace(Variant patternSpace) {
        setProperties("PatternSpace", patternSpace);
        return this;
    }

    public int getPatternType() {
        return getProperties("PatternType").getInt();
    }

    public Variant setPattern(Variant patternType, Variant patternName) {
        return call("SetPattern", patternType, patternName);
    }

    /**
     * 追加外边界环。
     * <p>
     * 推荐使用 {@link #appendOuterLoop(AcadEntity...)} 重载，无需自行构造 Variant 数组。
     * </p>
     *
     * @param objectArray 包含边界实体对象的 Variant 数组（元素为实体 Dispatch）
     * @return COM 调用结果
     */
    public Variant appendOuterLoop(Variant objectArray) {
        return Disp.call(getClass(), dispatch, "AppendOuterLoop", objectArray);
    }

    /**
     * 追加外边界环（友好重载）。
     * <p>
     * 直接传入构成边界的实体（如闭合多段线），内部自动构造 COM 所需的对象数组。
     * 这是创建 Hatch 后必须调用的方法，用于指定填充的闭合边界。
     * </p>
     *
     * @param entities 构成外边界的实体（通常为一个闭合的多段线）
     * @return COM 调用结果
     */
    @Operation(summary = "追加外边界环", description = "为填充指定闭合外边界实体（如闭合多段线）。创建 Hatch 后必须调用以指定边界")
    @Parameter(name = "entities", description = "构成外边界的实体，通常为一个闭合的多段线对象")
    public Variant appendOuterLoop(AcadEntity... entities) {
        return Disp.call(getClass(), dispatch, "AppendOuterLoop", toObjectArrayVariant(entities));
    }

    /**
     * 追加外边界环（列表重载）。
     *
     * @param entities 构成外边界的实体列表
     * @return COM 调用结果
     */
    @Operation(summary = "追加外边界环", description = "为填充指定闭合外边界实体列表")
    @Parameter(name = "entities", description = "构成外边界的实体列表")
    public Variant appendOuterLoop(List<AcadEntity> entities) {
        return appendOuterLoop(entities.toArray(new AcadEntity[]{}));
    }

    public Variant appendInnerLoop(Variant objectArray) {
        return Disp.call(getClass(), dispatch, "AppendInnerLoop", objectArray);
    }

    /**
     * 追加内边界环（友好重载）。
     * <p>
     * 内边界即填充中的"岛"（洞），直接传入构成内边界的实体即可。
     * </p>
     *
     * @param entities 构成内边界的实体（通常为一个闭合的多段线）
     * @return COM 调用结果
     */
    @Operation(summary = "追加内边界环", description = "为填充指定内边界（岛/洞）实体")
    @Parameter(name = "entities", description = "构成内边界的实体，通常为一个闭合的多段线对象")
    public Variant appendInnerLoop(AcadEntity... entities) {
        return Disp.call(getClass(), dispatch, "AppendInnerLoop", toObjectArrayVariant(entities));
    }

    /**
     * 追加内边界环（列表重载）。
     *
     * @param entities 构成内边界的实体列表
     * @return COM 调用结果
     */
    @Operation(summary = "追加内边界环", description = "为填充指定内边界（岛/洞）实体列表")
    @Parameter(name = "entities", description = "构成内边界的实体列表")
    public Variant appendInnerLoop(List<AcadEntity> entities) {
        return appendInnerLoop(entities.toArray(new AcadEntity[]{}));
    }

    /**
     * 将实体数组转换为包含实体 Dispatch 对象的 Variant 数组（COM SafeArray）。
     * <p>
     * AutoCAD ActiveX 的 AppendOuterLoop/AppendInnerLoop 需要这种形式。
     * </p>
     *
     * @param entities 实体数组
     * @return 包含实体 Dispatch 的 Variant
     */
    private Variant toObjectArrayVariant(AcadEntity... entities) {
        if (ArrayUtil.isEmpty(entities)) {
            throw new IllegalArgumentException("边界实体数组不能为空");
        }
        // AutoCAD AppendOuterLoop/AppendInnerLoop 要求 VT_DISPATCH 数组（vlax-vbObject），
        // 不能用 VT_VARIANT，否则 COM 层报"对象数组无效"。
        // setVariant 在 SafeArray 元素类型为 VT_DISPATCH 时会自动提取 IDispatch 指针并 AddRef。
        SafeArray safeArray = new SafeArray(Variant.VariantDispatch, entities.length);
        for (int i = 0; i < entities.length; i++) {
            safeArray.setVariant(i, new Variant(entities[i].getDispatch()));
        }
        return new Variant(safeArray);
    }

    public Variant insertLoopAt(Variant index, int loopType, Variant objectArray) {
        return Disp.call(getClass(), dispatch, "InsertLoopAt", index, new Variant(loopType), objectArray);
    }

    /**
     * 获取第 index 个边界环的边界对象。
     * <p>
     * COM 签名为 {@code GetLoopAt(Index, [out] ObjectArray)}，结果通过 byref 输出参数返回，
     * 原实现把输出参数按值传入，结果始终丢失。
     * 注意：只有关联填充（AssociativeHatch=true）才有边界对象；非关联填充 AutoCAD 返回空，
     * 此时边界几何只能从 DXF 组码（entget）读取。
     * </p>
     *
     * @param index 环索引，从 0 开始，小于 {@link #getNumberOfLoops()}
     * @return 构成该环的边界实体；非关联填充返回空列表
     */
    @Operation(summary = "获取边界环的边界对象", description = "返回第 index 个环（从 0 开始）的边界实体列表；非关联填充没有边界对象，返回空列表")
    public List<AcadEntity> getLoopAt(@Parameter(description = "环索引，从 0 开始") int index) {
        Variant holder = new Variant();
        Variant outArg = new Variant();
        outArg.putVariantRef(holder);
        Disp.invokev(getClass(), dispatch, "GetLoopAt", Dispatch.Method,
                new Variant[]{new Variant(index), outArg}, new int[2]);
        return EntityUtil.parseArray(holder);
    }

    @Operation(summary = "计算并更新填充显示", description = "计算 Hatch 填充图案并更新显示。设置边界和图案后必须调用")
    public Variant evaluate() {
        return call("Evaluate");
    }

}
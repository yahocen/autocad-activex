package io.github.yahocen.acad.document;

import io.github.yahocen.acad.common.Disp;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ReflectUtil;
import io.github.yahocen.jacob.com.ComFailException;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.common.ComObject;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.base.AcadObject;
import io.github.yahocen.acad.document.base.Point;

/**
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-DE118C17-4567-48CF-8EFA-CABB52A27275">Utility Object (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Utility AutoCAD 工具类",
    description = "AutoCAD Utility 对象，提供各种辅助功能"
)
public class Utility extends ComObject {

    private final Document document;

    protected Utility(Dispatch util, Document document) {
        super(util);
        this.document = document;
    }

    /**
     * 获取用户选择的点
     * @param basePoint 基点
     * @param prompt 提示信息
     * @return Point 用户选择的点
     */
    @Operation(summary = "获取用户选择的点", description = "获取用户选择的点")
    @Parameter(name = "basePoint", description = "基点")
    @Parameter(name = "prompt", description = "提示信息")
    public Point getPoint(Point basePoint, String prompt) {
        Variant pointVariant = basePoint != null ? basePoint.toVariant() : Variant.DEFAULT;
        Variant promptVariant = prompt != null ? new Variant(prompt) : Variant.DEFAULT;
        // 调用 AutoCAD 的 GetPoint 方法获取用户选择的点
        Variant result = null;
        int retry = 3;
        while(retry-- > 0){
            try {
                result = Disp.call(getClass(), dispatch, "GetPoint", pointVariant, promptVariant);
                break;
            } catch (ComFailException e) {
                if(retry == 0) {
                    throw e;
                }
                ThreadUtil.sleep(1000);
            }
        }
        // 将返回的 Variant 类型转换为 Point 对象
        if (result == null) {
            return null;
        }
        var point = Point.of(result.toSafeArray());
        result.safeRelease();
        return point;
    }

    /**
     * 获取用户交互选择的对象
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-69164FAA-F3C7-47A4-962C-5F4B2D5BC583">GetEntity Method (ActiveX)</a>
     * @param prompt 提示信息
     * @return 选中的对象和选择点
     */
    @Operation(summary = "获取用户交互选择的对象", description = "获取用户交互选择的对象")
    @Parameter(name = "prompt", description = "提示信息")
    public EntityPickResult getEntity(String prompt) {
        // 方案F：混合 byRef 类型 — 匹配 AutoCAD GetEntity IDL 签名
        // entity:      out IDispatch**  → VT_DISPATCH|VT_BYREF
        // PickedPoint: out VARIANT*      → VT_VARIANT|VT_BYREF
        // Prompt:      in  BSTR          → VT_BSTR
        var entityVar = new Variant();
        entityVar.putDispatchRef(null);

        var pointHolder = new Variant();
        var pointVar = new Variant();
        pointVar.putVariantRef(pointHolder);

        var promptVar = new Variant(prompt);

        Disp.invokev(getClass(), dispatch, "GetEntity", Dispatch.Method,
                new Variant[]{entityVar, pointVar, promptVar}, new int[3]);

        // 读取实体（从 byRef Dispatch）
        var entityDispatch = entityVar.getDispatchRef();
        if (entityDispatch == null || entityDispatch.m_pDispatch == 0) {
            return null;
        }
        var handle = Dispatch.call(entityDispatch, "Handle").toString();

        // 读取坐标（pointHolder 被 AutoCAD 回写为 VT_R8|VT_ARRAY）
        var point = Point.of(pointHolder.toSafeArray());

        entityVar.safeRelease();
        pointVar.safeRelease();
        promptVar.safeRelease();

        return new EntityPickResult(handle, point, document);
    }

    @Operation(summary = "在命令行显示提示信息", description = "在命令行显示提示信息")
    @Parameter(name = "prompt", description = "提示信息")
    public void prompt(String prompt) {
        Disp.call(getClass(), dispatch, "Prompt", prompt);
    }

    /**
     * 选中对象的结果类
     * @param handle 选中的对象
     * @param pickedPoint 选择点
     */
    public record EntityPickResult(String handle, Point pickedPoint, Document document) {

        /**
         *  将选中的对象转换为指定类型的对象
         * @param clazz  指定类型的类
         * @return 指定类型的对象
         * @param <T> 指定类型的类
         */
        @Operation(summary = "将选中的对象转换为指定类型的对象", description = "将选中的对象转换为指定类型的对象")
        @Parameter(name = "clazz", description = "指定类型的类")
        public <T extends AcadObject> T toEntityObject (Class<T> clazz) {
            return ReflectUtil.newInstance(clazz, document.handleToObject(handle));
        }

        @Operation(summary = "将选中的对象转换为 AcadEntity", description = "将选中的对象转换为 AcadEntity")
        public AcadEntity toEntity () {
            return new AcadEntity(document.handleToObject(handle));
        }

    }

}

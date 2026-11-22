package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.SafeArray;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.Application;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AutoCAD选择集对象
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-BF264239-CF7D-4BC8-8D37-69477862DB5A">SelectionSet Object (ActiveX)</a>
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "SelectionSet AutoCAD选择集",
    description = "AutoCAD中用户选择的对象集合"
)
public class SelectionSet extends AcadObject {

    public SelectionSet(Dispatch dispatch) {
        super(dispatch);
    }

    /**
     * 获取选择集的名称
     * 
     * @return 选择集的名称
     */
    @Operation(summary = "获取选择集的名称", description = "获取选择集的名称")
    public String getName() {
        return Disp.get(getClass(), dispatch, "Name").getString();
    }

    /**
     * 设置选择集的名称
     * 
     * @param name 选择集的名称
     */
    @Operation(summary = "设置选择集的名称", description = "设置选择集的名称")
    public void setName(@Parameter(description = "选择集的名称") String name) {
        Disp.put(getClass(), dispatch, "Name", new Variant(name));
    }

    /**
     * 清空选择集中的所有项目
     */
    @Operation(summary = "清空选择集中的所有项目", description = "清空选择集中的所有项目")
    public void clear() {
        Disp.call(getClass(), dispatch, "Clear");
    }

    /**
     * 删除选择集对象本身
     */
    @Operation(summary = "删除选择集对象本身", description = "删除选择集对象本身")
    public void delete() {
        Disp.call(getClass(), dispatch, "Delete");
    }

    /**
     * 通过索引获取集合元素
     * 
     * @param index 从0开始的索引
     * @return 集合元素对象
     */
    @Operation(summary = "通过索引获取集合元素", description = "通过索引获取集合元素")
    public Dispatch item(@Parameter(description = "从 0 开始的索引") int index) {
        return Disp.call(getClass(), dispatch, "Item", new Variant(index)).getDispatch();
    }

    /**
     * 从选择集中移除指定的项目
     * 
     * @param items 要移除的项目数组
     */
    @Operation(summary = "从选择集中移除指定的项目", description = "从选择集中移除指定的项目")
    public void removeItems(@Parameter(description = "要移除的项目数组") Object[] items) {
        Disp.call(getClass(), dispatch, "RemoveItems", new Variant(items));
    }

    /**
     * 将项目添加到选择集中
     * 
     * @param items 要添加的项目数组
     */
    @Operation(summary = "将项目添加到选择集中", description = "将项目添加到选择集中")
    public void addItems(@Parameter(description = "要添加的项目数组") Object[] items) {
        Disp.call(getClass(), dispatch, "AddItems", new Variant(items));
    }

    /**
     * 高亮显示选择集中的项目
     * 
     * @param highlight 是否高亮显示
     */
    @Operation(summary = "高亮显示选择集中的项目", description = "高亮显示选择集中的项目")
    public void highlight(@Parameter(description = "是否高亮显示") boolean highlight) {
        Disp.call(getClass(), dispatch, "Highlight", new Variant(highlight));
    }

    /**
     * 更新选择集
     */
    @Operation(summary = "更新选择集", description = "更新选择集")
    public void update() {
        Disp.call(getClass(), dispatch, "Update");
    }

    /**
     * 获取选择集中的项目数量
     * 
     * @return 项目数量
     */
    @Operation(summary = "获取选择集中的项目数量", description = "获取选择集中的项目数量")
    public int getCount() {
        return Disp.get(getClass(), dispatch, "Count").getInt();
    }

    /**
     * 获取选择集所属的应用程序
     * 
     * @return 应用程序对象
     */
    @Operation(summary = "获取选择集所属的应用程序", description = "获取选择集所属的应用程序")
    public Application getApplication() {
        return new Application(Disp.get(getClass(), dispatch, "Application").getDispatch());
    }

    /**
     * 提示用户从屏幕上选择对象，并将其添加到选择集中。
     * @param filterTypes 可选参数，DXF组码，指定过滤器类型。
     * @param filterData 可选参数，过滤器数据。
     */
    @Operation(summary = "提示用户从屏幕上选择对象", description = "提示用户从屏幕上选择对象，并将其添加到选择集中")
    public void selectOnScreen(@Parameter(description = "DXF 组码，指定过滤器类型") int[] filterTypes, @Parameter(description = "过滤器数据") Object[] filterData) {
        try {
            // 创建Variant数组
            Variant[] vFilterTypes = new Variant[filterTypes.length];
            Variant[] vFilterData = new Variant[filterData.length];

            for (int i = 0; i < filterTypes.length; i++) {
                vFilterTypes[i] = new Variant(filterTypes[i]);
                vFilterData[i] = new Variant(filterData[i]);
            }

            // 创建SafeArray
            SafeArray saFilterTypes = new SafeArray(Variant.VariantVariant, vFilterTypes.length);
            saFilterTypes.fromVariantArray(vFilterTypes);

            SafeArray saFilterData = new SafeArray(Variant.VariantVariant, vFilterData.length);
            saFilterData.fromVariantArray(vFilterData);

            // 调用方法 - 注意这里使用Variant包装SafeArray
            Disp.call(getClass(), this.dispatch,"SelectOnScreen",
                    new Variant(saFilterTypes),
                    new Variant(saFilterData));
        } catch (Exception e) {
            throw new RuntimeException("调用SelectOnScreen失败", e);
        }
    }

    @Operation(summary = "提示用户从屏幕上选择对象", description = "提示用户从屏幕上选择对象，并将其添加到选择集中（无参数版本）")
    public void selectOnScreen() {
        Disp.call(getClass(), this.dispatch,"SelectOnScreen");
    }

}
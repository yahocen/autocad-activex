package io.github.yahocen.acad.document;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import io.github.yahocen.jacob.com.ComFailException;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.common.ComObject;
import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.constant.AcRegenType;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.manager.*;
import io.github.yahocen.acad.util.EntityUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文档对象
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-9216BFCD-D358-4FC6-B631-B52E6693D242">Document Object (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Document AutoCAD 文档",
    description = "AutoCAD 文档对象，用于操作图纸文档"
)
public class Document extends ComObject {

    public Document(Dispatch document) {
        super(document);
    }

    /**
     * 另存为当前文档
     * @param filePath 保存路径
     */
    @Operation(summary = "另存为当前文档", description = "另存为当前文档")
    @Parameter(name = "filePath", description = "保存路径")
    public void saveAs(String filePath) {
        // 不传 FileType：按当前版本的 DWG 格式保存。AcSaveAsType 里没有 0，传 0 会报"参数无效"
        Disp.call(getClass(), dispatch,"SaveAs", filePath);
    }

    /**
     * 关闭当前文档
     * @param saveChanges 是否保存
     * @param fileName 保存的文件名（可选）
     */
    @Operation(summary = "关闭当前文档", description = "关闭当前文档")
    @Parameter(name = "saveChanges", description = "是否保存")
    @Parameter(name = "fileName", description = "保存的文件名（可选）")
    public void close(boolean saveChanges, String fileName) {
        if (saveChanges && StrUtil.isNotBlank(fileName)) {
            Disp.call(getClass(), dispatch,"Close", new Variant(true), new Variant(fileName));
        } else {
            Disp.call(getClass(), dispatch,"Close", new Variant(saveChanges));
        }
    }

    /**
     * 获取文档名称
     * 该方法用于获取当前文档的名称
     * @return 返回文档的名称，类型为 String
     */
    @Operation(summary = "获取文档名称", description = "获取当前文档的名称")
    public String getName() {
        // 使用 Dispatch 类的 get 方法获取 document 对象的"Name"属性值
        // 然后将结果转换为 String 类型并返回
        return Disp.get(getClass(), dispatch,"Name").toString();
    }

    /**
     * 获取文档全路径
     * 该方法用于获取当前文档的完整路径，包括文件名和扩展名
     * @return 返回文档的完整路径字符串
     */
    @Operation(summary = "获取文档全路径", description = "获取当前文档的完整路径，包括文件名和扩展名")
    public String getFullName() {
        // 通过 Dispatch.get 方法获取 document 对象的 FullName 属性值
        // 并将其转换为字符串返回
        return Disp.get(getClass(), dispatch,"FullName").toString();
    }

    /**
     * 获取模型空间
     * 该方法用于获取 AutoCAD 文档中的模型空间对象
     * @return 返回一个 ModelSpace 对象，代表 AutoCAD 中的模型空间
     *         ModelSpace 是 AutoCAD 中用于存储所有模型空间实体的集合
     */
    @Operation(summary = "获取模型空间", description = "获取 AutoCAD 文档中的模型空间对象")
    public ModelSpace getModelSpace() {
        // 通过 Dispatch.get 获取 document 对象的"ModelSpace"属性
        // 将获取到的 Dispatch 对象封装成 ModelSpace 对象并返回
        return new ModelSpace(Disp.get(getClass(), dispatch,"ModelSpace").toDispatch());
    }

    /**
     * 设置系统变量
     * @param name 变量名
     * @param value 变量值
     */
    @Operation(summary = "设置系统变量", description = "设置系统变量")
    @Parameter(name = "name", description = "变量名")
    @Parameter(name = "value", description = "变量值")
    public void setVariable(String name, Object value) {
        int retry = 3;
        while(retry-- > 0){
            try {
                Disp.call(getClass(), dispatch,"SetVariable", new Variant(name), new Variant(value));
                break;
            } catch (ComFailException e) {
                if(retry == 0) {
                    throw e;
                }
                ThreadUtil.sleep(1000);
            }
        }
    }

    /**
     * 获取系统变量
     * @param name 变量名
     * @return Variant
     */
    @Operation(summary = "获取系统变量", description = "获取系统变量")
    @Parameter(name = "name", description = "变量名")
    public Variant getVariable(String name) {
        return Disp.call(getClass(), dispatch,"GetVariable", new Variant(name));
    }

    /**
     * 设置线型缩放
     * @param scale LTSCALE 值
     */
    @Operation(summary = "设置线型缩放", description = "设置线型缩放")
    @Parameter(name = "scale", description = "LTSCALE 值")
    public void setLineTypeScale(double scale) {
        setVariable("LTSCALE", scale);
    }

    /**
     * 获取线型缩放
     * @return double
     */
    @Operation(summary = "获取线型缩放", description = "获取线型缩放")
    public double getLineTypeScale() {
        return getVariable("LTSCALE").getDouble();
    }

    /**
     * 控制标注元素（如文字、箭头、尺寸界线）的显示大小
     * @param scale DIMSCALE 值
     */
    @Operation(summary = "控制标注元素的显示大小", description = "控制标注元素（如文字、箭头、尺寸界线）的显示大小")
    @Parameter(name = "scale", description = "DIMSCALE 值")
    public void setDimScale(double scale) {
        setVariable("DIMSCALE", scale);
    }

    /**
     * 控制标注元素（如文字、箭头、尺寸界线）的显示大小
     * @return double
     */
    @Operation(summary = "获取标注元素的显示大小", description = "获取标注元素（如文字、箭头、尺寸界线）的显示大小")
    public double getDimScale() {
        return getVariable("DIMSCALE").getDouble();
    }

    /**
     * 测量单位比例因子
     * @param scale DIMLFAC 值
     */
    @Operation(summary = "设置测量单位比例因子", description = "设置测量单位比例因子")
    @Parameter(name = "scale", description = "DIMLFAC 值")
    public void setDimLfac(double scale) {
        setVariable("DIMLFAC", scale);
    }

    /**
     * 测量单位比例因子
     * @return double
     */
    @Operation(summary = "获取测量单位比例因子", description = "获取测量单位比例因子")
    public double getDimLfac() {
        return getVariable("DIMLFAC").getDouble();
    }

    /**
     * 重新生成整个绘图并重新计算所有对象的屏幕坐标和视图分辨率
     * @param whichViewports AcRegenType 枚举值，指定要重新生成的视口
     */
    @Operation(summary = "重新生成整个绘图", description = "重新生成整个绘图并重新计算所有对象的屏幕坐标和视图分辨率")
    @Parameter(name = "whichViewports", description = "AcRegenType 枚举值，指定要重新生成的视口")
    public void regen(AcRegenType whichViewports) {
        Disp.call(getClass(), dispatch,"Regen", new Variant(whichViewports.getValue()));
    }

    /**
     * 获取文档的选择集集合
     * @return SelectionSets 对象
     */
    @Operation(summary = "获取文档的选择集集合", description = "获取文档的选择集集合")
    public SelectionSets getSelectionSets() {
        return new SelectionSets(Disp.get(getClass(), dispatch,"SelectionSets").toDispatch());
    }

    @Operation(summary = "获取实体列表", description = "提示用户手动在工作空间中框选一组实体并返回框选的实体列表")
    public List<AcadEntity> getEntityList() {
        SelectionSet tempSelectionSet = getSelectionSets().getOrAddItem("AcadActivexTempSelectionSet");
        tempSelectionSet.selectOnScreen();
        //组装成集合返回
        var list = new ArrayList<AcadEntity>();
        for (int i = 0; i < tempSelectionSet.getCount(); i++) {
            list.add(EntityUtil.parse(tempSelectionSet.item(i)));
        }
        tempSelectionSet.delete();
        return list;
    }

    /**
     * 获取用户事先已选中的实体列表（PickfirstSelectionSet）。
     * <p>
     * 与 {@link #getEntityList()} 不同，此方法不会弹出屏幕选择提示，
     * 而是直接返回用户在调用前已经通过夹点/框选选中的实体。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-ABF87227-2C4B-417B-9D22-4C288BC11222">PickfirstSelectionSet Property (ActiveX)</a>
     *
     * @return 已选中的实体列表，无选中时返回空列表
     */
    @Operation(summary = "获取事先已选中的实体列表", description = "获取用户在调用前已经选中的实体（PickfirstSelectionSet），不弹出屏幕选择提示")
    public List<AcadEntity> getPickfirstEntityList() {
        var pickfirst = Disp.get(getClass(), dispatch,"PickfirstSelectionSet").toDispatch();
        var list = new ArrayList<AcadEntity>();
        // pickfirst 是 IAcadSelectionSet 对象：Count/Item 的 DISPID 须按 SelectionSet 桶缓存，
        // 不能沿用 Document 桶（DISPID 按接口编号，跨接口会串号）
        int count = Disp.get(SelectionSet.class, pickfirst, "Count").getInt();
        for (int i = 0; i < count; i++) {
            var item = Disp.call(SelectionSet.class, pickfirst, "Item", new Variant(i)).toDispatch();
            list.add(EntityUtil.parse(item));
        }
        return list;
    }

    @Operation(summary = "获取 Utility 对象", description = "获取文档的 Utility 对象")
    public Utility getUtility() {
        return new Utility(Disp.get(getClass(), dispatch,"Utility").getDispatch(), this);
    }

    @Operation(summary = "获取文本样式集合", description = "获取文档的文本样式集合")
    public TextStyles getTextStyles() {
        return new TextStyles(Disp.get(getClass(), dispatch,"TextStyles").getDispatch());
    }

    /**
     * 发送命令到 AutoCAD。
     * SendCommand 不会自动回车，此处确保命令以换行结尾才会被执行。
     * @param command 命令
     */
    @Operation(summary = "发送命令", description = "发送命令到 AutoCAD")
    @Parameter(name = "command", description = "命令")
    public void sendCommand(String command) {
        if (!command.endsWith("\n")) {
            command = command + "\n";
        }
        Disp.call(getClass(), dispatch,"SendCommand", new Variant(command));
    }

    /**
     * 加载 lisp 文件
     * @param lispFile lisp 文件
     */
    @Operation(summary = "加载 lisp 文件", description = "加载 lisp 文件")
    @Parameter(name = "lispFile", description = "lisp 文件")
    public void loadLispFile(File lispFile) {
        this.sendCommand(StrUtil.format("(load \"{}\") ",  FileUtil.normalize(lispFile.getAbsolutePath())));
    }

    /**
     * 获取与给定句柄对应的对象
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-E03AFFD7-B473-45F9-9FF1-CE4D09C94CF3">HandleToObject Method (ActiveX)</a>
     * @param handle 对象句柄
     * @return 对象
     */
    @Operation(summary = "根据句柄获取对象", description = "获取与给定句柄对应的对象")
    @Parameter(name = "handle", description = "对象句柄")
    public Dispatch handleToObject(String handle) {
        return Disp.call(getClass(), dispatch,"HandleToObject", new Variant(handle)).toDispatch();
    }

    /**
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-D01C8A57-12BE-4EE3-9952-EEBD99818585">Layers Property (ActiveX)</a>
     */
    @Operation(summary = "获取图层集合", description = "获取文档的图层集合")
    public Layers getLayers() {
        return new Layers(Disp.get(getClass(), dispatch,"Layers").getDispatch());
    }

    @Operation(summary = "获取线型集合", description = "获取文档的线型集合。用 load(线型名, 线型文件) 静默加载线型（如 load(\"CENTER\",\"acad.lin\")），不弹对话框；脚本设图层线型前先加载，避免 LINETYPE 命令弹模态框阻塞自动化")
    public LineTypes getLinetypes() {
        return new LineTypes(Disp.get(getClass(), dispatch,"Linetypes").getDispatch());
    }

    @Operation(summary = "获取或添加图层", description = "获取指定名称的图层，如果不存在则创建")
    @Parameter(name = "layerName", description = "图层名称")
    public Layer getOrAddLayer(String layerName) {
        return getLayers().getOrAdd(layerName);
    }

    /**
     * 获取文档的块定义集合
     * @return Blocks 对象
     */
    @Operation(summary = "获取块定义集合", description = "获取文档的块定义集合")
    public Blocks getBlocks() {
        return new Blocks(Disp.get(getClass(), dispatch,"Blocks").getDispatch());
    }

}

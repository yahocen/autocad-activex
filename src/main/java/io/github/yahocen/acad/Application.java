// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad;

import io.github.yahocen.jacob.activeX.ActiveXComponent;
import io.github.yahocen.jacob.com.ComFailException;
import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import io.github.yahocen.acad.common.AcadException;
import io.github.yahocen.acad.common.ComObject;
import io.github.yahocen.acad.document.Document;
import io.github.yahocen.acad.document.Documents;
import io.github.yahocen.acad.util.CadWindowHelper;

import static io.github.yahocen.acad.constant.AutoCadVersion.AUTOCAD_2024;

/**
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-A809CD71-4655-44E2-B674-1FE200B9FE30">封装AutoCAD的ActiveX组件</a>
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-0225808C-8C91-407B-990C-15AB966FFFA8">Application Object (ActiveX)</a>
 *
 * @author Yahocen
 * @since 2.0
 */
@Slf4j
@Getter
@Tag(name = "脚本基类", description = "所有脚本默认可用的全局方法")
@Schema(
    title = "Application AutoCAD应用程序",
    description = "AutoCAD应用程序对象，提供对AutoCAD应用程序的访问"
)
public class Application extends ComObject {

    /**
     * AutoCAD的ActiveX组件
     */
    private final ActiveXComponent acadApp;

    /**
     * 文档集合
     */
    private final Documents documents;

    /**
     * AutoCAD应用程序类的构造函数，用于初始化 AutoCAD 应用程序实例
     */
    public Application(ActiveXComponent acadApp) {
        super(acadApp);
        this.acadApp = acadApp;
        this.dispatch = acadApp.getObject();
        this.documents = new Documents(this);
    }

    /**
     * 基于已有的 Dispatch 包装 AutoCAD 应用程序对象。
     * <p>
     * 用于从实体等对象反查其所属的应用程序时，用底层 Dispatch 重新包装。
     * </p>
     *
     * @param dispatch AutoCAD 应用程序的底层 Dispatch
     */
    public Application(Dispatch dispatch) {
        this(new ActiveXComponent(dispatch));
    }

    /**
     * 用于连接到已有的 AutoCAD 应用程序实例
     * @return Application
     */
    @Operation(summary = "连接到已有的 AutoCAD 应用程序实例", description = "连接到已有的 AutoCAD 应用程序实例")
    public static Application connectToActiveAcad() {
        // 新连接可能是重启/换版本后的实例：DISPID 编号只对同一次连接的类型库稳定，清空缓存防串号
        io.github.yahocen.acad.common.Disp.clear();
        var activeXComponent = ActiveXComponent.connectToActiveInstance(AUTOCAD_2024.getProgId());
        if(null == activeXComponent) {
            throw new AcadException("无法连接到已有 AutoCAD 实例");
        }
        return new Application(activeXComponent);
    }

    /**
     * 用于新建 AutoCAD 应用程序实例
     * @return Application
     */
    @Operation(summary = "新建 AutoCAD 应用程序实例", description = "新建 AutoCAD 应用程序实例")
    public static Application createNewAcad(@Parameter(description = "是否可见") boolean visible) {
        io.github.yahocen.acad.common.Disp.clear();
        var application = new Application(ActiveXComponent.createNewInstance(AUTOCAD_2024.getProgId()));
        application.setVisible(visible);
        return application;
    }

    @Operation(summary = "新建 AutoCAD 应用程序实例", description = "新建 AutoCAD 应用程序实例（默认可见）")
    public static Application createNewAcad() {
        return createNewAcad(true);
    }

    /**
     * 设置应用可见性
     * @param visible 可见性
     */
    @Operation(summary = "设置应用可见性", description = "设置应用可见性")
    public void setVisible(@Parameter(description = "可见性") boolean visible) {
        acadApp.setProperty("Visible", visible);
    }

    /**
     * 获取可见性
     * @return boolean
     */
    @Operation(summary = "获取可见性", description = "获取应用可见性")
    public boolean getVisible() {
        return acadApp.getProperty("Visible").getBoolean();
    }

    /**
     * 获取 AutoCAD 主窗口句柄 (HWND)
     * @return 窗口句柄数值，获取失败返回 0
     */
    @Operation(summary = "获取 AutoCAD 主窗口句柄", description = "获取 AutoCAD 主窗口句柄 (HWND)")
    public long getHwnd() {
        return Dispatch.get(acadApp.getObject(), "HWND").getLong();
    }

    /**
     * 将 CAD 窗口恢复到前台
     * <p>
     * 如果 CAD 窗口处于最小化状态（如通过 COM 操作后卡在任务栏），
     * 此方法用 Win32 API 异步恢复窗口到正常状态并置前。
     * 不依赖 COM 通道，即使 COM 消息泵阻塞也能工作。
     * </p>
     * @return true 表示恢复成功
     */
    @Operation(summary = "恢复 CAD 窗口到前台", description = "如果 CAD 窗口最小化/失焦，恢复到前台")
    public boolean bringToFront() {
        return CadWindowHelper.bringToFront(acadApp);
    }

    /**
     * 打开文档
     * @param filePath 文件路径
     * @return Document
     */
    @Operation(summary = "打开文档", description = "打开 DWG 文档")
    public Document openDocument(@Parameter(description = "文件路径") String filePath) {
        return this.documents.open(filePath);
    }

    /**
     * 退出 AutoCAD 应用程序
     */
    @Operation(summary = "退出 AutoCAD 应用程序", description = "退出 AutoCAD 应用程序")
    public void quit() {
        try {
            acadApp.invoke("Quit");
        } catch (ComFailException e) {
            throw new AcadException("退出AutoCAD应用程序失败：{}", e, e.getMessage());
        }
    }

    @Operation(summary = "执行表达式", description = "执行 AutoCAD 表达式")
    public void _eval(@Parameter(description = "表达式") String expression) {
        acadApp.invoke("Eval", expression);
    }

    /**
     * 获取当前活动文档
     * @return Document
     */
    @Operation(summary = "获取当前活动文档", description = "获取当前活动文档")
    public Document getActiveDocument() {
        try {
            return new Document(Dispatch.get(acadApp, "ActiveDocument").toDispatch());
        }catch (ComFailException cfe){
            log.error("获取当前活动文档失败：{}", cfe.getMessage());
            return null;
        }
    }

    @Operation(summary = "获取文档集合", description = "获取文档集合，可用 add() 新建图纸、open(path) 打开图纸")
    public Documents getDocuments() {
        return documents;
    }

}

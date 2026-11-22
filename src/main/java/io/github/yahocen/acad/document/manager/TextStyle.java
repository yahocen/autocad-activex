package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.common.AcadException;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AutoCAD文字样式对象
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-0531335D-26F3-404B-A4E1-1DE70BF0A307">TextStyle Object (ActiveX)</a>
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "TextStyle AutoCAD文字样式",
    description = "AutoCAD中文字样式的定义"
)
public class TextStyle extends AcadObject {

    public TextStyle(Dispatch acadElement) {
        super(acadElement);
    }

    /**
     * 获取文字样式名称
     * 
     * @return 文字样式名称
     */
    @Operation(summary = "获取文字样式名称", description = "获取文字样式名称")
    public String getName() {
        return getProperties("Name").getString();
    }

    /**
     * 设置字体样式名称
     * 
     * @param name 名称限制为 32 个字符，不能包含空格或标点符号
     */
    @Operation(summary = "设置字体样式名称", description = "设置字体样式名称。名称限制为 32 个字符，不能包含空格或标点符号")
    public void setName(@Parameter(description = "名称限制为 32 个字符，不能包含空格或标点符号") String name) {
        if (name == null || name.isEmpty()) {
            throw new AcadException("名称不能为空");
        }

        if (name.length() > 32) {
            throw new AcadException("名称超过 32 个字符，请缩短");
        }

        if (name.matches(".*[\\s\\p{Punct}].*")) {
            throw new AcadException("名称不能包含空格或标点符号");
        }

        try {
            setProperties("Name", name);
        } catch (Exception e) {
            throw new AcadException("设置名称失败：" + e.getMessage(), e);
        }
    }

    /**
     * 设置字体样式
     * 
     * @param typeface 字体名称
     * @param bold 是否加粗
     * @param italic 是否斜体
     * @param charSet 字符集
     * @param pitchAndFamily 字体间距和族
     */
    @Operation(summary = "设置字体样式", description = "设置字体样式")
    public void setFont(@Parameter(description = "字体名称") String typeface, @Parameter(description = "是否加粗") boolean bold, @Parameter(description = "是否斜体") boolean italic, @Parameter(description = "字符集") int charSet, @Parameter(description = "字体间距和族") int pitchAndFamily) {
        try {
            call("SetFont", typeface, bold, italic, charSet, pitchAndFamily);
        } catch (Exception e) {
            throw new AcadException("设置字体失败：" + e.getMessage(), e);
        }
    }

}
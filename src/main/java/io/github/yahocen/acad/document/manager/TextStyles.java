package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.common.ComCollection;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AutoCAD文字样式集合
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-22FC4F30-F03C-4D9C-83DE-FEAEB7135D7F">TextStyles Collection (ActiveX)</a>
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "TextStyles AutoCAD文字样式集合",
    description = "AutoCAD中所有文字样式的集合"
)
public class TextStyles extends ComCollection<TextStyle> {

    public TextStyles(Dispatch acadElement) {
        super(acadElement);
    }

    @Override
    protected TextStyle wrap(Dispatch dispatch) {
        return new TextStyle(dispatch);
    }

}

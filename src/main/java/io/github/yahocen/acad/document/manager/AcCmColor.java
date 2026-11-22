package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadAcCmColor — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class AcCmColor extends ComObject {

    public AcCmColor(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getBlue() {
        return Disp.get(getClass(), dispatch, "Blue");
    }

    public Variant getBookName() {
        return Disp.get(getClass(), dispatch, "BookName");
    }

    public int getColorIndex() {
        return Disp.get(getClass(), dispatch, "ColorIndex").getInt();
    }

    public AcCmColor setColorIndex(int colorIndex) {
        Disp.put(getClass(), dispatch, "ColorIndex", colorIndex);
        return this;
    }

    public int getColorMethod() {
        return Disp.get(getClass(), dispatch, "ColorMethod").getInt();
    }

    public AcCmColor setColorMethod(int colorMethod) {
        Disp.put(getClass(), dispatch, "ColorMethod", colorMethod);
        return this;
    }

    public Variant getColorName() {
        return Disp.get(getClass(), dispatch, "ColorName");
    }

    public Variant getEntityColor() {
        return Disp.get(getClass(), dispatch, "EntityColor");
    }

    public AcCmColor setEntityColor(Variant entityColor) {
        Disp.put(getClass(), dispatch, "EntityColor", entityColor);
        return this;
    }

    public Variant getGreen() {
        return Disp.get(getClass(), dispatch, "Green");
    }

    public Variant getRed() {
        return Disp.get(getClass(), dispatch, "Red");
    }

    public Variant setNames(Variant colorName, Variant bookName) {
        Variant result = Disp.call(getClass(), dispatch, "SetNames", colorName, bookName);
        return result;
    }

    public Variant delete() {
        Variant result = Disp.call(getClass(), dispatch, "Delete");
        return result;
    }

    public Variant setRGB(Variant red, Variant green, Variant blue) {
        Variant result = Disp.call(getClass(), dispatch, "SetRGB", red, green, blue);
        return result;
    }

    public Variant setColorBookColor(Variant bookName, Variant colorName) {
        Variant result = Disp.call(getClass(), dispatch, "SetColorBookColor", bookName, colorName);
        return result;
    }

}

package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadHyperlink — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class Hyperlink extends ComObject {

    public Hyperlink(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getApplication() {
        return Disp.get(getClass(), dispatch, "Application");
    }

    public Variant getURL() {
        return Disp.get(getClass(), dispatch, "URL");
    }

    public Hyperlink setURL(Variant uRL) {
        Disp.put(getClass(), dispatch, "URL", uRL);
        return this;
    }

    public Variant getURLDescription() {
        return Disp.get(getClass(), dispatch, "URLDescription");
    }

    public Hyperlink setURLDescription(Variant uRLDescription) {
        Disp.put(getClass(), dispatch, "URLDescription", uRLDescription);
        return this;
    }

    public Variant getURLNamedLocation() {
        return Disp.get(getClass(), dispatch, "URLNamedLocation");
    }

    public Hyperlink setURLNamedLocation(Variant uRLNamedLocation) {
        Disp.put(getClass(), dispatch, "URLNamedLocation", uRLNamedLocation);
        return this;
    }

    public Variant delete() {
        Variant result = Disp.call(getClass(), dispatch, "Delete");
        return result;
    }

}

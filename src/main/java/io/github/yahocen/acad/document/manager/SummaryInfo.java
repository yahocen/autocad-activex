package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadSummaryInfo — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class SummaryInfo extends ComObject {

    public SummaryInfo(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getAuthor() {
        return Disp.get(getClass(), dispatch, "Author");
    }

    public SummaryInfo setAuthor(Variant author) {
        Disp.put(getClass(), dispatch, "Author", author);
        return this;
    }

    public Variant getComments() {
        return Disp.get(getClass(), dispatch, "Comments");
    }

    public SummaryInfo setComments(Variant comments) {
        Disp.put(getClass(), dispatch, "Comments", comments);
        return this;
    }

    public Variant getHyperlinkBase() {
        return Disp.get(getClass(), dispatch, "HyperlinkBase");
    }

    public SummaryInfo setHyperlinkBase(Variant hyperlinkBase) {
        Disp.put(getClass(), dispatch, "HyperlinkBase", hyperlinkBase);
        return this;
    }

    public Variant getKeywords() {
        return Disp.get(getClass(), dispatch, "Keywords");
    }

    public SummaryInfo setKeywords(Variant keywords) {
        Disp.put(getClass(), dispatch, "Keywords", keywords);
        return this;
    }

    public Variant getLastSavedBy() {
        return Disp.get(getClass(), dispatch, "LastSavedBy");
    }

    public SummaryInfo setLastSavedBy(Variant lastSavedBy) {
        Disp.put(getClass(), dispatch, "LastSavedBy", lastSavedBy);
        return this;
    }

    public Variant getRevisionNumber() {
        return Disp.get(getClass(), dispatch, "RevisionNumber");
    }

    public SummaryInfo setRevisionNumber(Variant revisionNumber) {
        Disp.put(getClass(), dispatch, "RevisionNumber", revisionNumber);
        return this;
    }

    public Variant getSubject() {
        return Disp.get(getClass(), dispatch, "Subject");
    }

    public SummaryInfo setSubject(Variant subject) {
        Disp.put(getClass(), dispatch, "Subject", subject);
        return this;
    }

    public Variant getTitle() {
        return Disp.get(getClass(), dispatch, "Title");
    }

    public SummaryInfo setTitle(Variant title) {
        Disp.put(getClass(), dispatch, "Title", title);
        return this;
    }

    public Variant numCustomInfo() {
        Variant result = Disp.call(getClass(), dispatch, "NumCustomInfo");
        return result;
    }

    public Variant getCustomByIndex(Variant index, Variant pKey, Variant pValue) {
        Variant result = Disp.call(getClass(), dispatch, "GetCustomByIndex", index, pKey, pValue);
        return result;
    }

    public Variant getCustomByKey(Variant key, Variant pValue) {
        Variant result = Disp.call(getClass(), dispatch, "GetCustomByKey", key, pValue);
        return result;
    }

    public Variant setCustomByIndex(Variant index, Variant key, Variant value) {
        Variant result = Disp.call(getClass(), dispatch, "SetCustomByIndex", index, key, value);
        return result;
    }

    public Variant setCustomByKey(Variant key, Variant value) {
        Variant result = Disp.call(getClass(), dispatch, "SetCustomByKey", key, value);
        return result;
    }

    public Variant addCustomInfo(Variant key, Variant value) {
        Variant result = Disp.call(getClass(), dispatch, "AddCustomInfo", key, value);
        return result;
    }

    public Variant removeCustomByIndex(Variant index) {
        Variant result = Disp.call(getClass(), dispatch, "RemoveCustomByIndex", index);
        return result;
    }

    public Variant removeCustomByKey(Variant key) {
        Variant result = Disp.call(getClass(), dispatch, "RemoveCustomByKey", key);
        return result;
    }

}

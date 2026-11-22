package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadSecurityParams — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class SecurityParams extends ComObject {

    public SecurityParams(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getAction() {
        return Disp.get(getClass(), dispatch, "Action");
    }

    public SecurityParams setAction(Variant action) {
        Disp.put(getClass(), dispatch, "Action", action);
        return this;
    }

    public Variant getAlgorithm() {
        return Disp.get(getClass(), dispatch, "Algorithm");
    }

    public SecurityParams setAlgorithm(Variant algorithm) {
        Disp.put(getClass(), dispatch, "Algorithm", algorithm);
        return this;
    }

    public Variant getComment() {
        return Disp.get(getClass(), dispatch, "Comment");
    }

    public SecurityParams setComment(Variant comment) {
        Disp.put(getClass(), dispatch, "Comment", comment);
        return this;
    }

    public Variant getIssuer() {
        return Disp.get(getClass(), dispatch, "Issuer");
    }

    public SecurityParams setIssuer(Variant issuer) {
        Disp.put(getClass(), dispatch, "Issuer", issuer);
        return this;
    }

    public Variant getKeyLength() {
        return Disp.get(getClass(), dispatch, "KeyLength");
    }

    public SecurityParams setKeyLength(Variant keyLength) {
        Disp.put(getClass(), dispatch, "KeyLength", keyLength);
        return this;
    }

    public Variant getPassword() {
        return Disp.get(getClass(), dispatch, "Password");
    }

    public SecurityParams setPassword(Variant password) {
        Disp.put(getClass(), dispatch, "Password", password);
        return this;
    }

    public Variant getProviderName() {
        return Disp.get(getClass(), dispatch, "ProviderName");
    }

    public SecurityParams setProviderName(Variant providerName) {
        Disp.put(getClass(), dispatch, "ProviderName", providerName);
        return this;
    }

    public Variant getProviderType() {
        return Disp.get(getClass(), dispatch, "ProviderType");
    }

    public SecurityParams setProviderType(Variant providerType) {
        Disp.put(getClass(), dispatch, "ProviderType", providerType);
        return this;
    }

    public Variant getSerialNumber() {
        return Disp.get(getClass(), dispatch, "SerialNumber");
    }

    public SecurityParams setSerialNumber(Variant serialNumber) {
        Disp.put(getClass(), dispatch, "SerialNumber", serialNumber);
        return this;
    }

    public Variant getSubject() {
        return Disp.get(getClass(), dispatch, "Subject");
    }

    public SecurityParams setSubject(Variant subject) {
        Disp.put(getClass(), dispatch, "Subject", subject);
        return this;
    }

    public Variant getTimeServer() {
        return Disp.get(getClass(), dispatch, "TimeServer");
    }

    public SecurityParams setTimeServer(Variant timeServer) {
        Disp.put(getClass(), dispatch, "TimeServer", timeServer);
        return this;
    }

}

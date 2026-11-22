package io.github.yahocen.acad.document.manager;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.ComObject;

/**
 * AcadPreferencesSelection — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class PreferencesSelection extends ComObject {

    public PreferencesSelection(Dispatch dispatch) {
        super(dispatch);
    }
    public Dispatch getApplication() {
        return Disp.get(getClass(), dispatch, "Application").getDispatch();
    }

    public Variant getDisplayGrips() {
        return Disp.get(getClass(), dispatch, "DisplayGrips");
    }

    public PreferencesSelection setDisplayGrips(Variant displayGrips) {
        Disp.put(getClass(), dispatch, "DisplayGrips", displayGrips);
        return this;
    }

    public Variant getDisplayGripsWithinBlocks() {
        return Disp.get(getClass(), dispatch, "DisplayGripsWithinBlocks");
    }

    public PreferencesSelection setDisplayGripsWithinBlocks(Variant displayGripsWithinBlocks) {
        Disp.put(getClass(), dispatch, "DisplayGripsWithinBlocks", displayGripsWithinBlocks);
        return this;
    }

    public int getGripColorSelected() {
        return Disp.get(getClass(), dispatch, "GripColorSelected").getInt();
    }

    public PreferencesSelection setGripColorSelected(int gripColorSelected) {
        Disp.put(getClass(), dispatch, "GripColorSelected", gripColorSelected);
        return this;
    }

    public int getGripColorUnselected() {
        return Disp.get(getClass(), dispatch, "GripColorUnselected").getInt();
    }

    public PreferencesSelection setGripColorUnselected(int gripColorUnselected) {
        Disp.put(getClass(), dispatch, "GripColorUnselected", gripColorUnselected);
        return this;
    }

    public Variant getGripSize() {
        return Disp.get(getClass(), dispatch, "GripSize");
    }

    public PreferencesSelection setGripSize(Variant gripSize) {
        Disp.put(getClass(), dispatch, "GripSize", gripSize);
        return this;
    }

    public Variant getPickAdd() {
        return Disp.get(getClass(), dispatch, "PickAdd");
    }

    public PreferencesSelection setPickAdd(Variant pickAdd) {
        Disp.put(getClass(), dispatch, "PickAdd", pickAdd);
        return this;
    }

    public Variant getPickAuto() {
        return Disp.get(getClass(), dispatch, "PickAuto");
    }

    public PreferencesSelection setPickAuto(Variant pickAuto) {
        Disp.put(getClass(), dispatch, "PickAuto", pickAuto);
        return this;
    }

    public Variant getPickBoxSize() {
        return Disp.get(getClass(), dispatch, "PickBoxSize");
    }

    public PreferencesSelection setPickBoxSize(Variant pickBoxSize) {
        Disp.put(getClass(), dispatch, "PickBoxSize", pickBoxSize);
        return this;
    }

    public Variant getPickDrag() {
        return Disp.get(getClass(), dispatch, "PickDrag");
    }

    public PreferencesSelection setPickDrag(Variant pickDrag) {
        Disp.put(getClass(), dispatch, "PickDrag", pickDrag);
        return this;
    }

    public Variant getPickFirst() {
        return Disp.get(getClass(), dispatch, "PickFirst");
    }

    public PreferencesSelection setPickFirst(Variant pickFirst) {
        Disp.put(getClass(), dispatch, "PickFirst", pickFirst);
        return this;
    }

    public Variant getPickGroup() {
        return Disp.get(getClass(), dispatch, "PickGroup");
    }

    public PreferencesSelection setPickGroup(Variant pickGroup) {
        Disp.put(getClass(), dispatch, "PickGroup", pickGroup);
        return this;
    }

}

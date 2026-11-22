package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * Attribute - AutoCAD Attribute 实体
 */
@AcadEntityName("AcDbAttributeDefinition")
public class Attribute extends AcadEntity {

    public Attribute(Dispatch dispatch) {
        super(dispatch);
    }

    public int getAlignment() {
        return getProperties("Alignment").getInt();
    }

    public Attribute setAlignment(int alignment) {
        setProperties("Alignment", alignment);
        return this;
    }

    public Variant getBackward() {
        return getProperties("Backward");
    }

    public Attribute setBackward(Variant backward) {
        setProperties("Backward", backward);
        return this;
    }

    public Variant getConstant() {
        return getProperties("Constant");
    }

    public Attribute setConstant(Variant constant) {
        setProperties("Constant", constant);
        return this;
    }

    public int getFieldLength() {
        return getProperties("FieldLength").getInt();
    }

    public Attribute setFieldLength(int fieldLength) {
        setProperties("FieldLength", fieldLength);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public Attribute setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public int getHorizontalAlignment() {
        return getProperties("HorizontalAlignment").getInt();
    }

    public Attribute setHorizontalAlignment(int horizontalAlignment) {
        setProperties("HorizontalAlignment", horizontalAlignment);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public Attribute setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public boolean getInvisible() {
        return getProperties("Invisible").getBoolean();
    }

    public Attribute setInvisible(boolean invisible) {
        setProperties("Invisible", invisible);
        return this;
    }

    public Variant getLockPosition() {
        return getProperties("LockPosition");
    }

    public Attribute setLockPosition(Variant lockPosition) {
        setProperties("LockPosition", lockPosition);
        return this;
    }

    public Variant getMTextAttribute() {
        return getProperties("MTextAttribute");
    }

    public Attribute setMTextAttribute(Variant mTextAttribute) {
        setProperties("MTextAttribute", mTextAttribute);
        return this;
    }

    public Variant getMTextAttributeContent() {
        return getProperties("MTextAttributeContent");
    }

    public Attribute setMTextAttributeContent(Variant mTextAttributeContent) {
        setProperties("MTextAttributeContent", mTextAttributeContent);
        return this;
    }

    public double getMTextBoundaryWidth() {
        return getProperties("MTextBoundaryWidth").getDouble();
    }

    public Attribute setMTextBoundaryWidth(double mTextBoundaryWidth) {
        setProperties("MTextBoundaryWidth", mTextBoundaryWidth);
        return this;
    }

    public int getMTextDrawingDirection() {
        return getProperties("MTextDrawingDirection").getInt();
    }

    public Attribute setMTextDrawingDirection(int mTextDrawingDirection) {
        setProperties("MTextDrawingDirection", mTextDrawingDirection);
        return this;
    }

    public Variant getMode() {
        return getProperties("Mode");
    }

    public Attribute setMode(Variant mode) {
        setProperties("Mode", mode);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public Attribute setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getObliqueAngle() {
        return getProperties("ObliqueAngle").getDouble();
    }

    public Attribute setObliqueAngle(double obliqueAngle) {
        setProperties("ObliqueAngle", obliqueAngle);
        return this;
    }

    public Variant getPreset() {
        return getProperties("Preset");
    }

    public Attribute setPreset(Variant preset) {
        setProperties("Preset", preset);
        return this;
    }

    public Variant getPromptString() {
        return getProperties("PromptString");
    }

    public Attribute setPromptString(Variant promptString) {
        setProperties("PromptString", promptString);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public Attribute setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public Attribute setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public Attribute setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public String getTagString() {
        return getProperties("TagString").getString();
    }

    public Attribute setTagString(String tagString) {
        setProperties("TagString", tagString);
        return this;
    }

    public Point getTextAlignmentPoint() {
        return Point.of(getProperties("TextAlignmentPoint").toSafeArray());
    }

    public Attribute setTextAlignmentPoint(Point textAlignmentPoint) {
        setProperties("TextAlignmentPoint", textAlignmentPoint.toVariant());
        return this;
    }

    public Variant getTextGenerationFlag() {
        return getProperties("TextGenerationFlag");
    }

    public Attribute setTextGenerationFlag(Variant textGenerationFlag) {
        setProperties("TextGenerationFlag", textGenerationFlag);
        return this;
    }

    public String getTextString() {
        return getProperties("TextString").getString();
    }

    public Attribute setTextString(String textString) {
        setProperties("TextString", textString);
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public Attribute setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

    public Variant getUpsideDown() {
        return getProperties("UpsideDown");
    }

    public Attribute setUpsideDown(Variant upsideDown) {
        setProperties("UpsideDown", upsideDown);
        return this;
    }

    public Variant getVerify() {
        return getProperties("Verify");
    }

    public Attribute setVerify(Variant verify) {
        setProperties("Verify", verify);
        return this;
    }

    public int getVerticalAlignment() {
        return getProperties("VerticalAlignment").getInt();
    }

    public Attribute setVerticalAlignment(int verticalAlignment) {
        setProperties("VerticalAlignment", verticalAlignment);
        return this;
    }

    public Variant updateMTextAttribute() {
        return call("UpdateMTextAttribute");
    }

}
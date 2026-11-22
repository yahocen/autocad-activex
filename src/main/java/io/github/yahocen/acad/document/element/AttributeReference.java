package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * AttributeReference - AutoCAD AttributeReference 实体
 */
@AcadEntityName("AcDbAttribute")
public class AttributeReference extends AcadEntity {

    public AttributeReference(Dispatch dispatch) {
        super(dispatch);
    }

    public int getAlignment() {
        return getProperties("Alignment").getInt();
    }

    public AttributeReference setAlignment(int alignment) {
        setProperties("Alignment", alignment);
        return this;
    }

    public Variant getBackward() {
        return getProperties("Backward");
    }

    public AttributeReference setBackward(Variant backward) {
        setProperties("Backward", backward);
        return this;
    }

    public Variant getConstant() {
        return getProperties("Constant");
    }

    public int getFieldLength() {
        return getProperties("FieldLength").getInt();
    }

    public AttributeReference setFieldLength(int fieldLength) {
        setProperties("FieldLength", fieldLength);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public AttributeReference setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public int getHorizontalAlignment() {
        return getProperties("HorizontalAlignment").getInt();
    }

    public AttributeReference setHorizontalAlignment(int horizontalAlignment) {
        setProperties("HorizontalAlignment", horizontalAlignment);
        return this;
    }

    public Point getInsertionPoint() {
        return Point.of(getProperties("InsertionPoint").toSafeArray());
    }

    public AttributeReference setInsertionPoint(Point insertionPoint) {
        setProperties("InsertionPoint", insertionPoint.toVariant());
        return this;
    }

    public boolean getInvisible() {
        return getProperties("Invisible").getBoolean();
    }

    public AttributeReference setInvisible(boolean invisible) {
        setProperties("Invisible", invisible);
        return this;
    }

    public Variant getLockPosition() {
        return getProperties("LockPosition");
    }

    public Variant getMTextAttribute() {
        return getProperties("MTextAttribute");
    }

    public AttributeReference setMTextAttribute(Variant mTextAttribute) {
        setProperties("MTextAttribute", mTextAttribute);
        return this;
    }

    public Variant getMTextAttributeContent() {
        return getProperties("MTextAttributeContent");
    }

    public AttributeReference setMTextAttributeContent(Variant mTextAttributeContent) {
        setProperties("MTextAttributeContent", mTextAttributeContent);
        return this;
    }

    public double getMTextBoundaryWidth() {
        return getProperties("MTextBoundaryWidth").getDouble();
    }

    public AttributeReference setMTextBoundaryWidth(double mTextBoundaryWidth) {
        setProperties("MTextBoundaryWidth", mTextBoundaryWidth);
        return this;
    }

    public int getMTextDrawingDirection() {
        return getProperties("MTextDrawingDirection").getInt();
    }

    public AttributeReference setMTextDrawingDirection(int mTextDrawingDirection) {
        setProperties("MTextDrawingDirection", mTextDrawingDirection);
        return this;
    }

    public Variant getNormal() {
        return getProperties("Normal");
    }

    public AttributeReference setNormal(Variant normal) {
        setProperties("Normal", normal);
        return this;
    }

    public double getObliqueAngle() {
        return getProperties("ObliqueAngle").getDouble();
    }

    public AttributeReference setObliqueAngle(double obliqueAngle) {
        setProperties("ObliqueAngle", obliqueAngle);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public AttributeReference setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public AttributeReference setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public AttributeReference setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public String getTagString() {
        return getProperties("TagString").getString();
    }

    public AttributeReference setTagString(String tagString) {
        setProperties("TagString", tagString);
        return this;
    }

    public Point getTextAlignmentPoint() {
        return Point.of(getProperties("TextAlignmentPoint").toSafeArray());
    }

    public AttributeReference setTextAlignmentPoint(Point textAlignmentPoint) {
        setProperties("TextAlignmentPoint", textAlignmentPoint.toVariant());
        return this;
    }

    public Variant getTextGenerationFlag() {
        return getProperties("TextGenerationFlag");
    }

    public AttributeReference setTextGenerationFlag(Variant textGenerationFlag) {
        setProperties("TextGenerationFlag", textGenerationFlag);
        return this;
    }

    public String getTextString() {
        return getProperties("TextString").getString();
    }

    public AttributeReference setTextString(String textString) {
        setProperties("TextString", textString);
        return this;
    }

    public double getThickness() {
        return getProperties("Thickness").getDouble();
    }

    public AttributeReference setThickness(double thickness) {
        setProperties("Thickness", thickness);
        return this;
    }

    public Variant getUpsideDown() {
        return getProperties("UpsideDown");
    }

    public AttributeReference setUpsideDown(Variant upsideDown) {
        setProperties("UpsideDown", upsideDown);
        return this;
    }

    public int getVerticalAlignment() {
        return getProperties("VerticalAlignment").getInt();
    }

    public AttributeReference setVerticalAlignment(int verticalAlignment) {
        setProperties("VerticalAlignment", verticalAlignment);
        return this;
    }

    public Variant updateMTextAttribute() {
        return call("UpdateMTextAttribute");
    }

}
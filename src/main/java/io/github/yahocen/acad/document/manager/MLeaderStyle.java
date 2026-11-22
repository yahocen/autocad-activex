// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.manager;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.AcadObject;

/**
 * AcadMLeaderStyle — AutoCAD 2024 ActiveX COM object
 * <p>Auto-generated from acax24enu.tlb type library.</p>
 */
public class MLeaderStyle extends AcadObject {

    public MLeaderStyle(Dispatch dispatch) {
        super(dispatch);
    }
    public Variant getAlignSpace() {
        return getProperties("AlignSpace");
    }

    public MLeaderStyle setAlignSpace(Variant alignSpace) {
        setProperties("AlignSpace", alignSpace);
        return this;
    }

    public Variant getAnnotative() {
        return getProperties("Annotative");
    }

    public MLeaderStyle setAnnotative(Variant annotative) {
        setProperties("Annotative", annotative);
        return this;
    }

    public Variant getArrowSize() {
        return getProperties("ArrowSize");
    }

    public MLeaderStyle setArrowSize(Variant arrowSize) {
        setProperties("ArrowSize", arrowSize);
        return this;
    }

    public Variant getArrowSymbol() {
        return getProperties("ArrowSymbol");
    }

    public MLeaderStyle setArrowSymbol(Variant arrowSymbol) {
        setProperties("ArrowSymbol", arrowSymbol);
        return this;
    }

    public Variant getBitFlags() {
        return getProperties("BitFlags");
    }

    public MLeaderStyle setBitFlags(Variant bitFlags) {
        setProperties("BitFlags", bitFlags);
        return this;
    }

    public Variant getBlock() {
        return getProperties("Block");
    }

    public MLeaderStyle setBlock(Variant block) {
        setProperties("Block", block);
        return this;
    }

    public AcCmColor getBlockColor() {
        return new AcCmColor(getProperties("BlockColor").getDispatch());
    }

    public MLeaderStyle setBlockColor(AcCmColor blockColor) {
        setProperties("BlockColor", blockColor.getDispatch());
        return this;
    }

    public int getBlockConnectionType() {
        return getProperties("BlockConnectionType").getInt();
    }

    public MLeaderStyle setBlockConnectionType(int blockConnectionType) {
        setProperties("BlockConnectionType", blockConnectionType);
        return this;
    }

    public Variant getBlockRotation() {
        return getProperties("BlockRotation");
    }

    public MLeaderStyle setBlockRotation(Variant blockRotation) {
        setProperties("BlockRotation", blockRotation);
        return this;
    }

    public Variant getBlockScale() {
        return getProperties("BlockScale");
    }

    public MLeaderStyle setBlockScale(Variant blockScale) {
        setProperties("BlockScale", blockScale);
        return this;
    }

    public Variant getBreakSize() {
        return getProperties("BreakSize");
    }

    public MLeaderStyle setBreakSize(Variant breakSize) {
        setProperties("BreakSize", breakSize);
        return this;
    }

    public int getContentType() {
        return getProperties("ContentType").getInt();
    }

    public MLeaderStyle setContentType(int contentType) {
        setProperties("ContentType", contentType);
        return this;
    }

    public Variant getDescription() {
        return getProperties("Description");
    }

    public MLeaderStyle setDescription(Variant description) {
        setProperties("Description", description);
        return this;
    }

    public Variant getDoglegLength() {
        return getProperties("DoglegLength");
    }

    public MLeaderStyle setDoglegLength(Variant doglegLength) {
        setProperties("DoglegLength", doglegLength);
        return this;
    }

    public int getDrawLeaderOrderType() {
        return getProperties("DrawLeaderOrderType").getInt();
    }

    public MLeaderStyle setDrawLeaderOrderType(int drawLeaderOrderType) {
        setProperties("DrawLeaderOrderType", drawLeaderOrderType);
        return this;
    }

    public int getDrawMLeaderOrderType() {
        return getProperties("DrawMLeaderOrderType").getInt();
    }

    public MLeaderStyle setDrawMLeaderOrderType(int drawMLeaderOrderType) {
        setProperties("DrawMLeaderOrderType", drawMLeaderOrderType);
        return this;
    }

    public Variant getEnableBlockRotation() {
        return getProperties("EnableBlockRotation");
    }

    public MLeaderStyle setEnableBlockRotation(Variant enableBlockRotation) {
        setProperties("EnableBlockRotation", enableBlockRotation);
        return this;
    }

    public Variant getEnableBlockScale() {
        return getProperties("EnableBlockScale");
    }

    public MLeaderStyle setEnableBlockScale(Variant enableBlockScale) {
        setProperties("EnableBlockScale", enableBlockScale);
        return this;
    }

    public Variant getEnableDogleg() {
        return getProperties("EnableDogleg");
    }

    public MLeaderStyle setEnableDogleg(Variant enableDogleg) {
        setProperties("EnableDogleg", enableDogleg);
        return this;
    }

    public Variant getEnableFrameText() {
        return getProperties("EnableFrameText");
    }

    public MLeaderStyle setEnableFrameText(Variant enableFrameText) {
        setProperties("EnableFrameText", enableFrameText);
        return this;
    }

    public Variant getEnableLanding() {
        return getProperties("EnableLanding");
    }

    public MLeaderStyle setEnableLanding(Variant enableLanding) {
        setProperties("EnableLanding", enableLanding);
        return this;
    }

    public int getFirstSegmentAngleConstraint() {
        return getProperties("FirstSegmentAngleConstraint").getInt();
    }

    public MLeaderStyle setFirstSegmentAngleConstraint(int firstSegmentAngleConstraint) {
        setProperties("FirstSegmentAngleConstraint", firstSegmentAngleConstraint);
        return this;
    }

    public Variant getLandingGap() {
        return getProperties("LandingGap");
    }

    public MLeaderStyle setLandingGap(Variant landingGap) {
        setProperties("LandingGap", landingGap);
        return this;
    }

    public AcCmColor getLeaderLineColor() {
        return new AcCmColor(getProperties("LeaderLineColor").getDispatch());
    }

    public MLeaderStyle setLeaderLineColor(AcCmColor leaderLineColor) {
        setProperties("LeaderLineColor", leaderLineColor.getDispatch());
        return this;
    }

    public String getLeaderLineTypeId() {
        return getProperties("LeaderLineTypeId").getString();
    }

    public MLeaderStyle setLeaderLineTypeId(String leaderLineTypeId) {
        setProperties("LeaderLineTypeId", leaderLineTypeId);
        return this;
    }

    public int getLeaderLineWeight() {
        return getProperties("LeaderLineWeight").getInt();
    }

    public MLeaderStyle setLeaderLineWeight(int leaderLineWeight) {
        setProperties("LeaderLineWeight", leaderLineWeight);
        return this;
    }

    public int getLeaderLinetype() {
        return getProperties("LeaderLinetype").getInt();
    }

    public MLeaderStyle setLeaderLinetype(int leaderLinetype) {
        setProperties("LeaderLinetype", leaderLinetype);
        return this;
    }

    public Variant getMaxLeaderSegmentsPoints() {
        return getProperties("MaxLeaderSegmentsPoints");
    }

    public MLeaderStyle setMaxLeaderSegmentsPoints(Variant maxLeaderSegmentsPoints) {
        setProperties("MaxLeaderSegmentsPoints", maxLeaderSegmentsPoints);
        return this;
    }

    public Variant getName() {
        return getProperties("Name");
    }

    public MLeaderStyle setName(Variant name) {
        setProperties("Name", name);
        return this;
    }

    public Variant getOverwritePropChanged() {
        return getProperties("OverwritePropChanged");
    }

    public Variant getScaleFactor() {
        return getProperties("ScaleFactor");
    }

    public MLeaderStyle setScaleFactor(Variant scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public int getSecondSegmentAngleConstraint() {
        return getProperties("SecondSegmentAngleConstraint").getInt();
    }

    public MLeaderStyle setSecondSegmentAngleConstraint(int secondSegmentAngleConstraint) {
        setProperties("SecondSegmentAngleConstraint", secondSegmentAngleConstraint);
        return this;
    }

    public int getTextAlignmentType() {
        return getProperties("TextAlignmentType").getInt();
    }

    public MLeaderStyle setTextAlignmentType(int textAlignmentType) {
        setProperties("TextAlignmentType", textAlignmentType);
        return this;
    }

    public int getTextAngleType() {
        return getProperties("TextAngleType").getInt();
    }

    public MLeaderStyle setTextAngleType(int textAngleType) {
        setProperties("TextAngleType", textAngleType);
        return this;
    }

    public int getTextAttachmentDirection() {
        return getProperties("TextAttachmentDirection").getInt();
    }

    public MLeaderStyle setTextAttachmentDirection(int textAttachmentDirection) {
        setProperties("TextAttachmentDirection", textAttachmentDirection);
        return this;
    }

    public int getTextBottomAttachmentType() {
        return getProperties("TextBottomAttachmentType").getInt();
    }

    public MLeaderStyle setTextBottomAttachmentType(int textBottomAttachmentType) {
        setProperties("TextBottomAttachmentType", textBottomAttachmentType);
        return this;
    }

    public AcCmColor getTextColor() {
        return new AcCmColor(getProperties("TextColor").getDispatch());
    }

    public MLeaderStyle setTextColor(AcCmColor textColor) {
        setProperties("TextColor", textColor.getDispatch());
        return this;
    }

    public Variant getTextHeight() {
        return getProperties("TextHeight");
    }

    public MLeaderStyle setTextHeight(Variant textHeight) {
        setProperties("TextHeight", textHeight);
        return this;
    }

    public int getTextLeftAttachmentType() {
        return getProperties("TextLeftAttachmentType").getInt();
    }

    public MLeaderStyle setTextLeftAttachmentType(int textLeftAttachmentType) {
        setProperties("TextLeftAttachmentType", textLeftAttachmentType);
        return this;
    }

    public int getTextRightAttachmentType() {
        return getProperties("TextRightAttachmentType").getInt();
    }

    public MLeaderStyle setTextRightAttachmentType(int textRightAttachmentType) {
        setProperties("TextRightAttachmentType", textRightAttachmentType);
        return this;
    }

    public Variant getTextString() {
        return getProperties("TextString");
    }

    public MLeaderStyle setTextString(Variant textString) {
        setProperties("TextString", textString);
        return this;
    }

    public Variant getTextStyle() {
        return getProperties("TextStyle");
    }

    public MLeaderStyle setTextStyle(Variant textStyle) {
        setProperties("TextStyle", textStyle);
        return this;
    }

    public int getTextTopAttachmentType() {
        return getProperties("TextTopAttachmentType").getInt();
    }

    public MLeaderStyle setTextTopAttachmentType(int textTopAttachmentType) {
        setProperties("TextTopAttachmentType", textTopAttachmentType);
        return this;
    }

}

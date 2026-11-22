package io.github.yahocen.acad.document.element;

import io.github.yahocen.acad.document.manager.AcCmColor;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * MLeader - AutoCAD MLeader 实体
 */
@AcadEntityName("AcDbMLeader")
public class MLeader extends AcadEntity {

    public MLeader(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getArrowheadBlock() {
        return getProperties("ArrowheadBlock");
    }

    public MLeader setArrowheadBlock(Variant arrowheadBlock) {
        setProperties("ArrowheadBlock", arrowheadBlock);
        return this;
    }

    public Variant getArrowheadSize() {
        return getProperties("ArrowheadSize");
    }

    public MLeader setArrowheadSize(Variant arrowheadSize) {
        setProperties("ArrowheadSize", arrowheadSize);
        return this;
    }

    public int getArrowheadType() {
        return getProperties("ArrowheadType").getInt();
    }

    public MLeader setArrowheadType(int arrowheadType) {
        setProperties("ArrowheadType", arrowheadType);
        return this;
    }

    public int getBlockConnectionType() {
        return getProperties("BlockConnectionType").getInt();
    }

    public MLeader setBlockConnectionType(int blockConnectionType) {
        setProperties("BlockConnectionType", blockConnectionType);
        return this;
    }

    public double getBlockScale() {
        return getProperties("BlockScale").getDouble();
    }

    public MLeader setBlockScale(double blockScale) {
        setProperties("BlockScale", blockScale);
        return this;
    }

    public String getContentBlockName() {
        return getProperties("ContentBlockName").getString();
    }

    public MLeader setContentBlockName(String contentBlockName) {
        setProperties("ContentBlockName", contentBlockName);
        return this;
    }

    public int getContentBlockType() {
        return getProperties("ContentBlockType").getInt();
    }

    public MLeader setContentBlockType(int contentBlockType) {
        setProperties("ContentBlockType", contentBlockType);
        return this;
    }

    public int getContentType() {
        return getProperties("ContentType").getInt();
    }

    public MLeader setContentType(int contentType) {
        setProperties("ContentType", contentType);
        return this;
    }

    public Variant getDogLegged() {
        return getProperties("DogLegged");
    }

    public MLeader setDogLegged(Variant dogLegged) {
        setProperties("DogLegged", dogLegged);
        return this;
    }

    public double getDoglegLength() {
        return getProperties("DoglegLength").getDouble();
    }

    public MLeader setDoglegLength(double doglegLength) {
        setProperties("DoglegLength", doglegLength);
        return this;
    }

    public Variant getLandingGap() {
        return getProperties("LandingGap");
    }

    public MLeader setLandingGap(Variant landingGap) {
        setProperties("LandingGap", landingGap);
        return this;
    }

    public int getLeaderCount() {
        return getProperties("LeaderCount").getInt();
    }

    public AcCmColor getLeaderLineColor() {
        return new AcCmColor(getProperties("LeaderLineColor").getDispatch());
    }

    public MLeader setLeaderLineColor(AcCmColor leaderLineColor) {
        setProperties("LeaderLineColor", leaderLineColor.getDispatch());
        return this;
    }

    public int getLeaderLineWeight() {
        return getProperties("LeaderLineWeight").getInt();
    }

    public MLeader setLeaderLineWeight(int leaderLineWeight) {
        setProperties("LeaderLineWeight", leaderLineWeight);
        return this;
    }

    public String getLeaderLinetype() {
        return getProperties("LeaderLinetype").getString();
    }

    public MLeader setLeaderLinetype(String leaderLinetype) {
        setProperties("LeaderLinetype", leaderLinetype);
        return this;
    }

    public int getLeaderType() {
        return getProperties("LeaderType").getInt();
    }

    public MLeader setLeaderType(int leaderType) {
        setProperties("LeaderType", leaderType);
        return this;
    }

    public double getScaleFactor() {
        return getProperties("ScaleFactor").getDouble();
    }

    public MLeader setScaleFactor(double scaleFactor) {
        setProperties("ScaleFactor", scaleFactor);
        return this;
    }

    public String getStyleName() {
        return getProperties("StyleName").getString();
    }

    public MLeader setStyleName(String styleName) {
        setProperties("StyleName", styleName);
        return this;
    }

    public int getTextAttachmentDirection() {
        return getProperties("TextAttachmentDirection").getInt();
    }

    public MLeader setTextAttachmentDirection(int textAttachmentDirection) {
        setProperties("TextAttachmentDirection", textAttachmentDirection);
        return this;
    }

    public Variant getTextBackgroundFill() {
        return getProperties("TextBackgroundFill");
    }

    public MLeader setTextBackgroundFill(Variant textBackgroundFill) {
        setProperties("TextBackgroundFill", textBackgroundFill);
        return this;
    }

    public int getTextBottomAttachmentType() {
        return getProperties("TextBottomAttachmentType").getInt();
    }

    public MLeader setTextBottomAttachmentType(int textBottomAttachmentType) {
        setProperties("TextBottomAttachmentType", textBottomAttachmentType);
        return this;
    }

    public int getTextDirection() {
        return getProperties("TextDirection").getInt();
    }

    public MLeader setTextDirection(int textDirection) {
        setProperties("TextDirection", textDirection);
        return this;
    }

    public Variant getTextFrameDisplay() {
        return getProperties("TextFrameDisplay");
    }

    public MLeader setTextFrameDisplay(Variant textFrameDisplay) {
        setProperties("TextFrameDisplay", textFrameDisplay);
        return this;
    }

    public double getTextHeight() {
        return getProperties("TextHeight").getDouble();
    }

    public MLeader setTextHeight(double textHeight) {
        setProperties("TextHeight", textHeight);
        return this;
    }

    public int getTextJustify() {
        return getProperties("TextJustify").getInt();
    }

    public MLeader setTextJustify(int textJustify) {
        setProperties("TextJustify", textJustify);
        return this;
    }

    public int getTextLeftAttachmentType() {
        return getProperties("TextLeftAttachmentType").getInt();
    }

    public MLeader setTextLeftAttachmentType(int textLeftAttachmentType) {
        setProperties("TextLeftAttachmentType", textLeftAttachmentType);
        return this;
    }

    public Variant getTextLineSpacingDistance() {
        return getProperties("TextLineSpacingDistance");
    }

    public MLeader setTextLineSpacingDistance(Variant textLineSpacingDistance) {
        setProperties("TextLineSpacingDistance", textLineSpacingDistance);
        return this;
    }

    public Variant getTextLineSpacingFactor() {
        return getProperties("TextLineSpacingFactor");
    }

    public MLeader setTextLineSpacingFactor(Variant textLineSpacingFactor) {
        setProperties("TextLineSpacingFactor", textLineSpacingFactor);
        return this;
    }

    public int getTextLineSpacingStyle() {
        return getProperties("TextLineSpacingStyle").getInt();
    }

    public MLeader setTextLineSpacingStyle(int textLineSpacingStyle) {
        setProperties("TextLineSpacingStyle", textLineSpacingStyle);
        return this;
    }

    public int getTextRightAttachmentType() {
        return getProperties("TextRightAttachmentType").getInt();
    }

    public MLeader setTextRightAttachmentType(int textRightAttachmentType) {
        setProperties("TextRightAttachmentType", textRightAttachmentType);
        return this;
    }

    public double getTextRotation() {
        return getProperties("TextRotation").getDouble();
    }

    public MLeader setTextRotation(double textRotation) {
        setProperties("TextRotation", textRotation);
        return this;
    }

    public String getTextString() {
        return getProperties("TextString").getString();
    }

    public MLeader setTextString(String textString) {
        setProperties("TextString", textString);
        return this;
    }

    public String getTextStyleName() {
        return getProperties("TextStyleName").getString();
    }

    public MLeader setTextStyleName(String textStyleName) {
        setProperties("TextStyleName", textStyleName);
        return this;
    }

    public int getTextTopAttachmentType() {
        return getProperties("TextTopAttachmentType").getInt();
    }

    public MLeader setTextTopAttachmentType(int textTopAttachmentType) {
        setProperties("TextTopAttachmentType", textTopAttachmentType);
        return this;
    }

    public double getTextWidth() {
        return getProperties("TextWidth").getDouble();
    }

    public MLeader setTextWidth(double textWidth) {
        setProperties("TextWidth", textWidth);
        return this;
    }

    public Variant addLeader() {
        return call("AddLeader");
    }

    public Variant removeLeader(Variant leaderIndex) {
        return call("RemoveLeader", leaderIndex);
    }

    public Variant addLeaderLine(Variant leaderIndex, Variant pointArray) {
        return call("AddLeaderLine", leaderIndex, pointArray);
    }

    public Variant addLeaderLineEx(Variant pointArray) {
        return call("AddLeaderLineEx", pointArray);
    }

    public Variant removeLeaderLine(Variant leaderLineIndex) {
        return call("RemoveLeaderLine", leaderLineIndex);
    }

    public Variant setLeaderLineVertices(Variant leaderLineIndex, Variant pointArray) {
        return call("SetLeaderLineVertices", leaderLineIndex, pointArray);
    }

    public Variant getLeaderLineVertices(Variant leaderLineIndex) {
        return call("GetLeaderLineVertices", leaderLineIndex);
    }

    public Variant getLeaderIndex(Variant leaderLineIndex) {
        return call("GetLeaderIndex", leaderLineIndex);
    }

    public Variant getLeaderLineIndexes(Variant leaderIndex) {
        return call("GetLeaderLineIndexes", leaderIndex);
    }

    public Variant getVertexCount(Variant leaderLineIndex) {
        return call("GetVertexCount", leaderLineIndex);
    }

    public Variant getDoglegDirection(Variant leaderIndex) {
        return call("GetDoglegDirection", leaderIndex);
    }

    public Variant setDoglegDirection(Variant leaderIndex, Variant dirVec) {
        return call("SetDoglegDirection", leaderIndex, dirVec);
    }

    public Variant getBlockAttributeValue(int attdefId) {
        return call("GetBlockAttributeValue", attdefId);
    }

    public Variant setBlockAttributeValue(int attdefId, Variant value) {
        return call("SetBlockAttributeValue", attdefId, value);
    }

}
package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * GeoPositionMarker - AutoCAD GeoPositionMarker 实体
 */
@AcadEntityName("AcDbGeoPositionMarker")
public class GeoPositionMarker extends AcadEntity {

    public GeoPositionMarker(Dispatch dispatch) {
        super(dispatch);
    }

    public Variant getAltitude() {
        return getProperties("Altitude");
    }

    public GeoPositionMarker setAltitude(Variant altitude) {
        setProperties("Altitude", altitude);
        return this;
    }

    public Variant getBackgroundFill() {
        return getProperties("BackgroundFill");
    }

    public GeoPositionMarker setBackgroundFill(Variant backgroundFill) {
        setProperties("BackgroundFill", backgroundFill);
        return this;
    }

    public int getDrawingDirection() {
        return getProperties("DrawingDirection").getInt();
    }

    public GeoPositionMarker setDrawingDirection(int drawingDirection) {
        setProperties("DrawingDirection", drawingDirection);
        return this;
    }

    public double getHeight() {
        return getProperties("Height").getDouble();
    }

    public GeoPositionMarker setHeight(double height) {
        setProperties("Height", height);
        return this;
    }

    public Variant getLandingGap() {
        return getProperties("LandingGap");
    }

    public GeoPositionMarker setLandingGap(Variant landingGap) {
        setProperties("LandingGap", landingGap);
        return this;
    }

    public Variant getLatitude() {
        return getProperties("Latitude");
    }

    public GeoPositionMarker setLatitude(Variant latitude) {
        setProperties("Latitude", latitude);
        return this;
    }

    public Variant getLineSpacingDistance() {
        return getProperties("LineSpacingDistance");
    }

    public GeoPositionMarker setLineSpacingDistance(Variant lineSpacingDistance) {
        setProperties("LineSpacingDistance", lineSpacingDistance);
        return this;
    }

    public Variant getLineSpacingFactor() {
        return getProperties("LineSpacingFactor");
    }

    public GeoPositionMarker setLineSpacingFactor(Variant lineSpacingFactor) {
        setProperties("LineSpacingFactor", lineSpacingFactor);
        return this;
    }

    public int getLineSpacingStyle() {
        return getProperties("LineSpacingStyle").getInt();
    }

    public GeoPositionMarker setLineSpacingStyle(int lineSpacingStyle) {
        setProperties("LineSpacingStyle", lineSpacingStyle);
        return this;
    }

    public Variant getLongitude() {
        return getProperties("Longitude");
    }

    public GeoPositionMarker setLongitude(Variant longitude) {
        setProperties("Longitude", longitude);
        return this;
    }

    public Variant getNotes() {
        return getProperties("Notes");
    }

    public GeoPositionMarker setNotes(Variant notes) {
        setProperties("Notes", notes);
        return this;
    }

    public Variant getPosition() {
        return getProperties("Position");
    }

    public GeoPositionMarker setPosition(Variant position) {
        setProperties("Position", position);
        return this;
    }

    public double getRadius() {
        return getProperties("Radius").getDouble();
    }

    public GeoPositionMarker setRadius(double radius) {
        setProperties("Radius", radius);
        return this;
    }

    public double getRotation() {
        return getProperties("Rotation").getDouble();
    }

    public GeoPositionMarker setRotation(double rotation) {
        setProperties("Rotation", rotation);
        return this;
    }

    public Variant getTextFrameDisplay() {
        return getProperties("TextFrameDisplay");
    }

    public GeoPositionMarker setTextFrameDisplay(Variant textFrameDisplay) {
        setProperties("TextFrameDisplay", textFrameDisplay);
        return this;
    }

    public int getTextJustify() {
        return getProperties("TextJustify").getInt();
    }

    public GeoPositionMarker setTextJustify(int textJustify) {
        setProperties("TextJustify", textJustify);
        return this;
    }

    public String getTextString() {
        return getProperties("TextString").getString();
    }

    public GeoPositionMarker setTextString(String textString) {
        setProperties("TextString", textString);
        return this;
    }

    public String getTextStyleName() {
        return getProperties("TextStyleName").getString();
    }

    public GeoPositionMarker setTextStyleName(String textStyleName) {
        setProperties("TextStyleName", textStyleName);
        return this;
    }

    public double getTextWidth() {
        return getProperties("TextWidth").getDouble();
    }

    public GeoPositionMarker setTextWidth(double textWidth) {
        setProperties("TextWidth", textWidth);
        return this;
    }

}
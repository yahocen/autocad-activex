package io.github.yahocen.acad.document.element;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.element.PointCloud;
import io.github.yahocen.acad.document.base.AcadEntity;

/**
 * PointCloudEx - AutoCAD PointCloudEx 实体
 */
public class PointCloudEx extends PointCloud {

    public PointCloudEx(Dispatch dispatch) {
        super(dispatch);
    }

    public String getColorScheme() {
        return getProperties("ColorScheme").getString();
    }

    public PointCloudEx setColorScheme(String colorScheme) {
        setProperties("ColorScheme", colorScheme);
        return this;
    }

    public Variant getGeolocate() {
        return getProperties("Geolocate");
    }

    public PointCloudEx setGeolocate(Variant geolocate) {
        setProperties("Geolocate", geolocate);
        return this;
    }

    public Variant getShowCropped() {
        return getProperties("ShowCropped");
    }

    public PointCloudEx setShowCropped(Variant showCropped) {
        setProperties("ShowCropped", showCropped);
        return this;
    }

}
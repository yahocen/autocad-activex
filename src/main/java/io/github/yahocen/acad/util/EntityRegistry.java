// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.util;

import java.util.Map;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.element.Arc;
import io.github.yahocen.acad.document.element.Attribute;
import io.github.yahocen.acad.document.element.AttributeReference;
import io.github.yahocen.acad.document.element.BlockReference;
import io.github.yahocen.acad.document.element.Circle;
import io.github.yahocen.acad.document.element.DgnUnderlay;
import io.github.yahocen.acad.document.element.Dim3PointAngular;
import io.github.yahocen.acad.document.element.DimAligned;
import io.github.yahocen.acad.document.element.DimAngular;
import io.github.yahocen.acad.document.element.DimArcLength;
import io.github.yahocen.acad.document.element.DimDiametric;
import io.github.yahocen.acad.document.element.DimOrdinate;
import io.github.yahocen.acad.document.element.DimRadial;
import io.github.yahocen.acad.document.element.DimRadialLarge;
import io.github.yahocen.acad.document.element.DimRotated;
import io.github.yahocen.acad.document.element.Dimension;
import io.github.yahocen.acad.document.element.DwfUnderlay;
import io.github.yahocen.acad.document.element.Ellipse;
import io.github.yahocen.acad.document.element.ExtrudedSurface;
import io.github.yahocen.acad.document.element.Face3D;
import io.github.yahocen.acad.document.element.GeoPositionMarker;
import io.github.yahocen.acad.document.element.GeomapImage;
import io.github.yahocen.acad.document.element.Hatch;
import io.github.yahocen.acad.document.element.Helix;
import io.github.yahocen.acad.document.element.LWPolyline;
import io.github.yahocen.acad.document.element.Leader;
import io.github.yahocen.acad.document.element.Line;
import io.github.yahocen.acad.document.element.LoftedSurface;
import io.github.yahocen.acad.document.element.MInsertBlock;
import io.github.yahocen.acad.document.element.MLeader;
import io.github.yahocen.acad.document.element.MLine;
import io.github.yahocen.acad.document.element.MText;
import io.github.yahocen.acad.document.element.NurbSurface;
import io.github.yahocen.acad.document.element.Ole;
import io.github.yahocen.acad.document.element.PViewport;
import io.github.yahocen.acad.document.element.PdfUnderlay;
import io.github.yahocen.acad.document.element.PlaneSurface;
import io.github.yahocen.acad.document.element.PointCloud;
import io.github.yahocen.acad.document.element.PointCloudEx2;
import io.github.yahocen.acad.document.element.PointEntity;
import io.github.yahocen.acad.document.element.PolyfaceMesh;
import io.github.yahocen.acad.document.element.PolygonMesh;
import io.github.yahocen.acad.document.element.Polyline;
import io.github.yahocen.acad.document.element.Polyline3D;
import io.github.yahocen.acad.document.element.RasterImage;
import io.github.yahocen.acad.document.element.Ray;
import io.github.yahocen.acad.document.element.Region;
import io.github.yahocen.acad.document.element.RevolvedSurface;
import io.github.yahocen.acad.document.element.Section;
import io.github.yahocen.acad.document.element.Shape;
import io.github.yahocen.acad.document.element.Solid2D;
import io.github.yahocen.acad.document.element.Solid3D;
import io.github.yahocen.acad.document.element.Spline;
import io.github.yahocen.acad.document.element.SubDMesh;
import io.github.yahocen.acad.document.element.Surface;
import io.github.yahocen.acad.document.element.SweptSurface;
import io.github.yahocen.acad.document.element.Table;
import io.github.yahocen.acad.document.element.Text;
import io.github.yahocen.acad.document.element.Tolerance;
import io.github.yahocen.acad.document.element.Trace;
import io.github.yahocen.acad.document.element.Wipeout;
import io.github.yahocen.acad.document.element.XLine;

/**
 * 实体注册表：AutoCAD ObjectName → 实体类。
 * <p>
 * 由 bin/codegen/gen_registry.py 从 @AcadEntityName 注解生成，勿手改。
 * 类加载即就绪，无运行时 classpath 扫描。
 */
public final class EntityRegistry {

    private EntityRegistry() {}

    public static final Map<String, Class<? extends AcadEntity>> ENTITIES = Map.ofEntries(
            Map.entry("AcDb2LineAngularDimension", DimAngular.class),
            Map.entry("AcDb2dPolyline", Polyline.class),
            Map.entry("AcDb3PointAngularDimension", Dim3PointAngular.class),
            Map.entry("AcDb3dPolyline", Polyline3D.class),
            Map.entry("AcDb3dSolid", Solid3D.class),
            Map.entry("AcDbAlignedDimension", DimAligned.class),
            Map.entry("AcDbArc", Arc.class),
            Map.entry("AcDbArcDimension", DimArcLength.class),
            Map.entry("AcDbAttribute", AttributeReference.class),
            Map.entry("AcDbAttributeDefinition", Attribute.class),
            Map.entry("AcDbBlockReference", BlockReference.class),
            Map.entry("AcDbCircle", Circle.class),
            Map.entry("AcDbDgnReference", DgnUnderlay.class),
            Map.entry("AcDbDiametricDimension", DimDiametric.class),
            Map.entry("AcDbDimension", Dimension.class),
            Map.entry("AcDbDwfReference", DwfUnderlay.class),
            Map.entry("AcDbEllipse", Ellipse.class),
            Map.entry("AcDbExtrudedSurface", ExtrudedSurface.class),
            Map.entry("AcDbFace", Face3D.class),
            Map.entry("AcDbFcf", Tolerance.class),
            Map.entry("AcDbGeoMapImage", GeomapImage.class),
            Map.entry("AcDbGeoPositionMarker", GeoPositionMarker.class),
            Map.entry("AcDbHatch", Hatch.class),
            Map.entry("AcDbHelix", Helix.class),
            Map.entry("AcDbLeader", Leader.class),
            Map.entry("AcDbLine", Line.class),
            Map.entry("AcDbLoftedSurface", LoftedSurface.class),
            Map.entry("AcDbMInsertBlock", MInsertBlock.class),
            Map.entry("AcDbMLeader", MLeader.class),
            Map.entry("AcDbMText", MText.class),
            Map.entry("AcDbMline", MLine.class),
            Map.entry("AcDbNurbSurface", NurbSurface.class),
            Map.entry("AcDbOle2Frame", Ole.class),
            Map.entry("AcDbOrdinateDimension", DimOrdinate.class),
            Map.entry("AcDbPdfReference", PdfUnderlay.class),
            Map.entry("AcDbPlaneSurface", PlaneSurface.class),
            Map.entry("AcDbPoint", PointEntity.class),
            Map.entry("AcDbPointCloud", PointCloud.class),
            Map.entry("AcDbPointCloudEx", PointCloudEx2.class),
            Map.entry("AcDbPolyFaceMesh", PolyfaceMesh.class),
            Map.entry("AcDbPolygonMesh", PolygonMesh.class),
            Map.entry("AcDbPolyline", LWPolyline.class),
            Map.entry("AcDbRadialDimension", DimRadial.class),
            Map.entry("AcDbRadialDimensionLarge", DimRadialLarge.class),
            Map.entry("AcDbRasterImage", RasterImage.class),
            Map.entry("AcDbRay", Ray.class),
            Map.entry("AcDbRegion", Region.class),
            Map.entry("AcDbRevolvedSurface", RevolvedSurface.class),
            Map.entry("AcDbRotatedDimension", DimRotated.class),
            Map.entry("AcDbSection", Section.class),
            Map.entry("AcDbShape", Shape.class),
            Map.entry("AcDbSolid", Solid2D.class),
            Map.entry("AcDbSpline", Spline.class),
            Map.entry("AcDbSubDMesh", SubDMesh.class),
            Map.entry("AcDbSurface", Surface.class),
            Map.entry("AcDbSweptSurface", SweptSurface.class),
            Map.entry("AcDbTable", Table.class),
            Map.entry("AcDbText", Text.class),
            Map.entry("AcDbTrace", Trace.class),
            Map.entry("AcDbViewport", PViewport.class),
            Map.entry("AcDbWipeout", Wipeout.class),
            Map.entry("AcDbXline", XLine.class)
    );
}

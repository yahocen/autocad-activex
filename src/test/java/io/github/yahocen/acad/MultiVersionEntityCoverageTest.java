// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad;

import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.document.Block;
import io.github.yahocen.acad.document.Document;
import io.github.yahocen.acad.document.ModelSpace;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.base.Point;
import io.github.yahocen.acad.document.element.*;
import io.github.yahocen.acad.document.manager.Layout;
import io.github.yahocen.acad.document.manager.Layouts;
import io.github.yahocen.acad.testsupport.AcadTestHarness;
import io.github.yahocen.acad.testsupport.CadSession;
import io.github.yahocen.acad.util.EntityUtil;
import io.github.yahocen.jacob.com.SafeArray;
import io.github.yahocen.jacob.com.Variant;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 多版本 AutoCAD 图元覆盖测试。
 * <p>
 * 对 {@code -Dacad.versions} 选出的每个版本（默认本机全部已安装版本）：
 * 启动该版本 CAD → 新建文档 → 逐一创建所有可经 COM API 创建的图元 →
 * 校验 ObjectName 与包装类 → 另存 DWG → 重新打开比对实体数 → 关闭退出。
 * </p>
 * <p>
 * 图元步骤用 {@link CadSession#step} 收集结果：单个图元失败不影响其余图元继续执行，
 * 版本结束时汇总断言，得到完整的「版本 × 图元」兼容矩阵。
 * 依赖外部文件或命令行交互的图元（Shape/Helix/PViewport）用 stepOptional 记录不计失败。
 * </p>
 */
class MultiVersionEntityCoverageTest {

    @TestFactory
    List<DynamicTest> entityCoveragePerVersion() {
        return AcadTestHarness.selectedVersions().stream()
                .map(v -> DynamicTest.dynamicTest(
                        "图元覆盖 " + v.getRelease() + " (" + v.getProgId() + ")",
                        () -> AcadTestHarness.withNewCad(v, EntityCoverage::run)))
                .toList();
    }

    /** 图元创建清单：每版本执行一遍 */
    static final class EntityCoverage {

        static void run(CadSession s) {
            ModelSpace ms = s.modelSpace();

            // ---------- 基础曲线 ----------
            s.step("Line", () -> {
                double x = s.nextX();
                verify(ms.addLine(Point.of(x, 0), Point.of(x + 30, 0)), "AcDbLine");
            });
            s.step("Circle", () -> verify(ms.addCircle(Point.of(s.nextX(), 0), 15), "AcDbCircle"));
            s.step("Arc", () -> verify(ms.addArc(Point.of(s.nextX(), 0), 10, 0, Math.PI / 2), "AcDbArc"));
            s.step("Ellipse", () -> verify(ms.addEllipse(Point.of(s.nextX(), 0), Point.of(20, 0, 0), 0.5), "AcDbEllipse"));
            s.step("Point", () -> verify(ms.addPoint(Point.of(s.nextX(), 0)), "AcDbPoint"));
            s.step("Ray", () -> {
                double x = s.nextX();
                verify(ms.addRay(Point.of(x, 0), Point.of(x + 10, 10)), "AcDbRay");
            });
            s.step("XLine", () -> {
                double x = s.nextX();
                verify(ms.addXLine(Point.of(x, 0), Point.of(x + 10, 10)), "AcDbXline");
            });
            s.step("LWPolyline", () -> {
                double x = s.nextX();
                verify(ms.addPolyline(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 30, 20), Point.of(x, 20)),
                        "AcDbPolyline");
            });
            s.step("Polyline3D", () -> {
                double x = s.nextX();
                verify(ms.addPolyline3D(Point.of(x, 0, 0), Point.of(x + 30, 0, 10), Point.of(x + 30, 20, 10)),
                        "AcDb3dPolyline");
            });
            s.step("Spline", () -> {
                double x = s.nextX();
                verify(ms.addSpline(Point.of(x, 0), Point.of(x + 10, 20), Point.of(x + 30, 0)), "AcDbSpline");
            });
            s.step("Trace", () -> {
                double x = s.nextX();
                verify(ms.addTrace(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 30, 10), Point.of(x, 10)),
                        "AcDbTrace");
            });
            s.step("Solid2D", () -> {
                double x = s.nextX();
                verify(ms.addSolid2D(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 30, 10), Point.of(x, 10)),
                        "AcDbSolid");
            });
            s.step("Face3D", () -> {
                double x = s.nextX();
                verify(ms.addFace3D(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 30, 10), Point.of(x, 10)),
                        "AcDbFace");
            });
            s.step("MLine", () -> {
                double x = s.nextX();
                verify(ms.addMLine(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 30, 20)), "AcDbMline");
            });

            // ---------- 网格 ----------
            s.step("3DMesh", () -> add3DMeshWithFallback(s));
            s.step("PolyfaceMesh", () -> {
                double x = s.nextX();
                // 库方法已按 5 版本实测结果固化为 VT_I2 平铺面索引数组
                verify(ms.addPolyfaceMesh(
                        List.of(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 30, 20), Point.of(x, 20)),
                        List.of(new int[]{1, 2, 3, 4})), "AcDbPolyFaceMesh");
            });

            // ---------- 文字与标注 ----------
            s.step("Text", () -> verify(ms.addText("ACAD-ACTIVEX", Point.of(s.nextX(), 0), 5), "AcDbText"));
            s.step("MText", () -> verify(ms.addMText(Point.of(s.nextX(), 0), 60, "ACAD ACTIVEX MTEXT"), "AcDbMText"));
            s.step("Tolerance", () -> verify(ms.addTolerance(Point.of(s.nextX(), 0), Point.of(1, 0, 0), "%%v"), "AcDbFcf"));
            s.step("DimAligned", () -> {
                double x = s.nextX();
                verify(ms.addDimAligned(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 15, 15)),
                        "AcDbAlignedDimension");
            });
            s.step("DimRotated", () -> {
                double x = s.nextX();
                verify(ms.addDimRotated(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 15, 15), 0),
                        "AcDbRotatedDimension");
            });
            s.step("DimRadial", () -> {
                double x = s.nextX();
                verify(ms.addDimRadial(Point.of(x, 0), Point.of(x + 15, 0), 10), "AcDbRadialDimension");
            });
            s.step("DimDiametric", () -> {
                double x = s.nextX();
                verify(ms.addDimDiametric(Point.of(x, 0), Point.of(x + 30, 0), 10), "AcDbDiametricDimension");
            });
            s.step("DimAngular", () -> {
                double x = s.nextX();
                verify(ms.addDimAngular(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x, 30), Point.of(x + 15, 15)),
                        "AcDb2LineAngularDimension");
            });
            s.step("Dim3PointAngular", () -> {
                double x = s.nextX();
                verify(ms.addDim3PointAngular(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x, 30), Point.of(x + 15, 15)),
                        "AcDb3PointAngularDimension");
            });
            s.step("DimOrdinate", () -> {
                double x = s.nextX();
                verify(ms.addDimOrdinate(Point.of(x, 0), Point.of(x + 15, 15), true), "AcDbOrdinateDimension");
            });
            s.step("DimArcLength", () -> {
                double x = s.nextX();
                verify(ms.addDimArcLength(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 15, 15), Point.of(x + 15, 7)),
                        "AcDbArcDimension");
            });
            s.step("DimRadialLarge", () -> {
                double x = s.nextX();
                verify(ms.addDimRadialLarge(Point.of(x, 0), Point.of(x + 30, 0), Point.of(x + 60, 0), Point.of(x + 45, 5), 0.3),
                        "AcDbRadialDimensionLarge");
            });
            s.step("Leader", () -> {
                double x = s.nextX();
                verify(ms.addLeader(new Point[]{Point.of(x, 0), Point.of(x + 10, 10), Point.of(x + 20, 10)}, null, 0),
                        "AcDbLeader");
            });
            s.step("MLeader", () -> {
                double x = s.nextX();
                verify(ms.addMLeader(new Point[]{Point.of(x, 0), Point.of(x + 10, 10), Point.of(x + 20, 10)}),
                        "AcDbMLeader");
            });
            s.step("Table", () -> verify(ms.addTable(Point.of(s.nextX(), 0), 3, 3, 8, 30), "AcDbTable"));
            s.step("Section", () -> {
                double x = s.nextX();
                verify(ms.addSection(Point.of(x, 0), Point.of(x + 30, 0), Point.of(0, 0, 1)), "AcDbSection");
            });

            // ---------- 填充 ----------
            s.step("Hatch", () -> {
                double x = s.nextX();
                Circle loop = ms.addCircle(Point.of(x, 0), 15);
                Hatch h = ms.addHatch(0, "ANSI31", true);
                h.appendOuterLoop(loop);
                h.evaluate();
                verify(h, "AcDbHatch");
            });
            s.step("HatchGradient", () -> {
                double x = s.nextX();
                Circle loop = ms.addCircle(Point.of(x, 0), 15);
                Hatch h = ms.addHatch(0, "LINEAR", true, 1);
                h.appendOuterLoop(loop);
                h.evaluate();
                verify(h, "AcDbHatch");
            });

            // ---------- 面域与三维实体 ----------
            s.step("Region", () -> {
                double x = s.nextX();
                Circle loop = ms.addCircle(Point.of(x, 0), 15);
                List<Region> regions = ms.addRegion(loop);
                assertEquals(1, regions.size(), "AddRegion 应返回 1 个面域");
                verify(regions.get(0), "AcDbRegion");
            });
            s.step("Box", () -> verify(ms.addBox(Point.of(s.nextX(), 0), 20, 20, 20), "AcDb3dSolid"));
            s.step("Cone", () -> verify(ms.addCone(Point.of(s.nextX(), 0), 10, 20), "AcDb3dSolid"));
            s.step("Cylinder", () -> verify(ms.addCylinder(Point.of(s.nextX(), 0), 10, 20), "AcDb3dSolid"));
            s.step("Sphere", () -> verify(ms.addSphere(Point.of(s.nextX(), 0), 15), "AcDb3dSolid"));
            s.step("Torus", () -> verify(ms.addTorus(Point.of(s.nextX(), 0), 20, 5), "AcDb3dSolid"));
            s.step("Wedge", () -> verify(ms.addWedge(Point.of(s.nextX(), 0), 20, 20, 20), "AcDb3dSolid"));
            s.step("EllipticalCone", () -> verify(ms.addEllipticalCone(Point.of(s.nextX(), 0), 20, 10, 25), "AcDb3dSolid"));
            s.step("EllipticalCylinder", () -> verify(ms.addEllipticalCylinder(Point.of(s.nextX(), 0), 20, 10, 25), "AcDb3dSolid"));
            // 已知限制：AddExtrudedSolid 的 Profile 为 byref Dispatch，AutoCAD 的 IDispatch
            // 后期绑定实现在 jacob/comtypes/VBScript 下均报"类型不匹配"（仅 VBA 早期绑定可用）。
            // 3D 实体创建已由 Box/Cone/Cylinder/Sphere/Torus/Wedge/EllipticalCone/Cylinder 覆盖。
            s.stepOptional("ExtrudedSolid", () -> {
                double x = s.nextX();
                Circle profile = ms.addCircle(Point.of(x, 0), 10);
                // Profile 为 byref Dispatch：jacob 装箱形态不确定，逐一尝试
                Throwable first = null;
                // 形态1：Dispatch 直接作为参数（jacob 自动装箱为 VT_DISPATCH）
                try {
                    Variant v = Disp.call(ModelSpace.class, ms.getDispatch(), "AddExtrudedSolid",
                            profile.getDispatch(), new Variant(30.0), new Variant(0.0));
                    verify(new Solid3D(v.toDispatch()), "AcDb3dSolid");
                    return;
                } catch (Throwable t) {
                    first = t;
                }
                // 形态2：VT_BYREF|VT_DISPATCH
                try {
                    Variant pv = new Variant();
                    pv.putDispatchRef(profile.getDispatch());
                    Variant v = Disp.call(ModelSpace.class, ms.getDispatch(), "AddExtrudedSolid",
                            pv, new Variant(30.0), new Variant(0.0));
                    verify(new Solid3D(v.toDispatch()), "AcDb3dSolid");
                    return;
                } catch (Throwable t) {
                    if (first == null) {
                        first = t;
                    }
                }
                // 形态3：VT_BYREF|VT_VARIANT 指向 VT_DISPATCH Variant
                try {
                    Variant holder = new Variant(profile.getDispatch());
                    Variant pv = new Variant();
                    pv.putVariantRef(holder);
                    Variant v = Disp.call(ModelSpace.class, ms.getDispatch(), "AddExtrudedSolid",
                            pv, new Variant(30.0), new Variant(0.0));
                    verify(new Solid3D(v.toDispatch()), "AcDb3dSolid");
                    return;
                } catch (Throwable t) {
                    if (first == null) {
                        first = t;
                    }
                }
                throw new RuntimeException(first);
            });

            // ---------- 块定义 / 块参照 / 属性 ----------
            s.step("Block+InsertBlock+Attribute", () -> {
                String blockName = "ACAD_AX_TEST_BLOCK";
                Block block = s.document().getBlocks().add(Point.ORIGIN, blockName);
                // 生成的 Block.addLine/addAttribute 返回 int（对 Dispatch 结果调 getInt 会炸），
                // 直接走 Disp 创建，不取返回值
                Disp.call(Block.class, block.getDispatch(), "AddLine",
                        Point.of(0, 0).toVariant(), Point.of(20, 0).toVariant());
                Disp.call(Block.class, block.getDispatch(), "AddAttribute",
                        new Variant(2.5), new Variant(0), new Variant("PROMPT"),
                        Point.of(0, 2, 0).toVariant(), new Variant("TAG1"), new Variant("VALUE1"));
                BlockReference ref = ms.insertBlock(Point.of(s.nextX(), 0), blockName, 1, 1, 1, 0);
                verify(ref, "AcDbBlockReference");
                var attrs = EntityUtil.parseArray(ref.getAttributes());
                assertFalse(attrs.isEmpty(), "块参照应带 1 个属性引用");
                assertEquals("AcDbAttribute", attrs.get(0).getObjectName());
            });
            s.step("MInsertBlock", () -> verify(
                    ms.addMInsertBlock(Point.of(s.nextX(), 0), "ACAD_AX_TEST_BLOCK", 2, 3, 30, 30), "AcDbMInsertBlock"));

            // ---------- 通用属性读写 ----------
            s.step("CommonProperties", () -> {
                double x = s.nextX();
                Line line = ms.addLine(Point.of(x, 0), Point.of(x + 30, 0));
                line.setColor(1);
                line.setLayer("ACAD_AX_TEST_LAYER");
                line.setLinetypeScale(2.0);
                assertEquals(1, line.getColor());
                assertEquals("ACAD_AX_TEST_LAYER", line.getLayer());
                assertEquals(2.0, line.getLinetypeScale(), 1e-9);
                // 坐标回读
                assertEquals(30.0, line.getEndPoint().getX() - line.getStartPoint().getX(), 1e-6);
            });

            // ---------- 实体几何操作（AcadEntity 基类方法跨版本验证） ----------
            s.step("EntityOps", () -> {
                double x = s.nextX();
                Line line = ms.addLine(Point.of(x, 0), Point.of(x + 30, 0));
                var copy = line.copy();
                assertEquals("AcDbLine", copy.getObjectName());
                copy.move(Point.of(0, 0), Point.of(0, 100));
                copy.rotate(Point.of(0, 100), Math.PI / 4);
                var bbox = copy.getBoundingBox();
                assertNotNull(bbox[0]);
                assertNotNull(bbox[1]);
                assertTrue(bbox[1].getX() >= bbox[0].getX(), "包围盒 X 范围异常");
                copy.arrayRectangular(2, 2, 1, 30, 30, 0);
                line.mirror(Point.of(x, 0), Point.of(x, 10));
                line.highlight(true);
                line.update();
                line.erase();
            });

            // ---------- 系统变量读写 ----------
            s.step("SysVariables", () -> {
                var doc = s.document();
                doc.setVariable("LTSCALE", 2.5);
                assertEquals(2.5, doc.getVariable("LTSCALE").getDouble(), 1e-9);
                doc.setVariable("LTSCALE", 1.0);
            });

            // ---------- 命令行 / 依赖外部条件的图元（失败不判负） ----------
            s.stepOptional("Shape", () -> {
                // AddShape 要求形名已通过 LoadShapeFile 加载（ltypeshp.shx 随 AutoCAD 附带，
                // 在 CAD 支持路径下），从 Preferences.Files.SupportPath 定位后传绝对路径
                String shx = findSupportFile(s, "ltypeshp.shx");
                if (shx == null) {
                    fail("CAD 支持路径中未找到 ltypeshp.shx");
                    return;
                }
                try {
                    Disp.call(Document.class, s.document().getDispatch(), "LoadShapeFile", shx);
                } catch (Throwable alreadyLoaded) {
                    // 已加载会报错，忽略
                }
                verify(ms.addShape("TRACK1", Point.of(s.nextX(), 0), 1.0, 0.0), "AcDbShape");
            });
            s.stepOptional("Helix(command)", () -> {
                double x = s.nextX();
                int before = ms.count();
                s.document().sendCommand("_.HELIX " + fmt(x) + ",0,0 10 5 20 ");
                int after = ms.count();
                assertTrue(after > before, "HELIX 命令未创建实体");
                assertEquals("AcDbHelix", ms.item(after - 1).getObjectName());
            });
            s.stepOptional("PViewport", () -> addPViewportInPaperSpace(s));

            // ---------- 保存 / 重开（必须放最后：会关闭当前文档） ----------
            s.step("SaveAs+Reopen", () -> saveAndReopen(s));

            s.assertNoRequiredFailures();
        }

        // ---------- helpers ----------

        private static String fmt(double d) {
            return String.valueOf(Math.round(d * 100) / 100.0);
        }

        /** 在 CAD 支持路径（Preferences.Files.SupportPath）中查找指定文件 */
        private static String findSupportFile(CadSession s, String fileName) {
            try {
                var prefs = s.application().getAcadApp().getProperty("Preferences").getDispatch();
                var files = io.github.yahocen.acad.common.Disp.get(
                        io.github.yahocen.acad.document.manager.Preferences.class, prefs, "Files").getDispatch();
                String supportPath = io.github.yahocen.acad.common.Disp.get(
                        io.github.yahocen.acad.document.manager.PreferencesFiles.class, files, "SupportPath").getString();
                for (String dir : supportPath.split(";")) {
                    Path cand = Path.of(dir.trim(), fileName);
                    if (Files.isRegularFile(cand)) {
                        return cand.toString();
                    }
                }
            } catch (Throwable t) {
                System.out.println("读取 SupportPath 失败：" + t);
            }
            return null;
        }

        private static AcadEntity verify(AcadEntity entity, String expectedObjectName) {
            assertEquals(expectedObjectName, entity.getObjectName(),
                    () -> "ObjectName 与预期不符，包装类=" + entity.getClass().getSimpleName());
            return entity;
        }

        /**
         * Add3DMesh 的点矩阵入参在不同版本/文档描述上有两种形态：
         * 平铺 double 数组（同 AddTrace）或「每点一个 Variant」数组，先试前者再试后者。
         */
        private static void add3DMeshWithFallback(CadSession s) {
            ModelSpace ms = s.modelSpace();
            double x = s.nextX();
            double[] flat = {x, 0, 0, x + 30, 0, 0, x + 30, 20, 0, x, 20, 0};
            try {
                SafeArray sa = new SafeArray(Variant.VariantDouble, flat.length);
                for (int i = 0; i < flat.length; i++) {
                    sa.setDouble(i, flat[i]);
                }
                verify(new PolygonMesh(Disp.call(ModelSpace.class, ms.getDispatch(),
                        "Add3DMesh", new Variant(2), new Variant(2), new Variant(sa)).toDispatch()),
                        "AcDbPolygonMesh");
            } catch (Throwable flatFailed) {
                // 回退：每点一个 Variant（数组套数组）
                SafeArray sa = new SafeArray(Variant.VariantVariant, 4);
                for (int i = 0; i < 4; i++) {
                    sa.setVariant(i, Point.of(flat[i * 3], flat[i * 3 + 1], flat[i * 3 + 2]).toVariant());
                }
                verify(new PolygonMesh(Disp.call(ModelSpace.class, ms.getDispatch(),
                        "Add3DMesh", new Variant(2), new Variant(2), new Variant(sa)).toDispatch()),
                        "AcDbPolygonMesh");
            }
        }

        private static void addPViewportInPaperSpace(CadSession s) {
            Layouts layouts = new Layouts(Disp.get(Document.class, s.document().getDispatch(), "Layouts").getDispatch());
            for (int i = 0; i < layouts.count(); i++) {
                Layout layout = layouts.item(i);
                if (layout.getModelType().getBoolean()) {
                    continue; // 跳过模型空间布局
                }
                Block paper = layout.getBlock();
                Variant v = Disp.call(Block.class, paper.getDispatch(), "AddPViewport",
                        Point.of(100, 80).toVariant(), new Variant(60.0), new Variant(40.0));
                verify(new PViewport(v.toDispatch()), "AcDbViewport");
                return;
            }
            fail("找不到图纸空间布局");
        }

        private static void saveAndReopen(CadSession s) {
            Path dir = Path.of("target", "acad-test");
            Path dwg;
            try {
                Files.createDirectories(dir);
                dwg = dir.resolve(s.version().name() + ".dwg");
            } catch (IOException e) {
                throw new IllegalStateException("创建测试输出目录失败", e);
            }
            Document doc = s.document();
            int expected = s.modelSpace().count();
            assertTrue(expected > 30, "模型空间实体数异常：" + expected);

            doc.saveAs(dwg.toAbsolutePath().toString());
            doc.close(false, null);

            Document reopened = s.application().getDocuments().open(dwg.toAbsolutePath().toString());
            try {
                ModelSpace reMs = reopened.getModelSpace();
                assertEquals(expected, reMs.count(), "重开后实体数不一致");
                // 逐个取实体，走 EntityUtil.parse 的 ObjectName 分派
                for (int i = 0; i < reMs.count(); i++) {
                    AcadEntity e = reMs.item(i);
                    assertNotNull(e.getObjectName());
                }
            } finally {
                reopened.close(false, null);
            }
        }
    }
}

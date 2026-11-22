package io.github.yahocen.acad.document.base;

import io.github.yahocen.jacob.com.SafeArray;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * AutoCAD点坐标类
 * <p>
 * 值类（record）：坐标不可变，所有改动坐标的操作（{@link #appendX}、{@link #setX} 等）
 * 都返回新的 Point，不修改原对象。脚本里要累积推进某个点时，必须把返回值赋回去：
 * <pre>{@code
 * basePoint = basePoint.appendX(offset)
 * }</pre>
 * 不能写成 {@code basePoint.appendX(offset)} 当语句用——那样 basePoint 不会变。
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Point AutoCAD点坐标",
    description = "表示AutoCAD中的三维点坐标"
)
public record Point(double x, double y, double z) {

    public final static Point ORIGIN = new Point(0, 0, 0);

    /**
     * 全局坐标偏移基点。
     * <p>
     * 注意：这是一个全局静态状态。在多文档场景下需谨慎使用，
     * 建议在使用完毕后调用 {@link #resetBasePoint()} 重置。
     * </p>
     */
    private static Point basePoint = new Point(0, 0, 0);

    public static Point getBasePoint() {
        return basePoint;
    }

    public static void setBasePoint(Point basePoint) {
        Point.basePoint = basePoint;
    }

    /** X 坐标（兼容旧 getter 写法，等价于 {@link #x()}） */
    public double getX() {
        return x;
    }

    /** Y 坐标（兼容旧 getter 写法，等价于 {@link #y()}） */
    public double getY() {
        return y;
    }

    /** Z 坐标（兼容旧 getter 写法，等价于 {@link #z()}） */
    public double getZ() {
        return z;
    }

    @Operation(summary = "创建点坐标", description = "创建点坐标（三维）")
    public static Point of(@Parameter(description = "X 坐标") double x, @Parameter(description = "Y 坐标") double y, @Parameter(description = "Z 坐标") double z) {
        return new Point(x + basePoint.x, y + basePoint.y, z + basePoint.z);
    }

    @Operation(summary = "创建点坐标", description = "创建点坐标（二维，Z=0）")
    public static Point of(@Parameter(description = "X 坐标") double x, @Parameter(description = "Y 坐标") double y) {
        return of(x, y, 0);
    }

    @Operation(summary = "从 Variant 创建点坐标", description = "从 Variant 创建点坐标")
    public static Point of(@Parameter(description = "Variant 对象") Variant variant) {
        return of(variant.toSafeArray());
    }

    @Operation(summary = "从 SafeArray 创建点坐标", description = "从 SafeArray 创建点坐标，支持二维点（如 LWPolyline 顶点、Hatch.Origin），Z 取 0")
    public static Point of(@Parameter(description = "SafeArray 对象") SafeArray safeArray) {
        int lb = safeArray.getLBound();
        int n = safeArray.getUBound() - lb + 1;
        var x = safeArray.getDouble(lb);
        var y = safeArray.getDouble(lb + 1);
        var z = n > 2 ? safeArray.getDouble(lb + 2) : 0d;
        safeArray.safeRelease();
        return new Point(x, y, z);
    }

    @Operation(summary = "追加 X 坐标（返回新点，原点不变）", description = "返回 X 坐标增加 dx 后的新点，原点不变。要推进原变量必须 p = p.appendX(dx)")
    public Point appendX(@Parameter(description = "X 坐标增量") double dx) {
        return new Point(x + dx, y, z);
    }

    @Operation(summary = "追加 Y 坐标（返回新点，原点不变）", description = "返回 Y 坐标增加 dy 后的新点，原点不变。要推进原变量必须 p = p.appendY(dy)")
    public Point appendY(@Parameter(description = "Y 坐标增量") double dy) {
        return new Point(x, y + dy, z);
    }

    @Operation(summary = "追加 Z 坐标（返回新点，原点不变）", description = "返回 Z 坐标增加 dz 后的新点，原点不变。要推进原变量必须 p = p.appendZ(dz)")
    public Point appendZ(@Parameter(description = "Z 坐标增量") double dz) {
        return new Point(x, y, z + dz);
    }

    @Operation(summary = "设置 X 坐标（返回新点，原点不变）", description = "返回 X 坐标改为 x 后的新点，原点不变")
    public Point setX(@Parameter(description = "X 坐标") double x) {
        return new Point(x, y, z);
    }

    @Operation(summary = "设置 Y 坐标（返回新点，原点不变）", description = "返回 Y 坐标改为 y 后的新点，原点不变")
    public Point setY(@Parameter(description = "Y 坐标") double y) {
        return new Point(x, y, z);
    }

    @Operation(summary = "设置 Z 坐标（返回新点，原点不变）", description = "返回 Z 坐标改为 z 后的新点，原点不变")
    public Point setZ(@Parameter(description = "Z 坐标") double z) {
        return new Point(x, y, z);
    }

    @Operation(summary = "转换为 Variant", description = "将点坐标转换为 Variant 对象")
    public Variant toVariant() {
        SafeArray sa = new SafeArray(Variant.VariantDouble, 3);
        sa.setDouble(0, x);
        sa.setDouble(1, y);
        sa.setDouble(2, z);
        return new Variant(sa);
    }

    /**
     * 把坐标数组（如多段线的 Coordinates）按维数切分成点列表。
     *
     * @param coordinates double 数组 Variant
     * @param dimension   每个点的坐标个数：LWPolyline 为 2，Polyline / Polyline3D 为 3
     * @return 点列表；二维坐标的 Z 为 0
     */
    @Operation(summary = "坐标数组转点列表", description = "把 Coordinates 这类 double 数组按维数（2 或 3）切分成点列表，二维坐标的 Z 为 0")
    public static List<Point> listOf(@Parameter(description = "坐标数组 Variant") Variant coordinates,
                                     @Parameter(description = "每个点的坐标个数，2 或 3") int dimension) {
        SafeArray sa = coordinates.toSafeArray();
        try {
            double[] a = sa.toDoubleArray();
            List<Point> points = new ArrayList<>(a.length / dimension);
            for (int i = 0; i + dimension <= a.length; i += dimension) {
                points.add(new Point(a[i], a[i + 1], dimension > 2 ? a[i + 2] : 0d));
            }
            return points;
        } finally {
            sa.safeRelease();
        }
    }

    @Operation(summary = "转换为二维 Variant", description = "将点坐标转换为只含 X、Y 的 Variant（LWPolyline 顶点等二维坐标属性使用）")
    public Variant toVariant2D() {
        SafeArray sa = new SafeArray(Variant.VariantDouble, 2);
        sa.setDouble(0, x);
        sa.setDouble(1, y);
        return new Variant(sa);
    }

    @Operation(summary = "重置基点", description = "重置基点为原点")
    public static void resetBasePoint() {
        basePoint = new Point(0, 0, 0);
    }

    @Operation(summary = "复制点坐标（值类下点不可变，copy 返回等价新实例，仅为兼容旧写法）", description = "值类下点本身不可变，copy 仅为兼容旧写法，返回等价的新实例")
    public Point copy() {
        return new Point(x, y, z);
    }

}

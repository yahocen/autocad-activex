// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.github.yahocen.acad.constant.LineDirection;
import io.github.yahocen.acad.document.base.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 点工具类
 * @author Yahocen
 */
public class PointUtil {

    /**
     * 将线段分割为多个段，每个段包含两个点，第一个点是起始点，第二个点是结束点
     * @param start 起始点
     * @param end 结束点
     * @param segments 分割段数
     * @return 包含分割段的开始点和结束点的列表
     */
    @Operation(summary = "将线段分割为多个段", description = "将线段分割为多个段，每个段包含两个点，第一个点是起始点，第二个点是结束点")
    public static List<Point[]> divideLineIntoSegments(@Parameter(description = "起始点") Point start, @Parameter(description = "结束点") Point end, @Parameter(description = "分割段数") int segments) {
        // 计算两点之间的向量
        double deltaX = end.getX() - start.getX();
        double deltaY = end.getY() - start.getY();
        double deltaZ = end.getZ() - start.getZ();

        // 创建一个列表来保存所有分割段的开始点，添加第一段的开始点
        List<Point[]> segmentsPoints = new ArrayList<>();
        segmentsPoints.add(new Point[]{start.copy(), null});

        // 计算每个分割段的结束点，并将其作为下一段的开始点
        for (int i = 1; i < segments; i++) {
            double segmentRatio = i / (double) segments;
            double x = start.getX() + deltaX * segmentRatio;
            double y = start.getY() + deltaY * segmentRatio;
            double z = start.getZ() + deltaZ * segmentRatio;
            segmentsPoints.add(new Point[]{new Point(x, y, z), null});
        }

        // 添加最后一段的结束点
        segmentsPoints.add(new Point[]{end.copy(), null});

        // 调整格式以便更容易理解
        List<Point[]> result = new ArrayList<>();
        for (int i = 0; i < segmentsPoints.size() - 1; i++) {
            if (i == 0) {
                // 第一段只有开始点
                result.add(new Point[]{segmentsPoints.get(i)[0], segmentsPoints.get(i + 1)[0]});
            } else if (i == segmentsPoints.size() - 1) {
                // 最后一段只有结束点
                result.add(new Point[]{segmentsPoints.get(i - 1)[0], segmentsPoints.get(i)[0]});
            } else {
                // 中间段既有开始点也有结束点
                result.add(new Point[]{segmentsPoints.get(i)[0], segmentsPoints.get(i + 1)[0]});
            }
        }

        return result;
    }

    /**
     * 获取两个点的中间点
     * @param start 起始点
     * @param end 结束点
     * @return 包含中间点的列表
     */
    @Operation(summary = "获取两个点的中间点", description = "获取两个点的中间点")
    public static Point middlePoint(@Parameter(description = "起始点") Point start, @Parameter(description = "结束点") Point end) {
        // 计算中间点的坐标
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        double midZ = (start.getZ() + end.getZ()) / 2;
        // 创建中间点
        return new Point(midX, midY, midZ);
    }

    /**
     * 计算两条三维直线的交点
     * @param line1 第一条直线，由两个点定义
     * @param line2 第二条直线，由两个点定义
     * @return 交点，如果不存在则返回null
     */
    @Operation(summary = "计算两条三维直线的交点", description = "计算两条三维直线的交点")
    public static Point computeIntersection(@Parameter(description = "第一条直线，由两个点定义") Point[] line1, @Parameter(description = "第二条直线，由两个点定义") Point[] line2) {
        Point p1 = line1[0], p2 = line1[1];
        Point p3 = line2[0], p4 = line2[1];
        // 计算两条直线的方向向量
        Point d1 = new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ());
        Point d2 = new Point(p4.getX() - p3.getX(), p4.getY() - p3.getY(), p4.getZ() - p3.getZ());
        // 计算连接两条直线上任意两点的向量
        Point p1p3 = new Point(p3.getX() - p1.getX(), p3.getY() - p1.getY(), p3.getZ() - p1.getZ());
        // 计算叉积
        Point crossD1D2 = crossProduct(d1, d2);
        double crossNorm = crossD1D2.getX() * crossD1D2.getX() +
                crossD1D2.getY() * crossD1D2.getY() +
                crossD1D2.getZ() * crossD1D2.getZ();
        // 如果方向向量平行（叉积为0），则检查是否共线
        // 使用小的阈值处理浮点误差
        if (crossNorm < 1e-12) {
            // 检查是否共线
            Point crossP1P3D1 = crossProduct(p1p3, d1);
            double crossP1P3Norm = crossP1P3D1.getX() * crossP1P3D1.getX() +
                    crossP1P3D1.getY() * crossP1P3D1.getY() +
                    crossP1P3D1.getZ() * crossP1P3D1.getZ();

            if (crossP1P3Norm < 1e-12) {
                // 共线，返回任意一个点（这里返回第一条直线的第一个点）
                return new Point(p1.getX(), p1.getY(), p1.getZ());
            } else {
                // 平行但不共线，无交点
                return null;
            }
        }
        // 计算两条直线之间的最短距离
        double distance = Math.abs(dotProduct(p1p3, crossD1D2)) / Math.sqrt(crossNorm);
        // 如果距离不为0，说明两条直线不相交
        // 使用小的阈值处理浮点误差
        if (distance > 1e-8) {
            return null;
        }
        // 计算交点
        // 使用参数方程法求解
        // 直线1: p1 + t * d1
        // 直线2: p3 + s * d2
        // 解方程 p1 + t * d1 = p3 + s * d2
        // 构建方程组并解t和s
        // 选择两个方程来解（避免分母为0）
        double t;
        if (Math.abs(d1.getX() * d2.getY() - d1.getY() * d2.getX()) > 1e-8) {
            // 使用x和y方程
            t = (d2.getY() * (p3.getX() - p1.getX()) - d2.getX() * (p3.getY() - p1.getY())) /
                    (d1.getX() * d2.getY() - d1.getY() * d2.getX());
        } else if (Math.abs(d1.getY() * d2.getZ() - d1.getZ() * d2.getY()) > 1e-8) {
            // 使用y和z方程
            t = (d2.getZ() * (p3.getY() - p1.getY()) - d2.getY() * (p3.getZ() - p1.getZ())) /
                    (d1.getY() * d2.getZ() - d1.getZ() * d2.getY());
        } else {
            // 使用x和z方程
            t = (d2.getZ() * (p3.getX() - p1.getX()) - d2.getX() * (p3.getZ() - p1.getZ())) /
                    (d1.getX() * d2.getZ() - d1.getZ() * d2.getX());
        }
        // 计算交点坐标
        double x = p1.getX() + t * d1.getX();
        double y = p1.getY() + t * d1.getY();
        double z = p1.getZ() + t * d1.getZ();
        return new Point(x, y, z);
    }

    /**
     * 计算两个向量的叉积
     */
    private static Point crossProduct(Point a, Point b) {
        double x = a.getY() * b.getZ() - a.getZ() * b.getY();
        double y = a.getZ() * b.getX() - a.getX() * b.getZ();
        double z = a.getX() * b.getY() - a.getY() * b.getX();
        return new Point(x, y, z);
    }

    /**
     * 计算两个向量的点积
     */
    private static double dotProduct(Point a, Point b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }

    /**
     * 计算所有三维直线的交点
     * @param lines 直线列表
     * @return 交点列表
     */
    @Operation(summary = "计算所有三维直线的交点", description = "计算所有三维直线的交点")
    public static List<Point> computeAllIntersections(@Parameter(description = "直线列表") List<Point[]> lines) {
        List<Point> intersections = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                Point intersection = computeIntersection(lines.get(i), lines.get(j));
                if (intersection != null) {
                    intersections.add(intersection);
                }
            }
        }
        return intersections;
    }

    /**
     * 找到距离指定点最近的点
     * @param referencePoint 参考点
     * @param points 点集合
     * @return 距离参考点最近的点，如果多个点距离相同，则随机返回其中一个
     */
    @Operation(summary = "找到距离指定点最近的点", description = "找到距离指定点最近的点")
    public static Point findNearestPoint(@Parameter(description = "参考点") Point referencePoint, @Parameter(description = "点集合") List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }
        // 初始化最近点和最小距离
        Point nearestPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (Point point : points) {
            // 计算当前点与参考点的距离
            double distance = Math.sqrt(
                Math.pow(point.getX() - referencePoint.getX(), 2) +
                Math.pow(point.getY() - referencePoint.getY(), 2) +
                Math.pow(point.getZ() - referencePoint.getZ(), 2)
            );
            // 如果距离更小或相等（处理多个最近点的情况），更新最近点
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            } else if (distance == minDistance) {
                // 随机选择一个最近点
                if (Math.random() > 0.5) {
                    nearestPoint = point;
                }
            }
        }
        return nearestPoint;
    }

    /**
     * 找到距离指定点最远的点
     * @param referencePoint 参考点
     * @param points 点集合
     * @return 距离参考点最远的点，如果多个点距离相同，则随机返回其中一个
     */
    @Operation(summary = "找到距离指定点最远的点", description = "找到距离指定点最远的点")
    public static Point findFarthestPoint(@Parameter(description = "参考点") Point referencePoint, @Parameter(description = "点集合") List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }
        // 初始化最远点和最大距离
        Point farthestPoint = null;
        double maxDistance = 0;
        for (Point point : points) {
            // 计算当前点与参考点的距离
            double distance = Math.sqrt(
                Math.pow(point.getX() - referencePoint.getX(), 2) +
                Math.pow(point.getY() - referencePoint.getY(), 2) +
                Math.pow(point.getZ() - referencePoint.getZ(), 2)
            );
            // 如果距离更大或相等（处理多个最远点的情况），更新最远点
            if (distance > maxDistance) {
                maxDistance = distance;
                farthestPoint = point;
            } else if (distance == maxDistance) {
                // 随机选择一个最远点
                if (Math.random() > 0.5) {
                    farthestPoint = point;
                }
            }
        }
        return farthestPoint;
    }

    /**
     * 找到距离指定点最近的点，只考虑X轴
     * @param referencePoint 参考点
     * @param points 点集合
     * @return 距离参考点最近的点，如果多个点距离相同，则随机返回其中一个
     */
    @Operation(summary = "找到距离指定点最近的点（只考虑 X 轴）", description = "找到距离指定点最近的点，只考虑 X 轴")
    public static Point findNearestPointX(@Parameter(description = "参考点") Point referencePoint, @Parameter(description = "点集合") List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }

        // 初始化最近点和最小 X 轴距离
        Point nearestPoint = null;
        double minDistanceX = Double.MAX_VALUE;

        for (Point point : points) {
            // 计算 X 轴上的距离
            double distanceX = Math.abs(point.getX() - referencePoint.getX());

            // 如果距离更小或相等（处理多个最近点的情况），更新最近点
            if (distanceX < minDistanceX) {
                minDistanceX = distanceX;
                nearestPoint = point;
            } else if (distanceX == minDistanceX) {
                // 随机选择一个最近点
                if (Math.random() > 0.5) {
                    nearestPoint = point;
                }
            }
        }

        return nearestPoint;
    }

    /**
     * 找到距离指定点最远的点，只考虑X轴
     * @param referencePoint 参考点
     * @param points 点集合
     * @return 距离参考点最远的点，如果多个点距离相同，则随机返回其中一个
     */
    @Operation(summary = "找到距离指定点最远的点（只考虑 X 轴）", description = "找到距离指定点最远的点，只考虑 X 轴")
    public static Point findFarthestPointX(@Parameter(description = "参考点") Point referencePoint, @Parameter(description = "点集合") List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }

        // 初始化最远点和最大 X 轴距离
        Point farthestPoint = null;
        double maxDistanceX = Double.MIN_VALUE;

        for (Point point : points) {
            // 计算 X 轴上的距离差值（绝对值）
            double distanceX = Math.abs(point.getX() - referencePoint.getX());

            // 如果距离更大或相等（处理多个最远点的情况），更新最远点
            if (distanceX > maxDistanceX) {
                maxDistanceX = distanceX;
                farthestPoint = point;
            } else if (distanceX == maxDistanceX) {
                // 随机选择一个最远点
                if (Math.random() > 0.5) {
                    farthestPoint = point;
                }
            }
        }

        return farthestPoint;
    }

    /**
     * 找到距离指定点最近的点，只考虑Y轴
     * @param referencePoint 参考点
     * @param points 点集合
     * @return 距离参考点最近的点，如果多个点距离相同，则随机返回其中一个
     */
    @Operation(summary = "找到距离指定点最近的点（只考虑 Y 轴）", description = "找到距离指定点最近的点，只考虑 Y 轴")
    public static Point findNearestPointY(@Parameter(description = "参考点") Point referencePoint, @Parameter(description = "点集合") List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }

        // 初始化最近点和最小 Y 轴距离
        Point nearestPoint = null;
        double minDistanceY = Double.MAX_VALUE;

        for (Point point : points) {
            // 计算 Y 轴上的距离
            double distanceY = Math.abs(point.getY() - referencePoint.getY());

            // 如果距离更小或相等（处理多个最近点的情况），更新最近点
            if (distanceY < minDistanceY) {
                minDistanceY = distanceY;
                nearestPoint = point;
            } else if (distanceY == minDistanceY) {
                // 随机选择一个最近点
                if (Math.random() > 0.5) {
                    nearestPoint = point;
                }
            }
        }

        return nearestPoint;
    }

    /**
     * 找到距离指定点最远的点，只考虑Y轴
     * @param referencePoint 参考点
     * @param points 点集合
     * @return 距离参考点最远的点，如果多个点距离相同，则随机返回其中一个
     */
    @Operation(summary = "找到距离指定点最远的点（只考虑 Y 轴）", description = "找到距离指定点最远的点，只考虑 Y 轴")
    public static Point findFarthestPointY(@Parameter(description = "参考点") Point referencePoint, @Parameter(description = "点集合") List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }

        // 初始化最远点和最大 Y 轴距离
        Point farthestPoint = null;
        double maxDistanceY = Double.MIN_VALUE;

        for (Point point : points) {
            // 计算 Y 轴上的距离差值（绝对值）
            double distanceY = Math.abs(point.getY() - referencePoint.getY());

            // 如果距离更大或相等（处理多个最远点的情况），更新最远点
            if (distanceY > maxDistanceY) {
                maxDistanceY = distanceY;
                farthestPoint = point;
            } else if (distanceY == maxDistanceY) {
                // 随机选择一个最远点
                if (Math.random() > 0.5) {
                    farthestPoint = point;
                }
            }
        }

        return farthestPoint;
    }

    /**
     * 找到距离X坐标0点最近且Y轴坐标0点最远的一个点
     * @param points 点集合
     * @return 符合条件的点，如果多个点满足条件，则随机返回其中一个
     */
    @Operation(summary = "找到距离 X 坐标 0 点最近且 Y 轴坐标 0 点最远的点", description = "找到距离 X 坐标 0 点最近且 Y 轴坐标 0 点最远的一个点")
    public static Point findNearestXFarthestY(@Parameter(description = "点集合") List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }
        // 初始化最近X轴距离和最远Y轴距离
        double minDistanceX = Double.MAX_VALUE;
        double maxDistanceY = Double.MIN_VALUE;
        // 存储符合条件的点
        List<Point> candidates = new ArrayList<>();
        for (Point point : points) {
            // 计算X轴上的距离
            double distanceX = Math.abs(point.getX());
            // 如果当前点的X轴距离更近，更新候选列表
            if (distanceX < minDistanceX) {
                minDistanceX = distanceX;
                candidates.clear();
                candidates.add(point);
            } else if (distanceX == minDistanceX) {
                // 如果X轴距离相同，加入候选列表
                candidates.add(point);
            }
        }
        // 从候选列表中筛选出Y轴距离最远的点
        for (Point candidate : candidates) {
            double distanceY = Math.abs(candidate.getY());
            if (distanceY > maxDistanceY) {
                maxDistanceY = distanceY;
            }
        }
        // 如果有多个点满足条件，随机选择一个
        if (candidates.size() > 1) {
            int randomIndex = (int) (Math.random() * candidates.size());
            return candidates.get(randomIndex);
        }
        return candidates.get(0);
    }

    /**
     * 找到 Y 坐标最大的点中，X 坐标最小的那个点
     */
    @Operation(summary = "找到 Y 坐标最大的点中 X 坐标最小的点", description = "找到 Y 坐标最大的点中，X 坐标最小的那个点")
    public static Point findFarthestYNearestX(@Parameter(description = "点集合") List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }

        // 第一步：筛选出 Y 值最大的所有候选点
        double maxY = Double.MIN_VALUE;
        List<Point> candidatesWithMaxY = new ArrayList<>();

        for (Point point : points) {
            double y = point.getY();
            if (y > maxY) {
                maxY = y;
                candidatesWithMaxY.clear();
                candidatesWithMaxY.add(point);
            } else if (y == maxY) {
                candidatesWithMaxY.add(point);
            }
        }

        // 第二步：在 Y 最大的候选点中，找 X 最小的点
        Point nearestXPoint = null;
        double minX = Double.MAX_VALUE;

        for (Point candidate : candidatesWithMaxY) {
            double x = Math.abs(candidate.getX());  // 可选：考虑绝对值是否必要
            if (x < minX) {
                minX = x;
                nearestXPoint = candidate;
            }
        }

        return nearestXPoint;
    }

    /**
     * 找出 points2 中距离 points1 最近的一个点
     * @param points1 第一组坐标
     * @param points2 第二组坐标
     * @return points2 中距离 points1 最近的点
     */
    @Operation(summary = "找出两组点之间距离最近的点", description = "找出 points2 中距离 points1 最近的一个点")
    public static Point findNearestPointBetweenGroups(@Parameter(description = "第一组坐标") List<Point> points1, @Parameter(description = "第二组坐标") List<Point> points2) {
        if (points1 == null || points1.isEmpty() || points2 == null || points2.isEmpty()) {
            throw new IllegalArgumentException("点集合不能为空");
        }
        // 初始化最近点和最小距离
        Point nearestPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (Point point1 : points1) {
            for (Point point2 : points2) {
                // 计算当前点与参考点的距离
                double distance = Math.sqrt(
                    Math.pow(point2.getX() - point1.getX(), 2) +
                    Math.pow(point2.getY() - point1.getY(), 2) +
                    Math.pow(point2.getZ() - point1.getZ(), 2)
                );
                // 如果距离更小，更新最近点
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPoint = point2;
                }
            }
        }
        return nearestPoint;
    }

    /**
     * 确定三维直线的朝向
     * @param start 直线起点
     * @param end 直线终点
     * @return 直线的朝向
     */
    @Operation(summary = "确定三维直线的朝向", description = "确定三维直线的朝向")
    public static LineDirection determineLineDirection(@Parameter(description = "直线起点") Point start, @Parameter(description = "直线终点") Point end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }

        double deltaX = Math.abs(end.getX() - start.getX());
        double deltaY = Math.abs(end.getY() - start.getY());

        // 完全水平或垂直的情况
        if (deltaX == 0) {
            return LineDirection.VERTICAL;
        }
        if (deltaY == 0) {
            return LineDirection.HORIZONTAL;
        }

        // 使用斜率判断
        double ratio = deltaY / deltaX;
        if (ratio < 0.5) {
            return LineDirection.HORIZONTAL;
        }
        if (ratio > 2.0) {
            return LineDirection.VERTICAL;
        }
        return LineDirection.DIAGONAL;
    }

    /**
     * 计算给定所有点的凸包，返回最外层的点集合。
     * @param allPoints 多条多段线的坐标列表
     * @return 凸包中的最外层点列表
     */
    @Operation(summary = "计算给定所有点的凸包", description = "计算给定所有点的凸包，返回最外层的点集合")
    public static List<Point> computeConvexHull(@Parameter(description = "多条多段线的坐标列表") List<List<Point>> allPoints) {
        // 1. 合并所有点
        List<Point> mergedPoints = new ArrayList<>();
        for (List<Point> points : allPoints) {
            mergedPoints.addAll(points);
        }

        // 2. 使用凸包算法获取最外层点
        return convexHull(mergedPoints);
    }

    /**
     * 使用 Monotone Chain 算法计算凸包。
     * @param points 输入点集
     * @return 凸包上的点列表（按顺时针或逆时针顺序）
     */
    private static List<Point> convexHull(List<Point> points) {
        if (points.size() <= 1) {
            return points;
        }

        // 按照 x 坐标排序，若 x 相同则按 y 排序
        points.sort((a, b) -> a.getX() != b.getX() ? Double.compare(a.getX(), b.getX()) : Double.compare(a.getY(), b.getY()));

        Point[] hull = new Point[2 * points.size()];
        // hull 的当前大小
        int k = 0;

        // 构建下凸包
        for (Point p : points) {
            while (k >= 2 && cross(hull[k - 2], hull[k - 1], p) <= 0) {
                k--;
            }
            hull[k++] = p;
        }

        // 构建上凸包
        for (int i = points.size() - 2; i >= 0; i--) {
            Point p = points.get(i);
            while (k >= 2 && cross(hull[k - 2], hull[k - 1], p) <= 0) {
                k--;
            }
            hull[k++] = p;
        }

        // 返回凸包上的点（去除重复的最后一个点）
        return new ArrayList<>(Arrays.asList(hull).subList(0, k - 1));
    }

    /**
     * 计算向量叉积：(o -> a) × (o -> b)
     * @param o 起点
     * @param a 终点1
     * @param b 终点2
     * @return 叉积值
     */
    private static double cross(Point o, Point a, Point b) {
        return (a.getX() - o.getX()) * (b.getY() - o.getY()) - (a.getY() - o.getY()) * (b.getX() - o.getX());
    }

    /**
     * 获取多段线路径上所有毫米级坐标点
     * @param vertices 多段线顶点列表
     * @return 所有经过的坐标点（包含顶点和中间点）
     */
    @Operation(summary = "获取多段线路径上所有毫米级坐标点", description = "获取多段线路径上所有毫米级坐标点")
    public static List<Point> getAllPathPointsInMillimeter(@Parameter(description = "多段线顶点列表") List<Point> vertices) {
        List<Point> allPoints = new ArrayList<>();

        for (int i = 0; i < vertices.size() - 1; i++) {
            Point start = vertices.get(i);
            Point end = vertices.get(i + 1);

            // 计算线段长度
            double length = calculateDistance(start, end);

            // 根据长度计算需要划分的段数（每段 ≤ 1mm）
            int segments = (int) Math.ceil(length);

            // 分割线段并获取所有中间点
            List<Point[]> dividedSegments = PointUtil.divideLineIntoSegments(start, end, segments);

            // 添加起始点
            if (!allPoints.isEmpty()) {
                allPoints.add(dividedSegments.get(0)[0]); // 去重最后一个点
            } else {
                allPoints.add(start); // 添加第一个起点
            }

            // 添加中间点
            for (int j = 1; j < dividedSegments.size(); j++) {
                allPoints.add(dividedSegments.get(j)[0]);
            }
        }

        return allPoints;
    }

    /**
     * 计算两点之间的欧几里得距离
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 距离
     */
    private static double calculateDistance(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        double dz = p2.getZ() - p1.getZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

}
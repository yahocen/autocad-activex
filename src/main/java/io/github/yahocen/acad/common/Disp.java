// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.common;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 带 DISPID 缓存的 COM 调用门面。
 * <p>
 * Jacob 按名字调用（{@code Dispatch.call(d, "AddLine", ...)}）底层每次都要先
 * {@code GetIDsOfNames} 把名字解析成 DISPID，再 {@code Invoke}——两次跨进程往返。
 * 而名字到 DISPID 的映射是 COM 接口编译期写死的常量，跨进程/跨会话/跨图元稳定不变，
 * 完全可以解析一次后永久复用。
 * </p>
 * <p>
 * 本类按「封装类（≈一个 COM 接口）+ 名字」缓存 DISPID，命中后直接走 Jacob 的
 * dispid 重载（{@code Dispatch.call(d, dispid, ...)}），跳过 {@code GetIDsOfNames}，
 * 把每个操作的跨进程往返从 2 次降到 1 次。对外 API 与按名调用完全等价。
 * </p>
 * <p>
 * 缓存特性：
 * <ul>
 *   <li><b>按类分桶</b>：DISPID 是接口级的，不同接口同名方法编号不同，故以 {@code Class} 隔离，
 *       不能用全局名字表。调用方传入 {@code getClass()}，且仅用于对象自身的 {@code dispatch}，
 *       保证类与接口一一对应。</li>
 *   <li><b>有界</b>：key 是「类 × 方法/属性名」，总量被 API 表面封死（几百到几千条），
 *       与图元数量无关，无需淘汰。</li>
 *   <li><b>无需失效</b>：DISPID 同版本内恒定，进程重启后旧缓存依然正确；懒加载天然消化版本差异。</li>
 *   <li><b>并发安全</b>：解析放在锁外，竞态下最多重复解析一次，结果幂等。</li>
 * </ul>
 *
 * @author Yahocen
 * @since 2.0
 */
public final class Disp {

    private Disp() {
    }

    /** 外层按封装类分桶，内层是该类的「名字 → DISPID」表。 */
    private static final ConcurrentHashMap<Class<?>, ConcurrentHashMap<String, Integer>> CACHE =
            new ConcurrentHashMap<>();

    /**
     * 解析（或命中缓存）指定接口上某个方法/属性的 DISPID。
     *
     * @param iface 封装类，作为接口级缓存桶的 key（通常传 {@code getClass()}）
     * @param d     底层 Dispatch（须与 iface 对应的 COM 接口一致）
     * @param name  方法/属性名
     * @return DISPID
     */
    public static int id(Class<?> iface, Dispatch d, String name) {
        ConcurrentHashMap<String, Integer> table = CACHE.computeIfAbsent(iface, k -> new ConcurrentHashMap<>());
        Integer id = table.get(name);
        if (id == null) {
            // 唯一一次 GetIDsOfNames；放在锁外，避免持锁跨进程往返
            id = Dispatch.getIDsOfNames(d, new String[]{name})[0];
            table.putIfAbsent(name, id);
        }
        return id;
    }

    /** 等价于 {@code Dispatch.call(d, name, args)}，命中缓存后按 DISPID 调用。 */
    public static Variant call(Class<?> iface, Dispatch d, String name, Object... args) {
        return Dispatch.call(d, id(iface, d, name), args);
    }

    /**
     * 清空全部 DISPID 缓存。
     * <p>
     * DISPID 的稳定性边界是「同一个 AutoCAD 类型库」。JVM 生命周期可能长于一次
     * AutoCAD 连接（app 重启 CAD、重连另一个版本实例），连接重建时调用本方法，
     * 保证后续调用对新连接重新走一次 GetIDsOfNames，而不是复用旧连接的编号。
     * 清空后懒加载自动重建，代价只是每名字一次额外往返。
     * </p>
     */
    public static void clear() {
        CACHE.clear();
    }

    /** 等价于 {@code Dispatch.callN(d, name, args)}，命中缓存后按 DISPID 调用。 */
    public static Variant callN(Class<?> iface, Dispatch d, String name, Object... args) {
        return Dispatch.callN(d, id(iface, d, name), args);
    }

    /** 等价于 {@code Dispatch.get(d, name)}，命中缓存后按 DISPID 读取。 */
    public static Variant get(Class<?> iface, Dispatch d, String name) {
        return Dispatch.get(d, id(iface, d, name));
    }

    /** 等价于 {@code Dispatch.put(d, name, val)}，命中缓存后按 DISPID 写入。 */
    public static void put(Class<?> iface, Dispatch d, String name, Object val) {
        Dispatch.put(d, id(iface, d, name), val);
    }

    /** 等价于 {@code Dispatch.invoke(d, name, wFlags, args, uArgErr)}，用于带索引参数的属性。 */
    public static Variant invoke(Class<?> iface, Dispatch d, String name, int wFlags, Object[] args, int[] uArgErr) {
        return Dispatch.invoke(d, id(iface, d, name), wFlags, args, uArgErr);
    }

    /** 等价于 {@code Dispatch.invokev(d, name, wFlags, args, uArgErr)}，用于带 byref 出参的方法（GetXData/GetBoundingBox 等）。 */
    public static Variant invokev(Class<?> iface, Dispatch d, String name, int wFlags, Variant[] args, int[] uArgErr) {
        return Dispatch.invokev(d, id(iface, d, name), wFlags, args, uArgErr);
    }
}
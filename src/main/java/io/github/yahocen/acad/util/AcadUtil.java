// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.util;

import io.github.yahocen.acad.constant.AutoCadVersion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

/**
 * AutoCAD 环境工具类。
 * <p>
 * 封装本机 AutoCAD 安装探测，供上游直接列出已安装版本（以 {@link AutoCadVersion} 返回）。
 * 探测基于注册表 ProgID → CLSID → LocalServer32 解析出各版本实际启动的 acad.exe 并校验存在，
 * 再按 exe 去重——能过滤卸载残留的孤儿 ProgID（其 CLSID 常被新版本重定向到别的 exe）。
 * 仅支持 Windows（reg.exe 随系统附带，无额外依赖）。
 * </p>
 *
 * @author Yahocen
 */
public class AcadUtil {

    private AcadUtil() {
    }

    /**
     * 列出本机已安装的 AutoCAD 版本（按版本从老到新）。
     * <p>
     * 每个实际安装的 acad.exe 对应一个版本；多版本装装卸卸留下的孤儿 ProgID 已被过滤。
     * 本机未安装 AutoCAD 时返回空列表（不抛异常）。
     * </p>
     *
     * @return 已安装版本列表，老版本在前；无则空列表
     */
    @Operation(summary = "列出已安装的 AutoCAD 版本", description = "探测本机已安装的 AutoCAD 版本（过滤卸载残留），按从老到新返回 AutoCadVersion 列表")
    public static List<AutoCadVersion> getInstalledVersions() {
        return AutoCadVersion.detectInstalled();
    }

    /**
     * 获取本机已安装的最新 AutoCAD 版本。
     *
     * @return 最新已安装版本；本机未安装 AutoCAD 时返回 null
     */
    @Operation(summary = "获取最新已安装版本", description = "返回本机已安装的最新 AutoCAD 版本，未安装返回 null")
    public static AutoCadVersion getLatestInstalled() {
        List<AutoCadVersion> installed = getInstalledVersions();
        return installed.isEmpty() ? null : installed.get(installed.size() - 1);
    }

    /**
     * 判断指定版本是否已安装。
     *
     * @param version 目标版本
     * @return 已安装返回 true
     */
    @Operation(summary = "判断版本是否已安装", description = "判断指定 AutoCadVersion 是否在本机已安装列表中")
    public static boolean isInstalled(@Parameter(description = "目标版本") AutoCadVersion version) {
        return version != null && getInstalledVersions().contains(version);
    }

    /**
     * 列出本机已安装 AutoCAD 版本的 ProgID（如 {@code AutoCAD.Application.24.3}），
     * 顺序与 {@link #getInstalledVersions()} 一致，便于上游直接用于 COM 连接。
     *
     * @return 已安装版本的 ProgID 列表；无则空列表
     */
    @Operation(summary = "列出已安装版本的 ProgID", description = "返回已安装 AutoCAD 版本的 ProgID 列表，可直接用于 COM 连接")
    public static List<String> getInstalledProgIds() {
        return getInstalledVersions().stream().map(AutoCadVersion::getProgId).toList();
    }
}

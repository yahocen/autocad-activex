// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahocen
 * <br>
 * <a href="https://help.autodesk.com/view/OARX/2027/CHS/?guid=GUID-FF023966-A01D-4B64-8957-7C0F02BF8162">About Migrating Automation Projects (ActiveX)</a>
 */
@AllArgsConstructor
@Getter
public enum AutoCadVersion {

    AUTOCAD("AutoCAD", "AutoCAD.Application"),

    // 2000-2002 (R15系列)
    AUTOCAD_2000("AutoCAD 2000", "AutoCAD.Application.15"),
    AUTOCAD_2000I("AutoCAD 2000i", "AutoCAD.Application.15.1"),
    AUTOCAD_2002("AutoCAD 2002", "AutoCAD.Application.15.2"),

    // 2004-2006 (R16系列)
    AUTOCAD_2004("AutoCAD 2004", "AutoCAD.Application.16"),
    AUTOCAD_2005("AutoCAD 2005", "AutoCAD.Application.16.1"),
    AUTOCAD_2006("AutoCAD 2006", "AutoCAD.Application.16.2"),

    // 2007-2009 (R17系列)
    AUTOCAD_2007("AutoCAD 2007", "AutoCAD.Application.17"),
    AUTOCAD_2008("AutoCAD 2008", "AutoCAD.Application.17.1"),
    AUTOCAD_2009("AutoCAD 2009", "AutoCAD.Application.17.2"),

    // 2010-2012 (R18系列)
    AUTOCAD_2010("AutoCAD 2010", "AutoCAD.Application.18"),
    AUTOCAD_2011("AutoCAD 2011", "AutoCAD.Application.18.1"),
    AUTOCAD_2012("AutoCAD 2012", "AutoCAD.Application.18.2"),

    // 2013-2014 (R19系列)
    AUTOCAD_2013("AutoCAD 2013", "AutoCAD.Application.19"),
    AUTOCAD_2014("AutoCAD 2014", "AutoCAD.Application.19.1"),

    // 2015-2016 (R20系列)
    AUTOCAD_2015("AutoCAD 2015", "AutoCAD.Application.20"),
    AUTOCAD_2016("AutoCAD 2016", "AutoCAD.Application.20.1"),

    // 2017-2020 (独立R版本)
    AUTOCAD_2017("AutoCAD 2017", "AutoCAD.Application.21"),
    AUTOCAD_2018("AutoCAD 2018", "AutoCAD.Application.22"),
    AUTOCAD_2019("AutoCAD 2019", "AutoCAD.Application.23"),
    AUTOCAD_2020("AutoCAD 2020", "AutoCAD.Application.23.1"),

    // 2021-2024 (R24系列)
    AUTOCAD_2021("AutoCAD 2021", "AutoCAD.Application.24"),
    AUTOCAD_2022("AutoCAD 2022", "AutoCAD.Application.24.1"),
    AUTOCAD_2023("AutoCAD 2023", "AutoCAD.Application.24.2"),
    AUTOCAD_2024("AutoCAD 2024", "AutoCAD.Application.24.3"),

    // 2025-2026 (R25系列)
    AUTOCAD_2025("AutoCAD 2025", "AutoCAD.Application.25"),
    AUTOCAD_2026("AutoCAD 2026", "AutoCAD.Application.25.1"),

    // 2027 (R26)
    AUTOCAD_2027("AutoCAD 2027", "AutoCAD.Application.26"),

    ;

    private final String release;
    private final String progId;

    /**
     * 按 ProgID（如 "AutoCAD.Application.24.3"）或其版本后缀（如 "24.3"）解析枚举。
     *
     * @param progIdOrSuffix 完整 ProgID 或 R 版本号后缀
     * @return 对应枚举值
     * @throws IllegalArgumentException 无匹配时抛出
     */
    public static AutoCadVersion fromProgId(String progIdOrSuffix) {
        String normalized = progIdOrSuffix.trim();
        if (!normalized.startsWith("AutoCAD.Application")) {
            normalized = "AutoCAD.Application." + normalized;
        }
        for (AutoCadVersion v : values()) {
            if (v.progId.equalsIgnoreCase(normalized)) {
                return v;
            }
        }
        throw new IllegalArgumentException("未知的 AutoCAD ProgID 或版本后缀：" + progIdOrSuffix);
    }

    /**
     * 按枚举名解析（如 "AUTOCAD_2024"），大小写不敏感。
     *
     * @param name 枚举名
     * @return 对应枚举值
     * @throws IllegalArgumentException 无匹配时抛出
     */
    public static AutoCadVersion fromName(String name) {
        return valueOf(name.trim().toUpperCase());
    }

    /**
     * 探测本机已安装的 AutoCAD 版本（按版本从老到新）。
     * <p>
     * 通过注册表 ProgID\CLSID → CLSID\LocalServer32 解析出各 ProgID 实际启动的 acad.exe
     * 路径并校验文件存在，再按 exe 路径分组去重、每组取版本号最大的 ProgID。
     * 去重是必须的：装装卸卸多个版本后，注册表里的孤儿 ProgID 的 CLSID 会被新装版本
     * 重定向到别的 exe（如 ProgID 24 指到 AutoCAD 2024 的 acad.exe），不去重会把同一
     * exe 重复报成多个版本。
     * </p>
     *
     * @return 已安装版本列表（每个 acad.exe 一个版本）；本机无 AutoCAD 时返回空列表
     */
    public static List<AutoCadVersion> detectInstalled() {
        // exe 路径（小写归一）→ 指向它的版本组
        java.util.Map<String, List<AutoCadVersion>> byExe = new java.util.LinkedHashMap<>();
        for (AutoCadVersion v : values()) {
            if (v == AUTOCAD) {
                continue; // 版本无关 ProgID，多版本共存时不可靠，跳过
            }
            String exe = resolveServerExe(v.progId);
            if (exe != null) {
                byExe.computeIfAbsent(exe, k -> new ArrayList<>()).add(v);
            }
        }
        List<AutoCadVersion> installed = new ArrayList<>();
        for (List<AutoCadVersion> group : byExe.values()) {
            // 同一 exe 的多个 ProgID 取版本号最大的（该 exe 的规范 ProgID）
            group.sort(java.util.Comparator.comparingInt(AutoCadVersion::progIdOrder));
            installed.add(group.get(group.size() - 1));
        }
        return installed;
    }

    /**
     * ProgID 版本后缀（如 "24.3"）的可排序序号：主版本×10000+次版本。
     */
    private int progIdOrder() {
        String[] parts = progId.substring("AutoCAD.Application.".length()).split("\\.");
        int major = Integer.parseInt(parts[0]);
        int minor = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        return major * 10000 + minor;
    }

    /**
     * 解析 ProgID 实际启动的 acad.exe 路径（小写归一）；无法解析或 exe 不存在返回 null。
     * 结果缓存：detectInstalled / isProgIdHijacked 会重复查询。
     */
    private static final java.util.Map<String, String> SERVER_EXE_CACHE =
            new java.util.concurrent.ConcurrentHashMap<>();

    private static String resolveServerExe(String progId) {
        return SERVER_EXE_CACHE.computeIfAbsent(progId, AutoCadVersion::resolveServerExe0);
    }

    private static String resolveServerExe0(String progId) {
        String clsid = regQueryDefaultValue("HKLM\\SOFTWARE\\Classes\\" + progId + "\\CLSID");
        if (clsid == null || !clsid.startsWith("{")) {
            return null;
        }
        String server = regQueryDefaultValue("HKLM\\SOFTWARE\\Classes\\CLSID\\" + clsid + "\\LocalServer32");
        if (server == null || server.isBlank()) {
            return null;
        }
        // LocalServer32 形如 "C:\...\acad.exe /Automation"，剥掉命令行参数（路径本身可含空格）
        String exe = server;
        int argPos = server.indexOf(" /");
        if (argPos > 0) {
            exe = server.substring(0, argPos);
        }
        exe = exe.replace("\"", "").trim();
        if (!exe.toLowerCase().endsWith(".exe") || !java.nio.file.Path.of(exe).toFile().isFile()) {
            return null;
        }
        return exe.toLowerCase();
    }

    /**
     * 该版本的 ProgID 是否已被其他版本劫持。
     * <p>
     * 卸载旧版本后，残留 ProgID 的 CLSID LocalServer32 会被新装版本重定向到新 exe
     * （如 ProgID 24.1 指向 AutoCAD 2024 的 acad.exe）。此时 CoCreate 该 ProgID 会以
     * 错误 CLSID 激活 2024，AutoCAD 检测到版本不符后自行崩溃（挂崩溃恢复对话框成为
     * 僵尸进程并占住 ROT）。调用方应跳过 CoCreate，直接定位本版本 exe 启动。
     * 判定标准：存在版本号更大且解析到同一 exe 的其他 ProgID。
     * </p>
     */
    public boolean isProgIdHijacked() {
        if (this == AUTOCAD) {
            return false;
        }
        String exe = resolveServerExe(progId);
        if (exe == null) {
            return false;
        }
        for (AutoCadVersion v : values()) {
            if (v == this || v == AUTOCAD) {
                continue;
            }
            if (exe.equals(resolveServerExe(v.progId)) && v.progIdOrder() > progIdOrder()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 读注册表键的默认值；用 reg query 而非 JNI，输出按 ISO-8859-1 解码
     * （只关心 ASCII 的路径/CLSID，避免 GBK/UTF-8 编码坑）。
     *
     * @return 默认值字符串；键不存在或无默认值返回 null
     */
    private static String regQueryDefaultValue(String key) {
        try {
            Process p = new ProcessBuilder("reg", "query", key, "/ve").redirectErrorStream(true).start();
            String output = new String(p.getInputStream().readAllBytes(), java.nio.charset.StandardCharsets.ISO_8859_1);
            p.waitFor();
            for (String line : output.split("\n")) {
                // 行形如 "  (默认)    REG_SZ    {28B9AA7C-...}"，标签本地化但 REG_SZ 固定
                int pos = line.indexOf("REG_SZ");
                if (pos >= 0) {
                    String value = line.substring(pos + "REG_SZ".length()).trim();
                    return value.isEmpty() ? null : value;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}

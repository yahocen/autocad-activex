package io.github.yahocen.acad.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yahocen
 * <br>
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-FF023966-A01D-4B64-8957-7C0F02BF8162">About Migrating Automation Projects (ActiveX)</a>
 */
@AllArgsConstructor
@Getter
public enum AutoCadVersion {

    AUTOCAD("AutoCAD", "AutoCAD.Application"),

    // 2000-2002 (R15系列)
    AUTOCAD_2000("AutoCAD 2000", "AutoCAD.Application.15"),
    AUTOCAD_2002("AutoCAD 2002", "AutoCAD.Application.15.1"),

    // 2004-2006 (R16系列)
    AUTOCAD_2004("AutoCAD 2004", "AutoCAD.Application.16.0"),
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

    ;

    private final String release;
    private final String progId;

}

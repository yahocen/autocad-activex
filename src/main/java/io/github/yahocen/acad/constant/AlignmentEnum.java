// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-27030E1C-6480-4A64-BE0A-E72C1FFF2023">Alignment Property (ActiveX)</a>
 * @author Yahocen
 */
@Slf4j
@AllArgsConstructor
public enum AlignmentEnum {

    MiddleCenter(10),
    MiddleRight(9),

    ;

    @Getter
    final int alignment;

    public static AlignmentEnum getAlignment(int alignment) {
        for (AlignmentEnum value : values()) {
            if (value.alignment == alignment) {
                return value;
            }
        }
        log.warn("AlignmentEnum not found: {}", alignment);
        return null;
    }

}

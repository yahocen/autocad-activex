package io.github.yahocen.acad.constant;

import lombok.Getter;

/**
 * AcRegenType 枚举，用于指定要重新生成的视口
 * @author Yahocen
 */
public enum AcRegenType {

    AC_ACTIVE_VIEWPORT(1),
    AC_ALL_VIEWPORTS(2);

    @Getter
    private final int value;

    AcRegenType(int value) {
        this.value = value;
    }

}

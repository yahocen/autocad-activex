package io.github.yahocen.acad.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Yahocen
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AcadEntityName {

    String value();

}

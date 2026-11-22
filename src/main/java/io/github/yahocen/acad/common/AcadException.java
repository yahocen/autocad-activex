package io.github.yahocen.acad.common;

import cn.hutool.core.util.StrUtil;

/**
 * @author Yahocen
 */
public class AcadException extends RuntimeException {

  public AcadException(String message, Throwable cause, Object... args) {
      super(StrUtil.format(message, args), cause);
  }

  public AcadException(String message, Object... args) {
    super(StrUtil.format(message, args), null);
  }

}

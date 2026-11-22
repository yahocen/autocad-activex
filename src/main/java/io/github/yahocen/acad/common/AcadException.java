// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.common;


/**
 * @author Yahocen
 */
public class AcadException extends RuntimeException {

  public AcadException(String message, Throwable cause, Object... args) {
      super(Internal.format(message, args), cause);
  }

  public AcadException(String message, Object... args) {
    super(Internal.format(message, args), null);
  }

}

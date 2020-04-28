package com.user.exception.handler;

public class PermissionCannotBeCreatedException extends RuntimeException {
  private static final long serialVersionUID = 2628751230192062496L;

  public PermissionCannotBeCreatedException(String message) {
    super(message);
  }
}

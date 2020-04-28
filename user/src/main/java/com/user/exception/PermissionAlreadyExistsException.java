package com.user.exception;

public class PermissionAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 164429038134975534L;

  public PermissionAlreadyExistsException(String message) {
    super(message);
  }
}

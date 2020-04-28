package com.user.exception;

public class UserRoleAlreadyExistsException extends RuntimeException {

  private static final long serialVersionUID = 6420237584098442757L;

  public UserRoleAlreadyExistsException(String message) {
    super(message);
  }
}

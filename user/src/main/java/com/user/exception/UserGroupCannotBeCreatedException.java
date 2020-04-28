package com.user.exception;

public class UserGroupCannotBeCreatedException extends RuntimeException {
  private static final long serialVersionUID = 3899908878463348871L;

  public UserGroupCannotBeCreatedException(String message) {
    super(message);
  }
}

package com.user.exception;

public class UserGroupAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 9170169636414903354L;

  public UserGroupAlreadyExistsException(String message) {
    super(message);
  }
}

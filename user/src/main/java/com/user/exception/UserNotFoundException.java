package com.user.exception;

public class UserNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -273819876008460039L;

  public UserNotFoundException(String message) {
    super(message);
  }
}

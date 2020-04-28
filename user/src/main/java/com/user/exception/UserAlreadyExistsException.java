package com.user.exception;

public class UserAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = -3002194342514304310L;

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}

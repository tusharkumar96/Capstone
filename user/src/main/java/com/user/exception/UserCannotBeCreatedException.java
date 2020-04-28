package com.user.exception;

public class UserCannotBeCreatedException extends RuntimeException {
  private static final long serialVersionUID = -3002194342514304310L;

  public UserCannotBeCreatedException(String message) {
    super(message);
  }
}

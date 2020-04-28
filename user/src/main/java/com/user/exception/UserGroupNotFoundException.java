package com.user.exception;

public class UserGroupNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -3006640494633898184L;

  public UserGroupNotFoundException(String message) {
    super(message);
  }
}

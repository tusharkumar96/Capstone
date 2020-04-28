package com.user.exception;

public class UserRoleNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 4976645403799541540L;

  public UserRoleNotFoundException(String message) {
    super(message);
  }
}

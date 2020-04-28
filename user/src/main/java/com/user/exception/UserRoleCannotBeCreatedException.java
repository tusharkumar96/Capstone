package com.user.exception;

public class UserRoleCannotBeCreatedException extends RuntimeException {
  private static final long serialVersionUID = 4976645403799541540L;

  public UserRoleCannotBeCreatedException(String message) {
    super(message);
  }
}

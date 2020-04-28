package com.user.exception;

public class PermissionNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1776437692397510040L;

  public PermissionNotFoundException(String message) {
    super(message);
  }
}

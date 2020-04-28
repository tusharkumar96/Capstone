package com.user.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.user.exception.ErrorResponse;
import com.user.exception.UserAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserAlreadyExistsException.class)
  public final ResponseEntity<Object> handleRecordNotFoundException(
      UserAlreadyExistsException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    String message = ex.getLocalizedMessage();
    details.add(message);
    ErrorResponse error = new ErrorResponse("User already exists", details);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }
}

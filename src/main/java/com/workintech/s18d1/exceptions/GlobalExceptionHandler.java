package com.workintech.s18d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BurgerException.class)
  public ResponseEntity<BurgerErrorResponse> handleBurgerException(BurgerException ex) {
    log.error("BurgerException: {}", ex.getMessage());
    BurgerErrorResponse response = new BurgerErrorResponse(ex.getMessage());
    return new ResponseEntity<>(response, ex.getHttpStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BurgerErrorResponse> handleGenericException(Exception ex) {
    log.error("Exception: {}", ex.getMessage());
    BurgerErrorResponse response = new BurgerErrorResponse(ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

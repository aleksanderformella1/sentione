package com.sentione.wordfrequency;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
class GenericExceptionControllerAdvice {

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorDto> handle(Exception e) {
    return ResponseEntity.internalServerError().body(
        new ErrorDto(INTERNAL_SERVER_ERROR, e.getMessage()));
  }

  record ErrorDto(HttpStatus error, String reason) {
  }
}

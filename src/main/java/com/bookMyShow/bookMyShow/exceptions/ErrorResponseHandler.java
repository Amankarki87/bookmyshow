package com.bookMyShow.bookMyShow.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Error.class)
    public ResponseEntity<Object> handleExceptions(Error exception, WebRequest webRequest) {
        return new ResponseEntity<>(exception,exception.getErrorResponse().getCode());
    }
}

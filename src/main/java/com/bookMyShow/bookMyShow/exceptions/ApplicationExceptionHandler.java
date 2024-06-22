package com.bookMyShow.bookMyShow.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Error.class)
    public ResponseEntity<Object> handleExceptions(Error exception, WebRequest webRequest) {
        return new ResponseEntity<>(exception, exception.getCode());
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleNoSuchElementFoundException(
            ElementAlreadyExistsException exception
    ) {
        return buildErrorResponse(exception,HttpStatus.CONFLICT,false);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      HttpStatus httpStatus,
                                                      Boolean isTraceOn) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .status("Error")
                .build();


        if (isTraceOn) {
            //Add stackTrace in log. errorResponse.setStackTrace(new StackTraceElement(exception));
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

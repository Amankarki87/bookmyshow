package com.bookMyShow.bookMyShow.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {
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

    @ExceptionHandler(value= HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        return buildErrorResponse(ex,HttpStatus.METHOD_NOT_ALLOWED,false);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBadRequestException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(
                    ValidationError
                            .builder()
                            .name(fieldName)
                            .status(errorMessage)
                            .build()
            );
        });

        return buildErrorResponse(ex,HttpStatus.BAD_REQUEST,false,errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        return buildErrorResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR,true);
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
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            errorResponse.setStackTrace(sw.toString());
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      HttpStatus httpStatus,
                                                      Boolean isTraceOn,
                                                      List<ValidationError> errors
                                                      ) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message("Validations error")
                .status("Error")
                .errors(errors)
                .build();


        if (isTraceOn) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            errorResponse.setStackTrace(sw.toString());
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

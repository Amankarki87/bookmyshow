package com.bookMyShow.bookMyShow.exceptions;

public class MissingAuthorizationHeaderException extends RuntimeException {
    public MissingAuthorizationHeaderException(String message){
        super(message);
    }
}

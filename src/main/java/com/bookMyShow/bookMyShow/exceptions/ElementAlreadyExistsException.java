package com.bookMyShow.bookMyShow.exceptions;

public class ElementAlreadyExistsException extends RuntimeException {
    public ElementAlreadyExistsException(String message){
        super(message);
    }
}

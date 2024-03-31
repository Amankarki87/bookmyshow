package com.bookMyShow.bookMyShow.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Data
@Builder
public class ErrorResponse<T> {
    private String status;
    private HttpStatus code;
    private String message;
    private HashMap<String,T> data;
    private HashMap<String,T> meta;
}

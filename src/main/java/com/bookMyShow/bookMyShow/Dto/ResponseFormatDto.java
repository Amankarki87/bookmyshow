package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ResponseFormatDto<T> {
    private String status;
    private HttpStatus statusCode;
    private String message;
    private T data;
    private Map<String,T> meta = new HashMap<String,T>(); // Use for holding pagination or any other additional properties for the payload.
}

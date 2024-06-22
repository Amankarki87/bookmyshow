package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ResponseFormatDto<T> {
    /** status for response format. */
    private String status;
    /** statusCode for response format. */
    private HttpStatus statusCode;
    /** message for response format. */
    private String message;
    /** data for response format. */
    private T data;
    /** Meta field . */
    private Map<String,T> meta = new HashMap<>(); // Use for holding pagination or any other additional properties for the payload.
}

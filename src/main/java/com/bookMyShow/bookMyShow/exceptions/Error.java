package com.bookMyShow.bookMyShow.exceptions;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.*;

@Data
@Builder
public class Error extends RuntimeException {
    private String status;
    private HttpStatus code;
    private String message;
    @Builder.Default
    private List<ErrorResponse> errors = new ArrayList<>();
    @Builder.Default
    private Map<String, Object> meta = new HashMap<>();
    @Builder.Default
    private long timestamp = new Date().getTime();

    @JsonIgnore
    @Override
    public Throwable getCause() {
        return super.getCause();
    }

    @JsonIgnore
    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    @JsonIgnore
    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }
}

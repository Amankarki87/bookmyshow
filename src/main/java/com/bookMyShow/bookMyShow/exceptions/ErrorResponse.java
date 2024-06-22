package com.bookMyShow.bookMyShow.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class ErrorResponse {
    private String status;
    private String message;
    private String stackTrace;
    @Builder.Default
    private List<ErrorResponse> errors = new ArrayList<>();
}

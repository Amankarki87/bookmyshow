package com.bookMyShow.bookMyShow.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class ErrorResponse {
    private String status;
    private String message;
    @Builder.Default
    private String stackTrace = "";
    @Builder.Default
    private List<ValidationError> errors = new ArrayList<>();
}

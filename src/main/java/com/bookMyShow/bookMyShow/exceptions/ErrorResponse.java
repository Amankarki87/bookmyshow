package com.bookMyShow.bookMyShow.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String name;
    private String value;
}

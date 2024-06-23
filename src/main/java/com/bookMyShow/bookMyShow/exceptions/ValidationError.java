package com.bookMyShow.bookMyShow.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationError {
    private String name;
    private String status;
}

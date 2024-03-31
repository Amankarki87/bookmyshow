package com.bookMyShow.bookMyShow.exceptions;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Error extends RuntimeException {
    private ErrorResponse errorResponse;
    private long timestamp = new Date().getTime();
}

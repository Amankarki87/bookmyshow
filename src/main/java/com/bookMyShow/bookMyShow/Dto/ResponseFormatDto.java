package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ResponseFormatDto<T> {
    private T data;
    @Builder.Default
    private Map<String,T> meta = new HashMap<String,T>(); // Use for holding pagination or any other additional properties for the payload.
}

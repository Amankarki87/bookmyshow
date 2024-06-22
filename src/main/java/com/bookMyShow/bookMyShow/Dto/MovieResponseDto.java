package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponseDto {
    /** Movie id. */
    private Long id;
    /** Movie name. */
    private String name;
}

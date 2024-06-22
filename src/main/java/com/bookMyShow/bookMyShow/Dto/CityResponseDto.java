package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityResponseDto {
    /** City id. */
    private Long id;
    /** City name. */
    private String name;
}

package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityResponseDto {
    private Long id;
    private String name;
}

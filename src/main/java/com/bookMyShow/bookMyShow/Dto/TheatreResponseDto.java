package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TheatreResponseDto {
    private String name;
    private String address;
    private Long id;
    private Long cityId;
}

package com.bookMyShow.bookMyShow.Dto;

import lombok.Data;

@Data
public class TheatreRequestDto {
    private String name;
    private String address;
    private Long cityId;
}

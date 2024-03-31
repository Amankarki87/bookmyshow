package com.bookMyShow.bookMyShow.Dto;

import lombok.Data;

@Data
public class ShowRequestDto {
    private String startTime;
    private String endTime;
    private Long movieId;
    private Long auditoriumId;
}

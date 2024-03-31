package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowResponseDto {
    private Long id;
    private String startTime;
    private String endTime;
    private Long movieId;
    private Long auditoriumId;
}

package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditoriumResponseDto {
    private Long id;
    private String name;
    private Long theatreId;
}

package com.bookMyShow.bookMyShow.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditoriumResponseDto {
    /** Auditorium id. */
    private Long id;
    /** Auditorium name. */
    private String name;
    /** Theatre in auditorium. */
    private Long theatreId;
}

package com.bookMyShow.bookMyShow.Dto;

import lombok.Data;

@Data
public class AuditoriumRequestDto {
    /** Auditorium request name. */
    private String name;
    /** Theatre for auditorium. */
    private Long theatreId;
}

package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.SeatType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatResponseDto {
    private Long id;
    private String seatNumber;
    private SeatType seatType;
    private Long auditoriumId;
}

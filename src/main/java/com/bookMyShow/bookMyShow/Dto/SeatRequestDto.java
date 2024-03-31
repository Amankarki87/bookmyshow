package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.SeatType;
import lombok.Data;

@Data
public class SeatRequestDto {
    private String seatNumber;
    private SeatType seatType;
    private Long auditoriumId;
}

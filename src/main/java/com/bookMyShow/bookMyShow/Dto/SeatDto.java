package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.SeatType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class SeatDto {
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private String row;
}

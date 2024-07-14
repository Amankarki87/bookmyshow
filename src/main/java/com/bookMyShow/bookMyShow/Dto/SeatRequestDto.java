package com.bookMyShow.bookMyShow.Dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatRequestDto {
    private Long auditoriumId;
    private List<SeatDto> seats;
}

package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.Dto.SeatDto;
import com.bookMyShow.bookMyShow.models.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> createSeat(Long auditoriumId, List<SeatDto> seats);
}

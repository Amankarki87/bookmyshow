package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Seat;
import com.bookMyShow.bookMyShow.models.SeatType;

public interface SeatService {
    Seat save(String seatNumber, SeatType seatType, Long auditoriumId);
}

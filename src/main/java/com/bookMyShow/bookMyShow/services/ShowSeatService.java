package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.ShowSeat;
import com.bookMyShow.bookMyShow.models.ShowSeatState;

public interface ShowSeatService {
    ShowSeat save(Long seatId, Long showId, ShowSeatState seatState);
}

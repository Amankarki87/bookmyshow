package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(
            List<Long> seats,
            Long showId,
            Long userId,
            int amount
    );
}

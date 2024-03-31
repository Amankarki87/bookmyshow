package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Show;

public interface ShowService {
    Show save(String startTime, String endTime,Long movieId,Long auditoriumId);
}

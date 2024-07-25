package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Auditorium;

public interface AuditoriumService {
    Auditorium createAuditorium(String name,Long theatreId);
}

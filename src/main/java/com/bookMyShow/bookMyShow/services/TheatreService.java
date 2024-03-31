package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Theatre;

public interface TheatreService {
    Theatre save(String name,String address,Long cityId);
}

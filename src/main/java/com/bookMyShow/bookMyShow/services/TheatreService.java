package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Theatre;

import java.util.List;

public interface TheatreService {
    Theatre createTheatre(String name,String address,Long cityId);
    List<Theatre> theatres(String cityName);
}

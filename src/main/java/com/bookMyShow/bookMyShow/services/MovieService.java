package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Genre;
import com.bookMyShow.bookMyShow.models.Movie;

public interface MovieService {
    Movie save(String name, Genre movieGenre);
}

package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Genre;
import com.bookMyShow.bookMyShow.models.Movie;

public interface MovieService {
    Movie createMovie(String name, Genre movieGenre);
}

package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie save(String name) {
        Movie movie = new Movie();
        movie.setName(name);
        Movie result = movieRepository.save(movie);
        return result;
    }
}

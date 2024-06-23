package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.exceptions.ElementAlreadyExistsException;
import com.bookMyShow.bookMyShow.models.Genre;
import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie createMovie(String name,Genre movieGenre) {
        Optional<Movie> movie = movieRepository.findByName(name);
        String movieName = movie.get().getName();

        if (movie.isPresent()) {
            throw new ElementAlreadyExistsException(ApiConstant.MOVIE_ALREADY_EXISTS);
        }

        Movie movieDto = Movie
                .builder()
                .name(name)
                .movieGenre(movieGenre)
                .build();

        Movie result = movieRepository.save(movieDto);
        return result;
    }
}

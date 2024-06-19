package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.exceptions.Error;
import com.bookMyShow.bookMyShow.models.Genre;
import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie save(String name,Genre movieGenre) {
        Optional<Movie> movie = movieRepository.findByName(name);

        if (movie.isPresent()) {
            Error error = Error.builder()
                    .code(HttpStatus.CONFLICT)
                    .message(ApiConstant.MOVIE_ALREADY_EXISTS)
                    .status(ApiConstant.ERROR)
                    .build();
            throw error;
        }

        Movie moviedto = Movie
                .builder()
                .name(name)
                .movieGenre(movieGenre)
                .build();

        Movie result = movieRepository.save(moviedto);
        return result;
    }
}

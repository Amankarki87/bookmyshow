package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.AuditoriumConstant;
import com.bookMyShow.bookMyShow.constants.MovieConstant;
import com.bookMyShow.bookMyShow.exceptions.Error;
import com.bookMyShow.bookMyShow.exceptions.ErrorResponse;
import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.models.Show;
import com.bookMyShow.bookMyShow.repositories.AuditoriumRepository;
import com.bookMyShow.bookMyShow.repositories.MovieRepository;
import com.bookMyShow.bookMyShow.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    AuditoriumRepository auditoriumRepository;

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Show save(String startTime, String endTime,Long movieId,Long auditoriumId) {
        Optional<Auditorium> auditorium = auditoriumRepository.findById(auditoriumId);

        if(!auditorium.isPresent()) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .status(ApiConstant.ERROR)
                    .message(AuditoriumConstant.AUDITORIUM_NOT_FOUND)
                    .build();

            Error error = Error.builder()
                    .errorResponse(errorResponse)
                    .build();

            throw error;
        }

        Optional<Movie> movie = movieRepository.findById(movieId);

        if(!movie.isPresent()) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .status(ApiConstant.ERROR)
                    .message(MovieConstant.MOVIE_NOT_FOUND)
                    .build();

            Error error = Error.builder()
                    .errorResponse(errorResponse)
                    .build();

            throw error;
        }

        Show show = new Show();
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setMovie(movie.get());
        show.setAuditorium(auditorium.get());

        Show result = showRepository.save(show);
        return result;
    }
}

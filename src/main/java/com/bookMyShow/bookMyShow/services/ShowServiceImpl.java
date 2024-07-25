package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ErrorMessages;
import com.bookMyShow.bookMyShow.exceptions.ElementNotFoundException;
import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.models.Show;
import com.bookMyShow.bookMyShow.repositories.AuditoriumRepository;
import com.bookMyShow.bookMyShow.repositories.MovieRepository;
import com.bookMyShow.bookMyShow.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Show createShow(String startTime, String endTime,Long movieId,Long auditoriumId) {
        Optional<Auditorium> auditorium = auditoriumRepository.findById(auditoriumId);

        if(auditorium.isEmpty()) {
            throw new ElementNotFoundException(ErrorMessages.AUDITORIUM_NOT_FOUND);
        }

        Optional<Movie> movie = movieRepository.findById(movieId);

        if(!movie.isPresent()) {
            throw new ElementNotFoundException(ErrorMessages.MOVIE_NOT_FOUND);
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

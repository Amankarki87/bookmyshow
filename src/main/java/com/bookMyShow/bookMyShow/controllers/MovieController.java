package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.constants.UrlConstant;
import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstant.API_V1_VERSIONING + UrlConstant.MOVIE_BASE_URL)
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasAuthority('PRIVILEGE_MOVIE_CREATE')")
    @PostMapping
    public ResponseEntity<ResponseFormatDto> createMovie(@Valid @RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = movieService.createMovie(
                movieRequestDto.getName(),
                movieRequestDto.getMovieGenre()
        );

        MovieResponseDto movieResponseDto = MovieResponseDto.builder().
                id(movie.getId()).
                name(movie.getName()).
                build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(movieResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

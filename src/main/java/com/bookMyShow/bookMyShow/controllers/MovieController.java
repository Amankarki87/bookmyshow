package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.MovieConstant;
import com.bookMyShow.bookMyShow.models.Movie;
import com.bookMyShow.bookMyShow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API_V1_VERSIONING + MovieConstant.MOVIE_BASE_URL)
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<ResponseFormatDto> save(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = movieService.save(movieRequestDto.getName());

        MovieResponseDto movieResponseDto = MovieResponseDto.builder().
                id(movie.getId()).
                name(movie.getName()).
                build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .status(ApiConstant.SUCCESS)
                .data(movieResponseDto)
                .statusCode(HttpStatus.CREATED)
                .message(MovieConstant.MOVIE_CREATED)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

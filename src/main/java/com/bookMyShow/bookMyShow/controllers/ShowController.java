package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.CityConstant;
import com.bookMyShow.bookMyShow.constants.ShowConstant;
import com.bookMyShow.bookMyShow.models.City;
import com.bookMyShow.bookMyShow.models.Show;
import com.bookMyShow.bookMyShow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API_V1_VERSIONING + ShowConstant.SHOW_BASE_URL)
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping
    public ResponseEntity<ResponseFormatDto> createShow(@RequestBody ShowRequestDto showRequestDto) {
        Show show = showService.createShow(showRequestDto.getStartTime(), showRequestDto.getEndTime(),showRequestDto.getMovieId(),showRequestDto.getAuditoriumId());

        ShowResponseDto showResponseDto = ShowResponseDto.builder().
                id(show.getId()).
                startTime(show.getStartTime()).
                endTime(show.getEndTime())
                .movieId(show.getMovie().getId())
                .auditoriumId(show.getAuditorium().getId()).
                build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(showResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

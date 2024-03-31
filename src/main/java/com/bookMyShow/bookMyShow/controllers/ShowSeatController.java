package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.ShowSeatConstant;
import com.bookMyShow.bookMyShow.models.ShowSeat;
import com.bookMyShow.bookMyShow.services.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API_V1_VERSIONING + ShowSeatConstant.SHOW_SEAT_BASE_URL)
public class ShowSeatController {
    @Autowired
    ShowSeatService showSeatService;

    @PostMapping
    public ResponseEntity<ResponseFormatDto> save(@RequestBody ShowSeatRequestDto showSeatRequestDto) {
        ShowSeat showSeat = showSeatService.save(showSeatRequestDto.getSeatId(),showSeatRequestDto.getShowId(),showSeatRequestDto.getState());

        ShowSeatResponseDto showSeatResponseDto = ShowSeatResponseDto.builder()
                .state(showSeat.getState())
                .seatId(showSeat.getSeat().getId())
                .showId(showSeat.getShow().getId())
                .build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .status(ApiConstant.SUCCESS)
                .data(showSeatResponseDto)
                .statusCode(HttpStatus.CREATED)
                .message(ShowSeatConstant.SHOW_SEAT_CREATED)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

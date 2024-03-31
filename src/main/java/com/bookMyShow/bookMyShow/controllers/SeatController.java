package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.SeatConstant;
import com.bookMyShow.bookMyShow.models.Seat;
import com.bookMyShow.bookMyShow.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API_V1_VERSIONING + SeatConstant.SEAT_BASE_URL)
public class SeatController {
    @Autowired
    SeatService seatService;

    @PostMapping
    public ResponseEntity<ResponseFormatDto> save(@RequestBody SeatRequestDto seatRequestDto) {
        Seat seat = seatService.save(seatRequestDto.getSeatNumber(),seatRequestDto.getSeatType(),seatRequestDto.getAuditoriumId());

        SeatResponseDto seatResponseDto = SeatResponseDto.builder()
                .seatNumber(seat.getSeatNumber())
                .seatType(seat.getSeatType())
                .id(seat.getId())
                .auditoriumId(seat.getAuditorium().getId())
                .build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .status(ApiConstant.SUCCESS)
                .data(seatResponseDto)
                .statusCode(HttpStatus.CREATED)
                .message(SeatConstant.SEAT_CREATED)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.constants.UrlConstant;
import com.bookMyShow.bookMyShow.models.Seat;
import com.bookMyShow.bookMyShow.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(UrlConstant.API_V1_VERSIONING + UrlConstant.SEAT_BASE_URL)
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PreAuthorize("hasAuthority('PRIVILEGE_THEATRE_CREATE')")
    @PostMapping
    public ResponseEntity<ResponseFormatDto> createSeat(@RequestBody SeatRequestDto seatRequestDto) {
        List<Seat> seats = seatService.createSeat(
                seatRequestDto.getAuditoriumId(),
                seatRequestDto.getSeats()
        );

        List<SeatResponseDto> response = new ArrayList<>();

        seats.forEach(seat -> {
            SeatResponseDto seatResponseDto = SeatResponseDto.builder()
                    .seatNumber(seat.getSeatNumber())
                    .seatType(seat.getSeatType())
                    .id(seat.getId())
                    .auditoriumId(seat.getAuditorium().getId())
                    .build();

            response.add(seatResponseDto);
        });

        ResponseFormatDto result = ResponseFormatDto.builder()
                .data(response)
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

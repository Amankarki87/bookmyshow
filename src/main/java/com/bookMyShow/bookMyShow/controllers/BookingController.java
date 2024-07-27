package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.constants.UrlConstant;
import com.bookMyShow.bookMyShow.models.Booking;
import com.bookMyShow.bookMyShow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstant.API_V1_VERSIONING + UrlConstant.BOOKING_BASE_URL)
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<ResponseFormatDto> createCity(@RequestBody BookingRequestDto bookingRequestDto) {
        Booking booking = bookingService.createBooking(
                bookingRequestDto.getSeatIds(),
                bookingRequestDto.getShowId(),
                bookingRequestDto.getUserId(),
                bookingRequestDto.getAmount()
        );

        BookingResponseDto bookingResponseDto = BookingResponseDto.builder().
                id(booking.getId()).
                build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(bookingResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

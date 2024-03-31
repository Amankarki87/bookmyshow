package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.SeatConstant;
import com.bookMyShow.bookMyShow.constants.ShowConstant;
import com.bookMyShow.bookMyShow.exceptions.Error;
import com.bookMyShow.bookMyShow.exceptions.ErrorResponse;
import com.bookMyShow.bookMyShow.models.*;
import com.bookMyShow.bookMyShow.repositories.SeatRepository;
import com.bookMyShow.bookMyShow.repositories.ShowRepository;
import com.bookMyShow.bookMyShow.repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {
    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ShowRepository showRepository;

    @Override
    public ShowSeat save(Long seatId, Long showId, ShowSeatState state) {
        Optional<Show> show = showRepository.findById(showId);

        if(!show.isPresent()) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .status(ApiConstant.ERROR)
                    .message(ShowConstant.SHOW_NOT_FOUND)
                    .build();

            Error error = Error.builder()
                    .errorResponse(errorResponse)
                    .build();

            throw error;
        }

        Optional<Seat> seat = seatRepository.findById(seatId);

        if(!seat.isPresent()) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .status(ApiConstant.ERROR)
                    .message(SeatConstant.SEAT_NOT_FOUND)
                    .build();

            Error error = Error.builder()
                    .errorResponse(errorResponse)
                    .build();

            throw error;
        }

        ShowSeat showSeat = new ShowSeat();
        showSeat.setSeat(seat.get());
        showSeat.setShow(show.get());
        showSeat.setState(state);
        ShowSeat showSeatState = showSeatRepository.save(showSeat);
        return showSeatState;
    }
}

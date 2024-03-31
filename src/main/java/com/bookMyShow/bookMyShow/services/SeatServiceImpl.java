package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.AuditoriumConstant;
import com.bookMyShow.bookMyShow.exceptions.Error;
import com.bookMyShow.bookMyShow.exceptions.ErrorResponse;
import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.models.Seat;
import com.bookMyShow.bookMyShow.models.SeatType;
import com.bookMyShow.bookMyShow.repositories.AuditoriumRepository;
import com.bookMyShow.bookMyShow.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    AuditoriumRepository auditoriumRepository;

    @Override
    public Seat save(String seatNumber, SeatType seatType, Long auditoriumId) {
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

        Seat seat = new Seat();
        seat.setSeatNumber(seatNumber);
        seat.setSeatType(seatType);
        seat.setAuditorium(auditorium.get());

        Seat result = seatRepository.save(seat);
        return result;
    }
}

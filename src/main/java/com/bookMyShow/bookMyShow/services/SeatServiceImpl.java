package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.Dto.SeatDto;
import com.bookMyShow.bookMyShow.constants.ErrorMessages;
import com.bookMyShow.bookMyShow.exceptions.ElementAlreadyExistsException;
import com.bookMyShow.bookMyShow.exceptions.ElementNotFoundException;
import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.models.Seat;
import com.bookMyShow.bookMyShow.repositories.AuditoriumRepository;
import com.bookMyShow.bookMyShow.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Override
    public List<Seat> createSeat(Long auditoriumId,List<SeatDto> seatDetailsDto) {
        Optional<Auditorium> auditorium = auditoriumRepository.findById(auditoriumId);

        if (auditorium.isEmpty()) {
            throw new ElementNotFoundException(ErrorMessages.AUDITORIUM_NOT_FOUND);
        }

        List<String> seatNumbers = seatDetailsDto.stream()
                .map(SeatDto::getSeatNumber)
                .collect(Collectors.toList());


        List<Seat> seats = seatRepository.findByAuditoriumIdAndSeatNumberIn(
                auditoriumId,
                seatNumbers
        );

        Map<String, Seat> existingSeatMap = seats.stream()
                .collect(Collectors.toMap(
                        seat -> seat.getSeatNumber() + "-" + seat.getRow(),
                        seat -> seat
                ));


        List<Seat> seatDtos = new ArrayList<>();
        seatDetailsDto.forEach(seatDetailDto -> {
            String seatKey = seatDetailDto.getSeatNumber() + "-" + seatDetailDto.getRow();
            if (existingSeatMap.containsKey(seatKey)) {
                throw new ElementAlreadyExistsException(
                        "Seat number " + seatDetailDto.getSeatNumber() + " already exists in row " +
                                seatDetailDto.getRow()
                );
            }

            Seat seat = new Seat();
            seat.setSeatNumber(seatDetailDto.getSeatNumber());
            seat.setSeatType(seatDetailDto.getSeatType());
            seat.setAuditorium(auditorium.get());
            seat.setRow(seatDetailDto.getRow());
            seatDtos.add(seat);
        });

        List<Seat> result = seatRepository.saveAll(seatDtos);
        return result;
    }
}

package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.TheatreConstant;
import com.bookMyShow.bookMyShow.exceptions.Error;
import com.bookMyShow.bookMyShow.exceptions.ErrorResponse;
import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.models.Theatre;
import com.bookMyShow.bookMyShow.repositories.AuditoriumRepository;
import com.bookMyShow.bookMyShow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    AuditoriumRepository auditoriumRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Override
    public Auditorium save(String name,Long theatreId) {
        Optional<Theatre> theatre = theatreRepository.findById(theatreId);

        if(!theatre.isPresent()) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .status(ApiConstant.ERROR)
                    .message(TheatreConstant.THEATRE_NOT_FOUND)
                    .build();

            Error error = Error.builder()
                    .errorResponse(errorResponse)
                    .build();

            throw error;
        }

        Auditorium auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium.setTheatre(theatre.get());

        Auditorium result = auditoriumRepository.save(auditorium);
        return result;
    }
}

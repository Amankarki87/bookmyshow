package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.exceptions.ElementAlreadyExistsException;
import com.bookMyShow.bookMyShow.exceptions.ElementNotFoundException;
import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.models.Theatre;
import com.bookMyShow.bookMyShow.repositories.AuditoriumRepository;
import com.bookMyShow.bookMyShow.repositories.TheatreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public Auditorium createAuditorium(String name,Long theatreId) {
        Optional<Theatre> theatre = theatreRepository.findById(theatreId);

        if(theatre.isEmpty()) {
            throw new ElementNotFoundException("No Theatre found");
        }

        Optional<Auditorium> auditorium = auditoriumRepository.findByNameAndTheatreId(
                name,
                theatre.get().getId()
        );

        if(auditorium.isPresent()) {
            throw new ElementAlreadyExistsException("Auditorium already exists in theatre");
        }

        Auditorium auditoriumDto = new Auditorium();
        auditoriumDto.setName(name);
        auditoriumDto.setTheatre(theatre.get());

        Auditorium result = auditoriumRepository.save(auditoriumDto);
        return result;
    }
}

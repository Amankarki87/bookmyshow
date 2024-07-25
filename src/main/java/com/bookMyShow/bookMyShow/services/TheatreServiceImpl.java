package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ErrorMessages;
import com.bookMyShow.bookMyShow.exceptions.ElementNotFoundException;
import com.bookMyShow.bookMyShow.models.City;
import com.bookMyShow.bookMyShow.models.Theatre;
import com.bookMyShow.bookMyShow.repositories.CityRepository;
import com.bookMyShow.bookMyShow.repositories.TheatreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Theatre createTheatre(String name, String address,Long cityId) {
        Theatre theatre = new Theatre();
        theatre.setName(name);
        theatre.setAddress(address);

        Optional<City> city = cityRepository.findById(cityId);

        if(!city.isPresent()) {
            throw new ElementNotFoundException(ErrorMessages.CITY_NOT_FOUND);
        }

        theatre.setCity(city.get());

        Theatre result = theatreRepository.save(theatre);
        return result;
    }

    @Override
    public List<Theatre> theatres(String cityName) {
        Optional<City> city = cityRepository.findByName(cityName);

        if (!city.isPresent()) {
            List<Theatre> theatres = new ArrayList<>();
            return theatres;
        }

        List<Theatre> theatres = theatreRepository.findAllByCityId(city.get().getId());
        return theatres;
    }
}

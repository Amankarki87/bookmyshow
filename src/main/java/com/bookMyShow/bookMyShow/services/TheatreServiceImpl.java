package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.CityConstant;
import com.bookMyShow.bookMyShow.exceptions.Error;
import com.bookMyShow.bookMyShow.exceptions.ErrorResponse;
import com.bookMyShow.bookMyShow.models.City;
import com.bookMyShow.bookMyShow.models.Theatre;
import com.bookMyShow.bookMyShow.repositories.CityRepository;
import com.bookMyShow.bookMyShow.repositories.TheatreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public Theatre save(String name, String address,Long cityId) {
        Theatre theatre = new Theatre();
        theatre.setName(name);
        theatre.setAddress(address);

        Optional<City> city = cityRepository.findById(cityId);

        if(!city.isPresent()) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .status(ApiConstant.ERROR)
                    .message(CityConstant.CITY_NOT_FOUND)
                    .build();

            Error error = Error.builder()
                    .errorResponse(errorResponse)
                    .build();

            throw error;
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

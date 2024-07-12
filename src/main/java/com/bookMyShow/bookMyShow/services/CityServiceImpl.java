package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.City;
import com.bookMyShow.bookMyShow.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City createCity(String name) {
        City city = new City();
        city.setName(name);
        City result = cityRepository.save(city);
        return result;
    }
}

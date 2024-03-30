package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.services.CityService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    @Autowired
    CityService cityService;
}

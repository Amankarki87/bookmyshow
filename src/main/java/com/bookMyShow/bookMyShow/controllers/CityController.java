package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.CityRequestDto;
import com.bookMyShow.bookMyShow.Dto.CityResponseDto;
import com.bookMyShow.bookMyShow.Dto.ResponseFormatDto;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.CityConstant;
import com.bookMyShow.bookMyShow.models.City;
import com.bookMyShow.bookMyShow.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API_V1_VERSIONING + CityConstant.CITY_BASE_URL)
public class CityController {
    @Autowired
    CityService cityService;

    @PostMapping
    public ResponseEntity<ResponseFormatDto> save(@RequestBody CityRequestDto cityRequestDto) {
        City city = cityService.save(cityRequestDto.getName());

        CityResponseDto cityResponseDto = CityResponseDto.builder().
                id(city.getId()).
                name(city.getName()).
                build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(cityResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

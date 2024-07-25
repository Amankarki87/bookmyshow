package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.CityRequestDto;
import com.bookMyShow.bookMyShow.Dto.CityResponseDto;
import com.bookMyShow.bookMyShow.Dto.ResponseFormatDto;
import com.bookMyShow.bookMyShow.constants.UrlConstant;
import com.bookMyShow.bookMyShow.models.City;
import com.bookMyShow.bookMyShow.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstant.API_V1_VERSIONING + UrlConstant.CITY_BASE_URL)
public class CityController {
    @Autowired
    private CityService cityService;

    @PreAuthorize("hasAuthority('PRIVILEGE_CITY_CREATE')")
    @PostMapping
    public ResponseEntity<ResponseFormatDto> createCity(@RequestBody CityRequestDto cityRequestDto) {
        City city = cityService.createCity(cityRequestDto.getName());

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

package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.AuditoriumRequestDto;
import com.bookMyShow.bookMyShow.Dto.AuditoriumResponseDto;
import com.bookMyShow.bookMyShow.Dto.ResponseFormatDto;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.AuditoriumConstant;
import com.bookMyShow.bookMyShow.constants.CityConstant;
import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API_V1_VERSIONING + AuditoriumConstant.AUDITORIUM_BASE_URL)
public class AuditoriumController {
    @Autowired
    AuditoriumService auditoriumService;

    @PostMapping
    public ResponseEntity<ResponseFormatDto> save(@RequestBody AuditoriumRequestDto auditoriumRequestDto) {
        Auditorium auditorium = auditoriumService.save(auditoriumRequestDto.getName(),auditoriumRequestDto.getTheatreId());

        AuditoriumResponseDto auditoriumResponseDto = AuditoriumResponseDto.builder().
                id(auditorium.getId()).
                name(auditorium.getName()).
                build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(auditoriumResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

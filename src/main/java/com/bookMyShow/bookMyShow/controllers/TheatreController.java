package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.constants.TheatreConstant;
import com.bookMyShow.bookMyShow.models.Theatre;
import com.bookMyShow.bookMyShow.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.API_V1_VERSIONING + TheatreConstant.THEATRE_BASE_URL)
public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @PostMapping
    public ResponseEntity<ResponseFormatDto> save(@RequestBody TheatreRequestDto theatreRequestDto) {
        Theatre theatre = theatreService.save(
                theatreRequestDto.getName(),
                theatreRequestDto.getAddress(),
                theatreRequestDto.getCityId()
        );

        TheatreResponseDto theatreResponse = TheatreResponseDto.builder()
                .name(theatre.getName())
                .address(theatre.getAddress())
                .id(theatre.getId())
                .cityId(theatre.getCity().getId())
                .build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(theatreResponse)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseFormatDto> theatres(@RequestParam String cityName) {
        List<Theatre> theatres = theatreService.theatres(cityName);

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(theatres)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}

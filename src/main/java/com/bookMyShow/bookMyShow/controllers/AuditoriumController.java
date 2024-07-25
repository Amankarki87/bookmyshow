package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.AuditoriumRequestDto;
import com.bookMyShow.bookMyShow.Dto.AuditoriumResponseDto;
import com.bookMyShow.bookMyShow.Dto.ResponseFormatDto;
import com.bookMyShow.bookMyShow.constants.UrlConstant;
import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstant.API_V1_VERSIONING + UrlConstant.AUDITORIUM_BASE_URL)
public class AuditoriumController {
    @Autowired
    private AuditoriumService auditoriumService;

    @PreAuthorize("hasAuthority('PRIVILEGE_THEATRE_CREATE')")
    @PostMapping
    public ResponseEntity<ResponseFormatDto> createAuditorium(@RequestBody AuditoriumRequestDto auditoriumRequestDto) {
        Auditorium auditorium = auditoriumService.createAuditorium(auditoriumRequestDto.getName(),auditoriumRequestDto.getTheatreId());

        AuditoriumResponseDto auditoriumResponseDto = AuditoriumResponseDto.builder()
                .id(auditorium.getId())
                .name(auditorium.getName())
                .theatreId(auditorium.getTheatre().getId())
                .build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(auditoriumResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

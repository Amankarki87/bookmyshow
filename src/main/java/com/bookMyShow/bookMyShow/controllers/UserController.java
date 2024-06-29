package com.bookMyShow.bookMyShow.controllers;

import com.bookMyShow.bookMyShow.Dto.*;
import com.bookMyShow.bookMyShow.config.auth.JwtService;
import com.bookMyShow.bookMyShow.constants.ApiConstant;
import com.bookMyShow.bookMyShow.models.User;
import com.bookMyShow.bookMyShow.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bookMyShow.bookMyShow.constants.UserConstant.USER_BASE_URL;

@RestController
@RequestMapping(ApiConstant.API_V1_VERSIONING + USER_BASE_URL)
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseFormatDto> signup(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        User user = userService.signup(
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword(),
                signUpRequestDto.getGender(),
                signUpRequestDto.getRoles()
        );

        SignUpResponseDto signUpResponseDto = SignUpResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(signUpResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseFormatDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        User authenticatedUser = userService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .name(authenticatedUser.getName())
                .email(authenticatedUser.getEmail())
                .gender(authenticatedUser.getGender())
                .token(jwtToken)
                .build();

        ResponseFormatDto response = ResponseFormatDto.builder()
                .data(loginResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponseDto {
    private String email;
    private String name;
    private Gender gender;
}

package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.Genre;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieRequestDto {
    @NotEmpty(message = "Must not be Empty and NULL")
    private String name;
    @NotNull
    private Genre movieGenre;
}

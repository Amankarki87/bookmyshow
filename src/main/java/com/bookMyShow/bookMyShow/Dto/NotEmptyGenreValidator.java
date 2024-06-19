package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.Genre;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyGenreValidator implements ConstraintValidator<NotEmptyGenre, Genre> {
    @Override
    public void initialize(NotEmptyGenre constraintAnnotation) {
    }

    @Override
    public boolean isValid(Genre genre, ConstraintValidatorContext context) {
        return genre != null;
    }
}

package com.bookMyShow.bookMyShow.Dto;

import com.bookMyShow.bookMyShow.models.Gender;
import com.bookMyShow.bookMyShow.models.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SignUpRequestDto {
    @NotEmpty(message = "Email must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, max = 10)
    private String password;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    private Gender gender;
    private List<Role> roles;
}

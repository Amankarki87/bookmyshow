package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.models.Gender;
import com.bookMyShow.bookMyShow.models.Role;
import com.bookMyShow.bookMyShow.models.User;

import java.util.List;

public interface UserService {
    User signup(String email, String password, Gender gender, List<Role> roles);

    User login(String email, String passwprd);
}

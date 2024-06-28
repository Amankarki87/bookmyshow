package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.UserConstant;
import com.bookMyShow.bookMyShow.exceptions.ElementAlreadyExistsException;
import com.bookMyShow.bookMyShow.models.Gender;
import com.bookMyShow.bookMyShow.models.Role;
import com.bookMyShow.bookMyShow.models.User;
import com.bookMyShow.bookMyShow.repositories.RoleRepository;
import com.bookMyShow.bookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User signup(String email, String password, Gender gender, List<Role> roles) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new ElementAlreadyExistsException(UserConstant.EMAIL_ALREADY_EXISTS);
        }

        List<Role> roleArray = new ArrayList<>();
        roles.forEach(role -> {
            Role roleDto = roleRepository.save(role);
            roleArray.add(roleDto);
        });


        User userDto = User
                .builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .gender(gender)
                .roles(roleArray)
                .build();

        User result = userRepository.save(userDto);
        return result;
    }

    @Override
    public User login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                email, password
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<User> user = userRepository.findByEmail(email);
        return user.get();
    }
}

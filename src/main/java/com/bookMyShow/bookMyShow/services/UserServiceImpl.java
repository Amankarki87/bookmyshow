package com.bookMyShow.bookMyShow.services;

import com.bookMyShow.bookMyShow.constants.ErrorMessages;
import com.bookMyShow.bookMyShow.exceptions.ElementAlreadyExistsException;
import com.bookMyShow.bookMyShow.exceptions.ElementNotFoundException;
import com.bookMyShow.bookMyShow.models.Gender;
import com.bookMyShow.bookMyShow.models.Role;
import com.bookMyShow.bookMyShow.models.RoleEnum;
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

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User signup(String email, String password, Gender gender, List<RoleEnum> rolesDto) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new ElementAlreadyExistsException(ErrorMessages.EMAIL_ALREADY_EXISTS);
        }

        List<Role> roles = roleRepository.findByNameIn(rolesDto);

        if (roles.isEmpty()) {
            throw new ElementNotFoundException(ErrorMessages.NO_ROLE_FOUND);
        }

        User userDto = User
                .builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .gender(gender)
                .roles(roles)
                .build();

        User result = userRepository.save(userDto);
        return result;
    }

    @Override
    public User login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<User> user = userRepository.findByEmail(email);
        return user.get();
    }
}

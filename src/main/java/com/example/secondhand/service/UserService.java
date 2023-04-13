package com.example.secondhand.service;

import com.example.secondhand.dto.UserDto;
import com.example.secondhand.exception.GenericException;
import com.example.secondhand.model.User;
import com.example.secondhand.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = repository.save(user);
        return UserDto.builder()
                .id(savedUser.getId())
                .role(savedUser.getRole())
                .build();
    }

    public UserDto getUser(String username) {
        var user = findUserByEmail(username);
        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .build();
    }

    public User findUserByEmail(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> GenericException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("User not found by given id!")
                        .build());
    }
}

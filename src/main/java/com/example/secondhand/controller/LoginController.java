package com.example.secondhand.controller;

import com.example.secondhand.dto.LoginRequest;
import com.example.secondhand.dto.TokenResponseDto;
import com.example.secondhand.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final AuthService service;

    public LoginController(AuthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TokenResponseDto>login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}

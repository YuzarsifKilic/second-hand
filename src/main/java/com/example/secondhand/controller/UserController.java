package com.example.secondhand.controller;

import com.example.secondhand.dto.request.EmailCheckRequest;
import com.example.secondhand.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkTheEmailInUse(@RequestBody EmailCheckRequest request) {
        return ResponseEntity.ok(service.checkTheEmailInUse(request));
    }
}

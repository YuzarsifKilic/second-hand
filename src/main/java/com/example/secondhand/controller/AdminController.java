package com.example.secondhand.controller;

import com.example.secondhand.dto.model.AdminDto;
import com.example.secondhand.service.AdminService;
import com.example.secondhand.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService service;
    private final UserService userService;

    public AdminController(AdminService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<AdminDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> findAdminById(@PathVariable String id) {
        return ResponseEntity.ok(service.findAdminById(id));
    }

    @DeleteMapping("/{id}")
    public void deleterUser(@PathVariable String id) {
        userService.deleterUser(id);
    }
}

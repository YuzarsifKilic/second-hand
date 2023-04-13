package com.example.secondhand.controller;

import com.example.secondhand.dto.AdminDto;
import com.example.secondhand.dto.CreateAdminRequest;
import com.example.secondhand.model.Admin;
import com.example.secondhand.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<AdminDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> findAdminById(@PathVariable String id) {
        return ResponseEntity.ok(service.findAdminById(id));
    }
}

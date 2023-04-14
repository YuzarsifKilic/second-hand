package com.example.secondhand.controller;

import com.example.secondhand.dto.PhoneDto;
import com.example.secondhand.dto.PhoneFilter;
import com.example.secondhand.service.PhoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phone")
public class PhoneController {

    private final PhoneService service;

    public PhoneController(PhoneService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PhoneDto>> getAllPhones() {
        return ResponseEntity.ok(service.getAllPhones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneDto> findPcById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findPhoneById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PhoneDto>> phoneFilter(@RequestBody PhoneFilter filter) {
        return ResponseEntity.ok(service.phoneFilter(filter));
    }
}

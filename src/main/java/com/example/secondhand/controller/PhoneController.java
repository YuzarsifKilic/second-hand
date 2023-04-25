package com.example.secondhand.controller;

import com.example.secondhand.dto.model.PhoneDto;
import com.example.secondhand.dto.filter.PhoneFilter;
import com.example.secondhand.dto.model.PhoneResponseDto;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.request.CreatePhoneRequest;
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
    public ResponseEntity<List<ProductDto>> getAllPhones() {
        return ResponseEntity.ok(service.getAllPhones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneResponseDto> findPcById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findPhoneById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> phoneFilter(@RequestBody PhoneFilter filter) {
        return ResponseEntity.ok(service.phoneFilter(filter));
    }

    @PostMapping("/save")
    public void savePhone(@RequestBody CreatePhoneRequest request) {
        service.savePhone(request);
    }
}

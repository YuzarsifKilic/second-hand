package com.example.secondhand.controller;

import com.example.secondhand.dto.CreateSellerRequest;
import com.example.secondhand.dto.SellerDto;
import com.example.secondhand.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    private final SellerService service;

    public SellerController(SellerService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public void createSeller(@RequestBody CreateSellerRequest request) {
        service.createSeller(request);
    }

    @GetMapping("/getAll")
    public List<SellerDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> findSellerById(@PathVariable String id) {
        return ResponseEntity.ok(service.findSellerById(id));
    }
}

package com.example.secondhand.controller;

import com.example.secondhand.dto.filter.ComputerAccessoriesFilter;
import com.example.secondhand.dto.model.ComputerAccessoriesDto;
import com.example.secondhand.dto.model.ComputerAccessoriesResponseDto;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.request.CreateComputerAccessoriesRequest;
import com.example.secondhand.service.ComputerAccessoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/computer-accessories")
public class ComputerAccessoriesController {

    private final ComputerAccessoriesService service;

    public ComputerAccessoriesController(ComputerAccessoriesService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerAccessoriesResponseDto> findComputerAccessoriesById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findComputerAccessoriesById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> filter(@RequestBody ComputerAccessoriesFilter filter) {
        return ResponseEntity.ok(service.filter(filter));
    }

    @PostMapping("/save")
    public void saveComputerAccessories(@RequestBody CreateComputerAccessoriesRequest request) {
        service.saveComputerAccessories(request);
    }
}

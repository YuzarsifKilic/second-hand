package com.example.secondhand.controller;

import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
import com.example.secondhand.dto.model.TvDto;
import com.example.secondhand.dto.filter.TvFilter;
import com.example.secondhand.dto.model.TvResponseDto;
import com.example.secondhand.dto.request.CreateTvRequest;
import com.example.secondhand.service.TvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tv")
public class TvController {

    private final TvService service;

    public TvController(TvService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductDto>> getAllTv() {
        return ResponseEntity.ok(service.getAllTv());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvResponseDto> findTvById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findTvById(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProductResponseDto>> filterTv(@RequestBody TvFilter tvFilter) {
        return ResponseEntity.ok(service.filterTv(tvFilter));
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveTv(@RequestBody CreateTvRequest request) {
        return ResponseEntity.ok(service.saveTv(request));
    }
}

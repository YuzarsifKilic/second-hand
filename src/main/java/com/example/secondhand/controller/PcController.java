package com.example.secondhand.controller;

import com.example.secondhand.dto.model.PcDto;
import com.example.secondhand.dto.filter.PcFilter;
import com.example.secondhand.dto.model.PcResponseDto;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
import com.example.secondhand.dto.request.CreatePcRequest;
import com.example.secondhand.service.PcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pc")
public class PcController {

    private final PcService service;

    public PcController(PcService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductDto>> getAllPc() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PcResponseDto> findPcById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findPcById(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProductResponseDto>> filterPc(@RequestBody PcFilter pcFilter) {
        return ResponseEntity.ok(service.filterPc(pcFilter));
    }

    @PostMapping("/save")
    public ResponseEntity<Long> savePc(@RequestBody CreatePcRequest request) {
        return ResponseEntity.ok(service.savePc(request));
    }
}

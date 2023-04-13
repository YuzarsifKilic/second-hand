package com.example.secondhand.controller;

import com.example.secondhand.dto.GpuDto;
import com.example.secondhand.service.GpuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gpu")
public class GpuController {

    private final GpuService service;

    public GpuController(GpuService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<GpuDto>> getAllGpu() {
        return ResponseEntity.ok(service.getAllGpu());
    }
}

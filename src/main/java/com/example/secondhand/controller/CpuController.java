package com.example.secondhand.controller;

import com.example.secondhand.dto.CpuDto;
import com.example.secondhand.service.CpuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cpu")
public class CpuController {

    private final CpuService service;

    public CpuController(CpuService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CpuDto>> getAllCpu() {
        return ResponseEntity.ok(service.getAllCpu());
    }
}

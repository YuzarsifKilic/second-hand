package com.example.secondhand.controller;

import com.example.secondhand.dto.PcDto;
import com.example.secondhand.dto.PcRequest;
import com.example.secondhand.service.PcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pc")
public class PcController {

    private final PcService service;

    public PcController(PcService service) {
        this.service = service;
    }

    @GetMapping("/get")
    public ResponseEntity<PcDto> findPcById(@RequestBody PcRequest request) {
        return ResponseEntity.ok(service.findPcById(request.id()));
    }
}

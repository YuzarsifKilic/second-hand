package com.example.secondhand.controller;

import com.example.secondhand.dto.model.PcDto;
import com.example.secondhand.dto.filter.PcFilter;
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
    public ResponseEntity<List<PcDto>> getAllPc() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PcDto> findPcById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findPcById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PcDto>> filterPc(@RequestBody PcFilter pcFilter) {
        return ResponseEntity.ok(service.filterPc(pcFilter));
    }

    @PostMapping("/save")
    public void savePc(@RequestBody CreatePcRequest request) {
        service.savePc(request);
    }


}

package com.example.secondhand.controller;

import com.example.secondhand.dto.model.GamingConsoleDto;
import com.example.secondhand.dto.filter.GamingConsoleFilter;
import com.example.secondhand.dto.model.GamingConsoleResponseDto;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
import com.example.secondhand.dto.request.CreateGamingConsoleRequest;
import com.example.secondhand.service.GamingConsoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gaming-console")
public class GamingConsoleController {

    private final GamingConsoleService service;

    public GamingConsoleController(GamingConsoleService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamingConsoleResponseDto> findGamingConsoleById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findGamingConsoleById(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProductResponseDto>> filter(@RequestBody GamingConsoleFilter filter) {
        return ResponseEntity.ok(service.filter(filter));
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveGamingConsole(@RequestBody CreateGamingConsoleRequest request) {
        return ResponseEntity.ok(service.saveGamingConsole(request));
    }
}

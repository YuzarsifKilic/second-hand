package com.example.secondhand.controller;

import com.example.secondhand.dto.model.GamingConsoleDto;
import com.example.secondhand.dto.filter.GamingConsoleFilter;
import com.example.secondhand.dto.model.ProductDto;
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
    public ResponseEntity<GamingConsoleDto> findGamingConsoleById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findGamingConsoleById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> filter(@RequestBody GamingConsoleFilter filter) {
        return ResponseEntity.ok(service.filter(filter));
    }

    @PostMapping("/save")
    public void saveGamingConsole(@RequestBody CreateGamingConsoleRequest request) {
        service.saveGamingConsole(request);
    }
}

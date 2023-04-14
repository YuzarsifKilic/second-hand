package com.example.secondhand.controller;

import com.example.secondhand.dto.ColorDto;
import com.example.secondhand.service.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/color")
public class ColorController {

    private final ColorService service;

    public ColorController(ColorService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ColorDto>> getAllColors() {
        return ResponseEntity.ok(service.getAllColors());
    }
}

package com.example.secondhand.controller;

import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findProductById(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<ProductDto>> getProductsBySellerId(@PathVariable String id) {
        return ResponseEntity.ok(service.findProductBySellerId(id));
    }
}

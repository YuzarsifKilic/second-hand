package com.example.secondhand.controller;

import com.example.secondhand.dto.filter.CategoryFilter;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<ProductResponseDto>> getProductsBySellerId(@PathVariable String id) {
        return ResponseEntity.ok(service.findProductBySellerId(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@RequestBody CategoryFilter categoryFilter) {
        return ResponseEntity.ok(service.getProducts(categoryFilter));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProduct(id));
    }
}

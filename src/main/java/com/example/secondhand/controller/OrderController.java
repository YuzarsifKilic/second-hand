package com.example.secondhand.controller;

import com.example.secondhand.dto.request.CreateOrderRequest;
import com.example.secondhand.dto.model.OrderDto;
import com.example.secondhand.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public void saveOrder(CreateOrderRequest request) {
        service.saveOrder(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findOrder(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOrderById(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDto>> findOrdersByCustomerId(@PathVariable String id) {
        return ResponseEntity.ok(service.findOrdersByCustomerId(id));
    }
}

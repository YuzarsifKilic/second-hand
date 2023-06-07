package com.example.secondhand.controller;

import com.example.secondhand.dto.model.CustomerDealDto;
import com.example.secondhand.dto.model.DealDto;
import com.example.secondhand.dto.model.DealProductDto;
import com.example.secondhand.dto.model.DealResponseDto;
import com.example.secondhand.dto.request.GetDealRequest;
import com.example.secondhand.dto.request.SendMessageRequest;
import com.example.secondhand.service.DealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/deal")
public class DealController {

    private final DealService service;

    public DealController(DealService service) {
        this.service = service;
    }

    @PostMapping("/getMessage")
    public ResponseEntity<List<DealDto>> getMessages(@RequestBody GetDealRequest request) {
        return ResponseEntity.ok(service.getMessages(request));
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody SendMessageRequest request) {
        service.sendMessage(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DealResponseDto>> getDeals(@PathVariable String id) {
        return ResponseEntity.ok(service.getDeals(id));
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity<Set<DealProductDto>> getDeal(@PathVariable String id) {
        return ResponseEntity.ok(service.getDeal(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Set<CustomerDealDto>> getDealsByCustomerId(@PathVariable String id) {
        return ResponseEntity.ok(service.getDealsByCustomerId(id));
    }

}

package com.example.secondhand.controller;

import com.example.secondhand.dto.model.DealDto;
import com.example.secondhand.dto.request.GetDealRequest;
import com.example.secondhand.dto.request.SendMessageRequest;
import com.example.secondhand.service.DealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deal")
public class DealController {

    private final DealService service;

    public DealController(DealService service) {
        this.service = service;
    }

    @GetMapping("/getMessage")
    public ResponseEntity<List<DealDto>> getMessages(@RequestBody GetDealRequest request) {
        return ResponseEntity.ok(service.getMessages(request));
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody SendMessageRequest request) {
        service.sendMessage(request);
    }

}

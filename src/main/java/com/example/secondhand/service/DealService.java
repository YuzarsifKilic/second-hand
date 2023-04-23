package com.example.secondhand.service;

import com.example.secondhand.dto.model.DealDto;
import com.example.secondhand.dto.request.GetDealRequest;
import com.example.secondhand.dto.request.SendMessageRequest;
import com.example.secondhand.model.Deal;
import com.example.secondhand.model.Product;
import com.example.secondhand.repository.DealRepository;
import jakarta.transaction.Transactional;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealService {

    private final DealRepository repository;
    private final ProductService productService;

    public DealService(DealRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Transactional
    public void sendMessage(SendMessageRequest request) {
        Product product = productService.findProduct(request.productId());

        Deal deal = Deal.builder()
                .senderId(request.senderId())
                .receiverId(request.receiverId())
                .message(request.message())
                .product(product)
                .build();

        repository.save(deal);
    }

    public List<DealDto> getMessages(GetDealRequest request) {
        return repository.findAll()
                .stream()
                .filter(d -> d.getSenderId().equals(request.userId()) && d.getProduct().getId() == request.productId())
                .map(DealDto::convert)
                .collect(Collectors.toList());
    }
}

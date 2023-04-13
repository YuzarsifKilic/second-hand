package com.example.secondhand.dto;

public record CreateOrderRequest(String customerId,
                                 Long productId) {
}

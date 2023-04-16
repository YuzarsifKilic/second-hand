package com.example.secondhand.dto.request;

public record CreateOrderRequest(String customerId,
                                 Long productId) {
}

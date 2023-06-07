package com.example.secondhand.dto.model;

public record DealProductDto(Long productId,
                             String shortDetails,
                             String customerId,
                             String customerName,
                             String brandName,
                             float price,
                             String photoUrl) {
}

package com.example.secondhand.dto.model;

public record OrderResponseDto(Long id,
                               Long productId,
                               String shortDetails,
                               String brandName,
                               float price,
                               String photoUrl) {
}

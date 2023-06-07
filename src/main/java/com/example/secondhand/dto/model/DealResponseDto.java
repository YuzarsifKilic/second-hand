package com.example.secondhand.dto.model;

public record DealResponseDto(Long id,
                              ProductResponseDto product,
                              String customerId) {
}

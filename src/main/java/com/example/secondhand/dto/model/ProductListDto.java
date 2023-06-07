package com.example.secondhand.dto.model;

public record ProductListDto(Long id,
                             String sellerId,
                             String brandName,
                             String shortDetails,
                             float price,
                             String url) {
}

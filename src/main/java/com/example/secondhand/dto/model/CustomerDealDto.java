package com.example.secondhand.dto.model;

public record CustomerDealDto(Long productId,
                              String shortDetails,
                              String brandName,
                              float price,
                              String sellerId,
                              String sellerUsername,
                              String url) {
}

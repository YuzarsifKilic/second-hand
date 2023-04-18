package com.example.secondhand.dto.request;

public record CreateTvRequest(String shortDetails,
                              float price,
                              String details,
                              int productBrandId,
                              String sellerId,
                              String brandModel,
                              String screenSize,
                              String resolution,
                              String screenType,
                              String quality) {
}

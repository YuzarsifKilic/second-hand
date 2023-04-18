package com.example.secondhand.dto.request;

public record CreateComputerAccessoriesRequest(String shortDetails,
                                               float price,
                                               String details,
                                               int productBrandId,
                                               String sellerId,
                                               String brandModel,
                                               String connectivityTechnology,
                                               int colorId) {
}

package com.example.secondhand.dto.request;

public record CreateGamingConsoleRequest (String shortDetails,
                                          float price,
                                          String details,
                                          int productBrandId,
                                          String sellerId,
                                          String brandModel,
                                          int storage,
                                          int colorId) {}

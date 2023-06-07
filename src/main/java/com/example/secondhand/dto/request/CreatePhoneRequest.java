package com.example.secondhand.dto.request;

public record CreatePhoneRequest(String shortDetails,
                                 float price,
                                 String details,
                                 int productBrandId,
                                 String sellerId,
                                 String brandModel,
                                 String os,
                                 double screenSize,
                                 String screenType,
                                 int ramSize,
                                 int storage,
                                 int camera,
                                 int frontCamera,
                                 int colorId) {
}

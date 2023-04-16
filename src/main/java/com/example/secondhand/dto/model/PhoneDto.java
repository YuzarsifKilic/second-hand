package com.example.secondhand.dto.model;

import com.example.secondhand.model.Phone;

public record PhoneDto(Long id,
                       String shortDetails,
                       float price,
                       boolean isSold,
                       String details,
                       ProductBrandDto productBrand,
                       SellerDto seller,
                       String brandModel,
                       String os,
                       double screenSize,
                       String screenType,
                       int ramSize,
                       int camera,
                       int frontCamera,
                       ColorDto color) {

    public static PhoneDto convert(Phone from) {
        return new PhoneDto(
                from.getId(),
                from.getShortDetails(),
                from.getPrice(),
                from.isSold(),
                from.getDetails(),
                ProductBrandDto.convert(from.getProductBrand()),
                SellerDto.convert(from.getSeller()),
                from.getBrandModel(),
                from.getOs(),
                from.getScreenSize(),
                from.getScreenType(),
                from.getRamSize(),
                from.getCamera(),
                from.getFrontCamera(),
                ColorDto.convert(from.getColor()));
    }
}

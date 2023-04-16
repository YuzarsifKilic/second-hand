package com.example.secondhand.dto.model;

import com.example.secondhand.model.GamingConsole;

public record GamingConsoleDto(Long id,
                               String shortDetails,
                               float price,
                               boolean isSold,
                               String details,
                               ProductBrandDto productBrand,
                               SellerDto seller,
                               String brandModel,
                               int storage,
                               ColorDto color) {

    public static GamingConsoleDto convert(GamingConsole from) {
        return new GamingConsoleDto(
                from.getId(),
                from.getShortDetails(),
                from.getPrice(),
                from.isSold(),
                from.getDetails(),
                ProductBrandDto.convert(from.getProductBrand()),
                SellerDto.convert(from.getSeller()),
                from.getBrandModel(),
                from.getStorage(),
                ColorDto.convert(from.getColor()));
    }
}

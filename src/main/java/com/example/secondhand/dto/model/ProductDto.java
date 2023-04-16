package com.example.secondhand.dto.model;

import com.example.secondhand.model.Product;

public record ProductDto(Long id,
                         String shortDetails,
                         float price,
                         boolean isSold,
                         String details,
                         ProductBrandDto productBrand,
                         SellerDto seller) {

    public static ProductDto convert(Product from) {
        return new ProductDto(
                from.getId(),
                from.getShortDetails(),
                from.getPrice(),
                from.isSold(),
                from.getDetails(),
                ProductBrandDto.convert(from.getProductBrand()),
                SellerDto.convert(from.getSeller()));
    }
}

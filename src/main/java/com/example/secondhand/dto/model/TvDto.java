package com.example.secondhand.dto.model;

import com.example.secondhand.model.Tv;

public record TvDto(Long id,
                    String shortDetails,
                    float price,
                    boolean isSold,
                    String details,
                    ProductBrandDto productBrand,
                    SellerDto seller,
                    String brandModel,
                    String screenSize,
                    String resolution,
                    String screenType,
                    String quality) {

    public static TvDto convert(Tv from) {
        return new TvDto(
                from.getId(),
                from.getShortDetails(),
                from.getPrice(),
                from.isSold(),
                from.getDetails(),
                ProductBrandDto.convert(from.getProductBrand()),
                SellerDto.convert(from.getSeller()),
                from.getBrandModel(),
                from.getScreenSize(),
                from.getResolution(),
                from.getScreenType(),
                from.getQuality());
    }
}

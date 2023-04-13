package com.example.secondhand.dto;

import com.example.secondhand.model.ProductBrand;

public record ProductBrandDto(int id,
                              String brandName) {

    public static ProductBrandDto convert(ProductBrand from) {
        return new ProductBrandDto(from.getId(), from.getBrandName());
    }
}

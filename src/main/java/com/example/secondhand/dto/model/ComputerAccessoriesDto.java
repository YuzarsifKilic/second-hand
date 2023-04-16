package com.example.secondhand.dto.model;

import com.example.secondhand.model.ComputerAccessories;

public record ComputerAccessoriesDto(Long id,
                                     String shortDetails,
                                     float price,
                                     boolean isSold,
                                     String details,
                                     ProductBrandDto productBrand,
                                     SellerDto seller,
                                     String brandModel,
                                     String connectivityTechnology,
                                     ColorDto color) {

    public static ComputerAccessoriesDto convert(ComputerAccessories from) {
        return new ComputerAccessoriesDto(
                from.getId(),
                from.getShortDetails(),
                from.getPrice(),
                from.isSold(),
                from.getDetails(),
                ProductBrandDto.convert(from.getProductBrand()),
                SellerDto.convert(from.getSeller()),
                from.getBrandModel(),
                from.getConnectivityTechnology(),
                ColorDto.convert(from.getColor()));
    }
}

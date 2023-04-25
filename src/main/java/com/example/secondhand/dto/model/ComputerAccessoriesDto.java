package com.example.secondhand.dto.model;

import com.example.secondhand.model.ComputerAccessories;

public record ComputerAccessoriesDto(String brandModel,
                                     String connectivityTechnology,
                                     ColorDto color) {

    public static ComputerAccessoriesDto convert(ComputerAccessories from) {
        return new ComputerAccessoriesDto(
                from.getBrandModel(),
                from.getConnectivityTechnology(),
                ColorDto.convert(from.getColor()));
    }
}

package com.example.secondhand.dto.model;

import com.example.secondhand.model.Phone;

public record PhoneDto(String brandModel,
                       String os,
                       double screenSize,
                       String screenType,
                       int ramSize,
                       int camera,
                       int frontCamera,
                       ColorDto color) {

    public static PhoneDto convert(Phone from) {
        return new PhoneDto(
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

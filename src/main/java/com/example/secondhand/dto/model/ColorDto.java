package com.example.secondhand.dto.model;

import com.example.secondhand.model.Color;

public record ColorDto(int id,
                       String colorName) {

    public static ColorDto convert(Color from) {
        return new ColorDto(
                from.getId(),
                from.getColorName());
    }
}

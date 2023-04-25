package com.example.secondhand.dto.model;

import com.example.secondhand.model.GamingConsole;

public record GamingConsoleDto(String brandModel,
                               int storage,
                               ColorDto color) {

    public static GamingConsoleDto convert(GamingConsole from) {
        return new GamingConsoleDto(
                from.getBrandModel(),
                from.getStorage(),
                ColorDto.convert(from.getColor()));
    }
}

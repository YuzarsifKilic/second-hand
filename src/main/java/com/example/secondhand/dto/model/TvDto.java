package com.example.secondhand.dto.model;

import com.example.secondhand.model.Tv;

public record TvDto(String brandModel,
                    String screenSize,
                    String resolution,
                    String screenType,
                    String quality) {

    public static TvDto convert(Tv from) {
        return new TvDto(
                from.getBrandModel(),
                from.getScreenSize(),
                from.getResolution(),
                from.getScreenType(),
                from.getQuality());
    }
}

package com.example.secondhand.dto;

import com.example.secondhand.model.Gpu;

public record GpuDto(int id,
                     String gpuBrand,
                     String gpuBrandModel) {

    public static GpuDto convert(Gpu from) {
        return new GpuDto(
                from.getId(),
                from.getGpuBrand(),
                from.getGpuBrandModel());
    }
}

package com.example.secondhand.dto.model;

import com.example.secondhand.model.Pc;

public record PcDto(Long id,
                    String shortDetails,
                    float price,
                    boolean isSold,
                    String details,
                    ProductBrandDto productBrand,
                    SellerDto seller,
                    CpuDto cpu,
                    double cpuSpeed,
                    GpuDto gpu,
                    int gpuSize,
                    int ramSize,
                    int ramSpeed,
                    double screenSize,
                    int modelYear,
                    String resolution) {

    public static PcDto convert(Pc from) {
        return new PcDto(
                from.getId(),
                from.getShortDetails(),
                from.getPrice(),
                from.isSold(),
                from.getDetails(),
                ProductBrandDto.convert(from.getProductBrand()),
                SellerDto.convert(from.getSeller()),
                CpuDto.convert(from.getCpu()),
                from.getCpuSpeed(),
                GpuDto.convert(from.getGpu()),
                from.getGpuSize(),
                from.getRamSize(),
                from.getRamSpeed(),
                from.getScreenSize(),
                from.getModelYear(),
                from.getResolution());
    }
}

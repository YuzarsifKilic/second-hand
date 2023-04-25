package com.example.secondhand.dto.model;

import com.example.secondhand.model.Pc;

public record PcDto(String brandModel,
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
                from.getBrandModel(),
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

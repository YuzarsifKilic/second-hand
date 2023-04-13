package com.example.secondhand.dto;

import com.example.secondhand.model.Cpu;

public record CpuDto(int id,
                     String cpuBrand,
                     String cpuBrandModel) {

    public static CpuDto convert(Cpu from) {
        return new CpuDto(
                from.getId(),
                from.getCpuBrand(),
                from.getCpuBrandModel());
    }
}

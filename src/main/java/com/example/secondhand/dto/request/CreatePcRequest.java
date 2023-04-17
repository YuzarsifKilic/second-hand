package com.example.secondhand.dto.request;

import com.example.secondhand.dto.model.CpuDto;
import com.example.secondhand.dto.model.GpuDto;
import com.example.secondhand.dto.model.ProductBrandDto;
import com.example.secondhand.dto.model.SellerDto;

public record CreatePcRequest(String shortDetails,
                              float price,
                              String details,
                              int productBrandId,
                              String sellerId,
                              String brandModel,
                              int cpuId,
                              double cpuSpeed,
                              int gpuId,
                              int gpuSize,
                              int ramSize,
                              int ramSpeed,
                              double screenSize,
                              int modelYear,
                              String resolution) {
}

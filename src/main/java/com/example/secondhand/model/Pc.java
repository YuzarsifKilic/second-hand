package com.example.secondhand.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pc extends Product {

    private String brandModel;
    @ManyToOne
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;
    private double cpuSpeed;
    @ManyToOne
    @JoinColumn(name = "gpu_id")
    private Gpu gpu;
    private int gpuSize;
    private int ramSize;
    private int ramSpeed;
    private double screenSize;
    private int modelYear;
    private String resolution;

    public Pc(String shortDetails, float price, boolean isSold, String details, ProductBrand productBrand,
              Seller seller, String brandModel, Cpu cpu, double cpuSpeed,
              Gpu gpu, int gpuSize, int ramSize, int ramSpeed, double screenSize, int modelYear, String resolution) {
        super(shortDetails, price, isSold, details, productBrand, seller);
        this.brandModel = brandModel;
        this.cpu = cpu;
        this.cpuSpeed = cpuSpeed;
        this.gpu = gpu;
        this.gpuSize = gpuSize;
        this.ramSize = ramSize;
        this.ramSpeed = ramSpeed;
        this.screenSize = screenSize;
        this.modelYear = modelYear;
        this.resolution = resolution;
    }
}

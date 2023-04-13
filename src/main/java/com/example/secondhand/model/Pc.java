package com.example.secondhand.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

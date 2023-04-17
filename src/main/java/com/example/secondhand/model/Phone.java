package com.example.secondhand.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Phone extends Product {

    private String brandModel;
    private String os;
    private double screenSize;
    private String screenType;
    private int ramSize;
    private int camera;
    private int frontCamera;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
}

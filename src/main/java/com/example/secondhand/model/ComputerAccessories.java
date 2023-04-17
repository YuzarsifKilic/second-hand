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
public class ComputerAccessories extends Product {

    private String brandModel;
    private String connectivityTechnology;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
}

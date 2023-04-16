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
public class GamingConsole extends Product {

    private String brandModel;
    private int storage;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

}

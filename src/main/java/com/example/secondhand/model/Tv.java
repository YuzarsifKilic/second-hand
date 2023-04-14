package com.example.secondhand.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tv extends Product {

    private String brandModel;
    private String screenSize;
    private String resolution;
    private String screenType;
    private String quality;
}

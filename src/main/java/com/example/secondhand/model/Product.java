package com.example.secondhand.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shortDetails;
    private float price;
    private boolean isSold;
    private String details;
    private boolean isPc;
    private boolean isPhone;
    private boolean isTv;
    private boolean isGamingConsole;
    private boolean isComputerAccessories;
    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private ProductBrand productBrand;
    @OneToMany(mappedBy = "product")
    private Set<ProductPhoto> productPhotos;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @OneToMany(mappedBy = "product")
    private Set<Deal> deals;
}

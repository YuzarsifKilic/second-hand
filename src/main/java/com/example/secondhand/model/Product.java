package com.example.secondhand.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shortDetails;
    private float price;
    private boolean isSold;
    private String details;
    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private ProductBrand productBrand;
    @OneToMany(mappedBy = "product")
    private Set<ProductPhoto> productPhotos;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}

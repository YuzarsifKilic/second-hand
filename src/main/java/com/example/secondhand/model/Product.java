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
    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private ProductBrand productBrand;
    @OneToMany(mappedBy = "product")
    private Set<ProductPhoto> productPhotos;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Product(String shortDetails, float price, boolean isSold, String details, ProductBrand productBrand, Seller seller) {
        this.shortDetails = shortDetails;
        this.price = price;
        this.isSold = isSold;
        this.details = details;
        this.productBrand = productBrand;
        this.seller = seller;
    }
}

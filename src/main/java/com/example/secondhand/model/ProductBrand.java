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
public class ProductBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String brandName;
    @OneToMany(mappedBy = "productBrand")
    private Set<Product> products;
}

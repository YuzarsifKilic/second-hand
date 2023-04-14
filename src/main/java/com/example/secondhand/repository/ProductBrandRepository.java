package com.example.secondhand.repository;

import com.example.secondhand.model.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer> {

    Optional<ProductBrand> getProductBrandByBrandName(String brandName);
}

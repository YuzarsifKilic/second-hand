package com.example.secondhand.repository;

import com.example.secondhand.model.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer> {
}

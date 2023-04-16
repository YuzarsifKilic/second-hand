package com.example.secondhand.service;

import com.example.secondhand.dto.model.ProductBrandDto;
import com.example.secondhand.exception.ProductBrandNotFoundException;
import com.example.secondhand.model.ProductBrand;
import com.example.secondhand.repository.ProductBrandRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductBrandService {

    private final ProductBrandRepository repository;

    public ProductBrandService(ProductBrandRepository repository) {
        this.repository = repository;
    }

    public ProductBrandDto findProductBrandById(int id) {
        return ProductBrandDto.convert(
                repository.findById(id)
                        .orElseThrow(
                                () -> new ProductBrandNotFoundException("Brand didnt find by id : " + id)));
    }

    protected ProductBrand getProduct(String brandName) {
        return repository.getProductBrandByBrandName(brandName)
                .orElseThrow(
                        () -> new ProductBrandNotFoundException("Brand didnt find by name : " + brandName));
    }
}

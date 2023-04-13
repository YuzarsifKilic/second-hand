package com.example.secondhand.service;

import com.example.secondhand.dto.ProductBrandDto;
import com.example.secondhand.exception.ProductBrandNotFoundException;
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
                                () -> new ProductBrandNotFoundException("Brand not find by id : " + id)));
    }
}

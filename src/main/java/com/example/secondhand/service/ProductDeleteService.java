package com.example.secondhand.service;

import com.example.secondhand.model.ProductPhoto;
import com.example.secondhand.repository.ProductPhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDeleteService {

    private final ProductPhotoRepository repository;

    public ProductDeleteService(ProductPhotoRepository repository) {
        this.repository = repository;
    }

    protected void deletePhotosByProductId(Long id) {
        final List<ProductPhoto> photoList = repository.findAll()
                .stream()
                .filter(p -> p.getProduct().getId() == id)
                .collect(Collectors.toList());

        for (ProductPhoto productPhoto : photoList) {
            repository.deleteById(productPhoto.getId());
        }
    }
}

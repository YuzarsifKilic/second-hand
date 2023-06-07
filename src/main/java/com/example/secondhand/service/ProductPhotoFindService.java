package com.example.secondhand.service;

import com.example.secondhand.model.ProductPhoto;
import com.example.secondhand.repository.ProductPhotoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductPhotoFindService {

    private final ProductPhotoRepository repository;

    public ProductPhotoFindService(ProductPhotoRepository repository) {
        this.repository = repository;
    }

    public String getFirstUrlOfProductPhoto(Long id) {
        List<ProductPhoto> productPhotoList = repository.findAll();
        List<ProductPhoto> productPhotos = new ArrayList<>();

        for (ProductPhoto productPhoto : productPhotoList) {
                if (productPhoto.getProduct().getId() == id) {
                productPhotos.add(productPhoto);
            }
        }


        return productPhotos.get(0).getImageLink();
    }
}

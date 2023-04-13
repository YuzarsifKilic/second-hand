package com.example.secondhand.service;

import com.example.secondhand.bucket.BucketName;
import com.example.secondhand.bucket.FileStore;
import com.example.secondhand.model.Product;
import com.example.secondhand.model.ProductPhoto;
import com.example.secondhand.repository.ProductPhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.http.entity.ContentType.*;

@Service
public class ProductPhotoService {

    private final ProductPhotoRepository repository;
    private final ProductService productService;
    private final FileStore fileStore;

    public ProductPhotoService(ProductPhotoRepository repository, ProductService productService, FileStore fileStore) {
        this.repository = repository;
        this.productService = productService;
        this.fileStore = fileStore;
    }

    public void uploadProductPhoto(Long productId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file : " + file.getSize());
        }

        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image");
        }

        Product product = productService.findProduct(productId);

        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), product.getId());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), product.getId());

        try {
            fileStore.save(path, fileName, Optional.of(metaData), file.getInputStream());
            ProductPhoto productPhoto = ProductPhoto.builder()
                    .product(product)
                    .imageLink(fileName)
                    .build();
            repository.save(productPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] downloadImageByUrl(Long id, String url) {
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), id);
        return fileStore.download(path, url);
    }

    public List<String> findPhotoByProductId(Long id) {
        return repository.findAll()
                .stream()
                .filter(i -> id == i.getProduct().getId())
                .map(p -> p.getImageLink())
                .collect(Collectors.toList());
    }
}

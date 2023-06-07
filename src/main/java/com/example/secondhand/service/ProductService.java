package com.example.secondhand.service;

import com.example.secondhand.dto.filter.CategoryFilter;
import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.model.ProductResponseDto;
import com.example.secondhand.exception.ProductNotFoundException;
import com.example.secondhand.model.Product;
import com.example.secondhand.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductDeleteService productDeleteService;
    private final ProductPhotoFindService productPhotoFindService;

    public ProductService(ProductRepository repository,
                          ProductDeleteService productDeleteService,
                          ProductPhotoFindService productPhotoFindService) {
        this.repository = repository;
        this.productDeleteService = productDeleteService;
        this.productPhotoFindService = productPhotoFindService;
    }

    public List<ProductResponseDto> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(p -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(p.getId());
                    return new ProductResponseDto(ProductDto.convert(p), imageUrl);
                })
                .collect(Collectors.toList());
    }

    public ProductResponseDto getProduct(Long id) {
        String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(id);
        Product product = findProduct(id);
        return new ProductResponseDto(ProductDto.convert(product), imageUrl);
    }

    public ProductDto findProductById(Long id) {
        return ProductDto.convert(
                repository.findById(id)
                        .orElseThrow(
                                () -> new ProductNotFoundException("Product didnt find by id : " + id)));
    }

    protected Product findProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product didnt find by id : " + id));
    }

    private List<Product> categoryFilter(CategoryFilter categoryFilter) {
        List<Product> productList = repository.findAll();

        List<Product> productListAfterFilter = new ArrayList<>();

        if (categoryFilter.isPc())
            for (Product product : productList)
                if (product.isPc())
                    productListAfterFilter.add(product);
        if (categoryFilter.isPhone())
            for (Product product : productList)
                if (product.isPhone())
                    productListAfterFilter.add(product);
        if (categoryFilter.isTv())
            for (Product product : productList)
                if (product.isTv())
                    productListAfterFilter.add(product);
        if (categoryFilter.isGamingConsole())
            for (Product product : productList)
                if (product.isGamingConsole())
                    productListAfterFilter.add(product);
        if (categoryFilter.isComputerAccessories())
            for (Product product : productList)
                if (product.isComputerAccessories())
                    productListAfterFilter.add(product);

        return productListAfterFilter;
    }

    public List<ProductResponseDto> getProducts(CategoryFilter categoryFilter) {
        final List<Product> products = categoryFilter(categoryFilter);

        return products.stream()
                .filter(p -> !p.isSold())
                .map(p -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(p.getId());
                    return new ProductResponseDto(ProductDto.convert(p), imageUrl);
                })
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        productDeleteService.deletePhotosByProductId(id);
        repository.deleteById(id);
    }

    public List<ProductResponseDto> findProductBySellerId(String id) {
        return repository.findAll()
                .stream()
                .filter(p -> p.getSeller().getId().equals(id))
                .map(p -> {
                    String imageUrl = productPhotoFindService.getFirstUrlOfProductPhoto(p.getId());
                    return new ProductResponseDto(ProductDto.convert(p), imageUrl);
                })
                .collect(Collectors.toList());
    }

    protected void sellTheProduct(Long id) {
        Product product = findProduct(id);

        product.setSold(true);

        repository.save(product);
    }
}

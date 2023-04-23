package com.example.secondhand.service;

import com.example.secondhand.dto.model.ProductDto;
import com.example.secondhand.dto.request.EmailCheckRequest;
import com.example.secondhand.exception.ProductNotFoundException;
import com.example.secondhand.model.Product;
import com.example.secondhand.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
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

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public List<ProductDto> findProductBySellerId(String id) {
        return repository.findAll()
                .stream()
                .filter(p -> p.getSeller().getId().equals(id))
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }
}

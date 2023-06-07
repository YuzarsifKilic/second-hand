package com.example.secondhand.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDto {

    private ProductDto product;
    private String productPhotoUrl;

}

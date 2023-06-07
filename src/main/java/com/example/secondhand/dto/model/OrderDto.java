package com.example.secondhand.dto.model;

import com.example.secondhand.model.Order;

public record OrderDto(Long id,
                       CustomerDto customer,
                       ProductResponseDto product) {

}

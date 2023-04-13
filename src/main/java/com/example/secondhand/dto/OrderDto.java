package com.example.secondhand.dto;

import com.example.secondhand.model.Order;

public record OrderDto(Long id,
                       CustomerDto customer,
                       ProductDto product) {

    public static OrderDto convert(Order from) {
        return new OrderDto(
                from.getId(),
                CustomerDto.convert(from.getCustomer()),
                ProductDto.convert(from.getProduct()));
    }
}

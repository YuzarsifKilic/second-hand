package com.example.secondhand.dto;

import com.example.secondhand.model.Customer;

public record CustomerDto(String id,
                          String email,
                          String firstName,
                          String lastName) {

    public static CustomerDto convert(Customer from) {
        return new CustomerDto(
                from.getId(),
                from.getEmail(),
                from.getFirstName(),
                from.getLastName()
        );
    }
}

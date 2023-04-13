package com.example.secondhand.dto;

import com.example.secondhand.model.Seller;

public record SellerDto(String id,
                        String email,
                        String username,
                        String address,
                        String phoneNumber) {

    public static SellerDto convert(Seller from) {
        return new SellerDto(
                from.getId(),
                from.getEmail(),
                from.getUsername(),
                from.getAddress(),
                from.getPhoneNumber());
    }
}

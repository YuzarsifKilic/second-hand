package com.example.secondhand.dto;

public record CreateSellerRequest(String email,
                                  String password,
                                  String username,
                                  String address,
                                  String phoneNumber) {
}

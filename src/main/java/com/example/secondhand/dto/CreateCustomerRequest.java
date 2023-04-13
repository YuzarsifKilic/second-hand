package com.example.secondhand.dto;

public record CreateCustomerRequest(String email,
                                    String password,
                                    String firstName,
                                    String lastName) {
}

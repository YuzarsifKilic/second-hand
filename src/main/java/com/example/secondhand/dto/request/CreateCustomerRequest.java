package com.example.secondhand.dto.request;

public record CreateCustomerRequest(String email,
                                    String password,
                                    String firstName,
                                    String lastName) {
}

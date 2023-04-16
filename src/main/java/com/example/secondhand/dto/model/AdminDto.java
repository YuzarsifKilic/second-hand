package com.example.secondhand.dto.model;

import com.example.secondhand.model.Admin;

public record AdminDto(String id,
                       String email,
                       String username) {

    public static AdminDto convert(Admin from) {
        return new AdminDto(
                from.getId(),
                from.getEmail(),
                from.getUsername());
    }
}

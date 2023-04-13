package com.example.secondhand.dto;

import com.example.secondhand.model.Role;
import com.example.secondhand.model.User;
import lombok.Builder;

@Builder
public record UserDto(String id,
                      Role role) {

    public static UserDto convert(User from) {
        return new UserDto(
                from.getId(),
                from.getRole());
    }
}

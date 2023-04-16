package com.example.secondhand.dto.model;

import lombok.Builder;

@Builder
public record TokenResponseDto(String accessToken,
                               UserDto userDto) {
}

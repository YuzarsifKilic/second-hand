package com.example.secondhand.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record TokenResponseDto(String accessToken,
                               UserDto userDto) {
}

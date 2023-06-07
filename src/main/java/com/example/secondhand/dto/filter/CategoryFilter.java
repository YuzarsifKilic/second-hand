package com.example.secondhand.dto.filter;

public record CategoryFilter(boolean isPc,
                             boolean isPhone,
                             boolean isTv,
                             boolean isGamingConsole,
                             boolean isComputerAccessories
                             ) {
}

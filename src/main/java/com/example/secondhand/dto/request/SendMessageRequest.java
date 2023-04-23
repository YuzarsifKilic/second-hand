package com.example.secondhand.dto.request;

public record SendMessageRequest(String receiverId,
                                 String senderId,
                                 Long productId,
                                 String message) {
}

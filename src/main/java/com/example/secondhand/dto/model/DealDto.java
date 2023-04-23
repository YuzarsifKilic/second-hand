package com.example.secondhand.dto.model;

import com.example.secondhand.model.Deal;

import java.sql.Date;
import java.time.LocalDateTime;

public record DealDto(String senderId,
                      String receiverId,
                      String date,
                      String message) {

    public static DealDto convert(Deal from) {
        return new DealDto(
                from.getSenderId(),
                from.getReceiverId(),
                from.getDate().toString(),
                from.getMessage());
    }
}

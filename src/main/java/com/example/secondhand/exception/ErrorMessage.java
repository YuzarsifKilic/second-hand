package com.example.secondhand.exception;

import java.time.LocalDateTime;

public record ErrorMessage(String message,
                           LocalDateTime timeStamp) {
}

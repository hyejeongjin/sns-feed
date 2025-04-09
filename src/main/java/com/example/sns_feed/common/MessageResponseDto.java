package com.example.sns_feed.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MessageResponseDto {

    private final String message;

    public MessageResponseDto(String message) {
        this.message = message;
    }
}

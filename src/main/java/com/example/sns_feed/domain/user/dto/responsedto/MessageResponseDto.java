package com.example.sns_feed.domain.user.dto.responsedto;

import lombok.Getter;

@Getter
public class MessageResponseDto {

    private final String message;

    public MessageResponseDto(String message) {
        this.message = message;
    }
}

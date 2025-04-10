package com.example.sns_feed.domain.user.dto.requestdto;

import lombok.Getter;

@Getter
public class EmailRequestDto {

    private final String email;

    public EmailRequestDto(String email) {
        this.email = email;
    }
}

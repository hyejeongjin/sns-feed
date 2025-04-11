package com.example.sns_feed.domain.user.dto.requestdto;

import lombok.Getter;

@Getter
public class CheckCodeRequestDto {

    private final String email;

    private final String cert;

    public CheckCodeRequestDto(String email, String cert) {
        this.email = email;
        this.cert = cert;
    }
}

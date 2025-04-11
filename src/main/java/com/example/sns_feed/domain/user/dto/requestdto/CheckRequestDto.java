package com.example.sns_feed.domain.user.dto.requestdto;

import lombok.Getter;

@Getter
public class CheckRequestDto {

    private final String cert;

    public CheckRequestDto(String cert) {
        this.cert = cert;
    }
}

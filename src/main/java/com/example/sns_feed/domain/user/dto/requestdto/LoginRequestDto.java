package com.example.sns_feed.domain.user.dto.requestdto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private final String email;
    private final String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

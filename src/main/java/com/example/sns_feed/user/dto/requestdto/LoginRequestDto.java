package com.example.sns_feed.user.dto.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LoginRequestDto {
    private final String email;
    private final String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

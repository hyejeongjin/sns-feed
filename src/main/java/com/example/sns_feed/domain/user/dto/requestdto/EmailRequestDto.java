package com.example.sns_feed.domain.user.dto.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EmailRequestDto {

    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "이메일 형식이 아닙니다")
    private final String email;

    public EmailRequestDto(String email) {
        this.email = email;
    }
}

package com.example.sns_feed.domain.user.dto.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CheckCodeRequestDto {

    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "이메일 형식이 아닙니다")
    private final String email;

    @NotBlank(message = "번호를 입력해주세요")
    private final String cert;

    public CheckCodeRequestDto(String email, String cert) {
        this.email = email;
        this.cert = cert;
    }
}

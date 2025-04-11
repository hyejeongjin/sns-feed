package com.example.sns_feed.domain.user.dto.requestdto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ChangePasswordRequestDto {

    private final String email;

    @Pattern(regexp ="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$!@*()-])[A-Za-z[0-9]$!@*()-]{8,20}",
            message = "최소 8 ~ 20자 입력해주세요.")
    private final String newPassword;

    public ChangePasswordRequestDto(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }
}

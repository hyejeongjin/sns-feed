package com.example.sns_feed.domain.user.dto.requestdto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class WithdrawRequestDto {

    @Pattern(regexp ="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$!@*()-])[A-Za-z[0-9]$!@*()-]{8,20}",
            message = "최소 8 ~ 20자 입력해주세요.(대소문자, 숫자, 특수문자가 최소 1개씩은 포함되어야 합니다.)")
    private final String password;

    public WithdrawRequestDto(String password) {
        this.password = password;
    }
}

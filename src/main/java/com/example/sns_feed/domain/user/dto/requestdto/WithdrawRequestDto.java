package com.example.sns_feed.domain.user.dto.requestdto;

import lombok.Getter;

@Getter
public class WithdrawRequestDto {

    private final String password;

    public WithdrawRequestDto(String password) {
        this.password = password;
    }
}

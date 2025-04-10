package com.example.sns_feed.domain.user.dto.requestdto;

import lombok.Getter;

@Getter
public class ChangePasswordRequestDto {

    private final String newPassword;

    public ChangePasswordRequestDto(String newPassword) {
        this.newPassword = newPassword;
    }
}

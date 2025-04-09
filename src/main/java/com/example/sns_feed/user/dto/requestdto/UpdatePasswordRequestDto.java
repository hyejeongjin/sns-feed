package com.example.sns_feed.user.dto.requestdto;

import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {

    private final String newPassword;
    private final String oldPassword;

    public UpdatePasswordRequestDto(String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }
}

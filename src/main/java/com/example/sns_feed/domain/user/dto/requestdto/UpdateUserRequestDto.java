package com.example.sns_feed.domain.user.dto.requestdto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String profile;

    private final String email;

    private final String mobileNumber;

    public UpdateUserRequestDto(String profile, String email, String mobileNumber) {
        this.profile = profile;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}

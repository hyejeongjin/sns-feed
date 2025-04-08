package com.example.sns_feed.user.dto.requestdto;

import lombok.Getter;

@Getter
public class RequestDto {

    private final String profile;

    private final String userName;

    private final String email;

    private final String password;

    private final String mobileNumber;

    private final String birthDate;

    public RequestDto(String profile, String userName, String email, String password, String mobileNumber, String birthDate) {
        this.profile = profile;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.birthDate = birthDate;
    }
}

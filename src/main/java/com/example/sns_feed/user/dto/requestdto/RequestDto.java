package com.example.sns_feed.user.dto.requestdto;

import lombok.Getter;

@Getter
public class RequestDto {

    private final String email;

    private final String password;

    private final String mobileNumber;

    public RequestDto(String email, String password, String mobileNumber) {
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
}

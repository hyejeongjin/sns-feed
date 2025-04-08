package com.example.sns_feed.user.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private final String profile;

    private final String userName;

    private String profile;

    private String userName;

    private String email;

    private String password;

    private String mobileNumber;

    private String brithDate;

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

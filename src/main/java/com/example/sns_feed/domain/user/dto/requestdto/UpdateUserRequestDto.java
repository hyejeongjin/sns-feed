package com.example.sns_feed.domain.user.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String profile;

    @NotBlank(message = "번호를 입력해주세요")
    private final String mobileNumber;

    public UpdateUserRequestDto(String profile, String mobileNumber) {
        this.profile = profile;
        this.mobileNumber = mobileNumber;
    }
}

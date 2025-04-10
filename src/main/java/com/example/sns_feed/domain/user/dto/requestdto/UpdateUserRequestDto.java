package com.example.sns_feed.domain.user.dto.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String profile;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "이메일 형식이 아닙니다")
    private final String email;

    @Pattern(regexp ="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$!@*()-])[A-Za-z[0-9]$!@*()-]{8,20}",
            message = "최소 8 ~ 20자 입력해주세요.")
    private final String password;

    @NotBlank(message = "번호를 입력해주세요")
    private final String mobileNumber;

    public UpdateUserRequestDto(String profile, String email, String password, String mobileNumber) {
        this.profile = profile;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
}

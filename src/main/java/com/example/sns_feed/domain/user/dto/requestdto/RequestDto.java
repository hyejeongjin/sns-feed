package com.example.sns_feed.domain.user.dto.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class RequestDto {

    private final String profile;

    @NotBlank(message = "공백을 입력했습니다.")
    @Size(min = 3, max = 10, message = "3 ~ 10자 입력해주세요.")
    private final String userName;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "이메일 형식이 아닙니다")
    private final String email;

    @Pattern(regexp ="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$!@*()-])[A-Za-z[0-9]$!@*()-]{8,20}",
            message = "최소 8 ~ 20자 입력해주세요.")
    private final String password;

    @NotBlank(message = "번호를 입력해주세요")
    private final String mobileNumber;

    @NotBlank(message = "생년월일를 입력해주세요")
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

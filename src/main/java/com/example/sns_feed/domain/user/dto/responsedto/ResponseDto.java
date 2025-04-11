package com.example.sns_feed.domain.user.dto.responsedto;

import com.example.sns_feed.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class  ResponseDto {

    private final String profile;

    private final String userName;

    private final String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public ResponseDto(User user) {
        this.profile = user.getProfile();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.updatedAt = user.getUpdatedAt();
    }

    public static ResponseDto toDto(User user) {
        return new ResponseDto(user);
    }
}

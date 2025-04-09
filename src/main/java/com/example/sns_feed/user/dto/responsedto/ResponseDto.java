package com.example.sns_feed.user.dto.responsedto;

import com.example.sns_feed.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class  ResponseDto {

    private final String email;

    private final LocalDateTime updatedAt;


    public ResponseDto(User user) {
        this.email = user.getEmail();
        this.updatedAt = user.getUpdatedAt();
    }

    public static ResponseDto toDto(User user) {
        return new ResponseDto(user);
    }
}

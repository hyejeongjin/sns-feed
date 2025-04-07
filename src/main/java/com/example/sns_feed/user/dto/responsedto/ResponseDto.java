package com.example.sns_feed.user.dto.responsedto;

import com.example.sns_feed.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto {

    private final String email;

//    private final LocalDateTime updatedAt;


    public ResponseDto(String email, LocalDateTime updatedAt) {
        this.email = email;
        updatedAt = updatedAt;
    }

//    public static ResponseDto toDto(User user) {
//        this.email = user.getEmail();
//        this.updated_at = user.getUpdatedAt;
//    }
}

package com.example.sns_feed.user.dto.responsedto;

import com.example.sns_feed.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDto {

    private final String email;

    private final LocalDateTime updatedAt;


    /*
    * 2025 04 07
    * 상태메세지 출력
    * */
//    private String message;
//    public ResponseDto( String message) {
//
//        this.message = message;
//    }

    public ResponseDto(String email, LocalDateTime updatedAt) {
        this.email = email;
        this.updatedAt = updatedAt;
    }

//    public static ResponseDto toDto(User user) {
//        this.email = user.getEmail();
//        this.updated_at = user.getUpdatedAt;
//    }
}

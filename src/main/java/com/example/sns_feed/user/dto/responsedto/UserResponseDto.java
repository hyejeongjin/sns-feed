package com.example.sns_feed.user.dto.responsedto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long id;

    public UserResponseDto(Long id) {
        this.id = id;
    }
}

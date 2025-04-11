package com.example.sns_feed.domain.user.dto.responsedto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {

    private final Long id;

    public UserResponseDto(Long id) {
        this.id = id;
    }
}

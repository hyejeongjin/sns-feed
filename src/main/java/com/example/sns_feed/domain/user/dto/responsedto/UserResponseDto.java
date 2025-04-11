package com.example.sns_feed.domain.user.dto.responsedto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserResponseDto implements Serializable {

    private final Long id;

    public UserResponseDto(Long id) {
        this.id = id;
    }
}

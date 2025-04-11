package com.example.sns_feed.domain.follow.dto.responsedto;

import com.example.sns_feed.domain.user.entity.User;
import lombok.Getter;

@Getter
public class MyFriendsResponseDto {
    private final Long userId;
    private final String username;

    public MyFriendsResponseDto(User user) {
        this.userId = user.getId();
        this.username = user.getUserName();
    }
}
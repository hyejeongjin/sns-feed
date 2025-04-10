package com.example.sns_feed.domain.follow.dto;

import com.example.sns_feed.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MyFriendsDto {
    private final Long userId;
    private final String username;

    public MyFriendsDto(User user) {
        this.userId = user.getId();
        this.username = user.getUserName();
    }
}
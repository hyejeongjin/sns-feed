package com.example.sns_feed.domain.follow.dto;

import com.example.sns_feed.domain.follow.entity.Follow;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FollowRequestListDto {
    private final Long followId;
    private final Long userId;
    private final String username;
    private final LocalDateTime followRequestTime;

    public FollowRequestListDto(Follow follow){
        this.followId = follow.getFollowId();
        this.userId = follow.getSender().getId();
        this.username = follow.getSender().getUserName();
        this.followRequestTime = follow.getCreateAt();
    }
}

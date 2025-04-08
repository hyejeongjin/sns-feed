package com.example.sns_feed.follow.dto;

import lombok.Getter;

@Getter
public class FollowRequestDto {
    // 팔로우 당하는 대상 ID
    private Long followingId;
}

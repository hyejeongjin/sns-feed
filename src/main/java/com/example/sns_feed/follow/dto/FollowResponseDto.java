package com.example.sns_feed.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowResponseDto {
    private String message;
    private Long followerId;
    private Long followedId;
}

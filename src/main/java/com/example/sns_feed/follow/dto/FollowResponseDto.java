package com.example.sns_feed.follow.dto;

import com.example.sns_feed.follow.entity.Follow;
import com.example.sns_feed.follow.enums.FollowStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowResponseDto {
    private String message;
    private Long followerId;
    private Long followedId;
}
package com.example.sns_feed.domain.follow.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowRequestDto {
    // 팔로우 당하는 대상 ID
    private Long receiverId;
}

package com.example.sns_feed.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowListDto {
    private Long userId;
    private String email;
}

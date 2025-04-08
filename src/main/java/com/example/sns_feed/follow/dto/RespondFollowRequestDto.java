package com.example.sns_feed.follow.dto;

import lombok.Getter;

@Getter
public class RespondFollowRequestDto {
    private Long senderId;
    private Boolean accept;
    private Boolean reject;
}

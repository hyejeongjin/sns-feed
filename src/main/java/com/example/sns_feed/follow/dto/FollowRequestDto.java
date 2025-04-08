package com.example.sns_feed.follow.dto;

import com.example.sns_feed.follow.enums.FollowStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class FollowRequestDto {
    // 팔로우 당하는 대상 ID
    private Long receiverId;
}

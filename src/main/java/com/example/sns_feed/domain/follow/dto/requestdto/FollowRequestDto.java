package com.example.sns_feed.domain.follow.dto.requestdto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowRequestDto {

    // 팔로우 당하는 대상 ID
    private Long receiverId;
}

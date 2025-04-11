package com.example.sns_feed.domain.follow.dto.responsedto;


import com.example.sns_feed.domain.follow.entity.Follow;
import com.example.sns_feed.domain.follow.enums.FollowStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FollowResponseDto {
    private final Long senderId;
    private final Long receiverId;
    private final FollowStatus status;

    public FollowResponseDto(Follow follow) {
        this.senderId = follow.getSender().getId();
        this.receiverId = follow.getReceiver().getId();
        this.status = follow.getFollowStatus();
    }
}
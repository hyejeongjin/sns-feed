package com.example.sns_feed.domain.follow.dto.requestdto;

import com.example.sns_feed.domain.follow.entity.Follow;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FollowRequestListDto {

    private final Long followId;

    private final Long userId;

    private final String username;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime followRequestTime;

    public FollowRequestListDto(Follow follow){
        this.followId = follow.getFollowId();
        this.userId = follow.getSender().getId();
        this.username = follow.getSender().getUserName();
        this.followRequestTime = follow.getCreatedAt();
    }
}

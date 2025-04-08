package com.example.sns_feed.follow.controller;

import com.example.sns_feed.follow.dto.FollowRequestDto;
import com.example.sns_feed.follow.dto.FollowResponseDto;
import com.example.sns_feed.follow.dto.RespondFollowRequestDto;
import com.example.sns_feed.follow.service.FollowService;
import com.example.sns_feed.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 팔로우 요청
    @PostMapping
    public FollowResponseDto follow(User user, @RequestBody FollowRequestDto requestDto) throws IllegalAccessException {
        return followService.followRequest(requestDto, user);
    }

    // 팔로우 수락/거절
    @PatchMapping("/{id}/accept")
    public void respondFollowAccept(@PathVariable Long id, @RequestBody RespondFollowRequestDto request) throws IllegalAccessException {
        followService.respondFollowRequest(id, request);
    }
}

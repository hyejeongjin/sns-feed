package com.example.sns_feed.follow.controller;

import com.example.sns_feed.common.Const;
import com.example.sns_feed.follow.dto.FollowListDto;
import com.example.sns_feed.follow.dto.FollowRequestDto;
import com.example.sns_feed.follow.dto.FollowResponseDto;
import com.example.sns_feed.follow.dto.RespondFollowRequestDto;
import com.example.sns_feed.follow.service.FollowService;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.dto.responsedto.UserResponseDto;
import com.example.sns_feed.user.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 팔로우 요청
    @PostMapping
    public FollowResponseDto follow(
            HttpSession session,
            @RequestBody FollowRequestDto requestDto) throws IllegalAccessException {
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new IllegalAccessException("로그인 필요!");
        }
        return followService.followRequest(requestDto, loginUser.getId()); // id 기반 전달
    }

    // 팔로우 수락/거절
    @PatchMapping("/{id}/accept")
    public void respondFollowAccept(@PathVariable Long id,
                                    @RequestBody RespondFollowRequestDto request,
                                    HttpSession session) throws IllegalAccessException {
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new IllegalAccessException("로그인 필요!");
        }
        followService.respondFollowRequest(id, request, loginUser.getId());
    }

    // 친구 목록
//    @GetMapping
//    public FollowListDto friends(
//
//    )
}

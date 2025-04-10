package com.example.sns_feed.domain.follow.controller;


import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.follow.dto.*;
import com.example.sns_feed.domain.follow.service.FollowService;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            throw new IllegalAccessException("로그인 필요합니다. ");
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
            throw new IllegalAccessException("로그인 필요합니다. ");
        }
        followService.respondFollowRequest(id, request, loginUser.getId());
    }

     // 나랑 팔로우한 유저 목록
    @GetMapping
    public ResponseEntity<List<MyFriendsDto>> friends(HttpSession session) throws IllegalAccessException{
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new IllegalAccessException("로그인이 필요합니다.");
        }

        return ResponseEntity.ok(followService.getMyfriends(loginUser.getId()));
    }

    // 나에게 요청 보낸 유저 목록
    @GetMapping("/requests")
    public ResponseEntity<List<FollowRequestListDto>> pendingRequests(HttpSession session) throws IllegalAccessException{
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new IllegalAccessException("로그인이 필요합니다.");
        }

        List<FollowRequestListDto> pendingFollowRequest = followService.getPendingFollowRequests(loginUser.getId());
        return ResponseEntity.ok(pendingFollowRequest);
    }

    // 친구 삭제 요청
    /**
     * @.build() : // 바디 없이 응답 완료
     * */
    @DeleteMapping("/unfollows")
    public ResponseEntity<Void> deleteFriends(
            HttpSession session,
            @RequestBody DeleteRequestDto requestDto) throws IllegalAccessException {

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new IllegalAccessException("로그인이 필요합니다.");
        }

        followService.receiverIdDelete(loginUser.getId(), requestDto.getReceiverId());
        return ResponseEntity.ok().build();

    }

}

package com.example.sns_feed.domain.follow.controller;


import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.follow.dto.*;
import com.example.sns_feed.domain.follow.service.FollowService;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;


    /**
     * 팔로우 요청
     *
     * @param requestDto  팔로우 요청 대상 ID를 담은 DTO
     * @return 팔로우 성공 응답 DTO
     */
    @PostMapping
    public ResponseEntity<FollowResponseDto> follow(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser,
            @RequestBody FollowRequestDto requestDto) {

        FollowResponseDto responseDto = followService.followRequest(requestDto, loginUser.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 팔로우 수락/거절
     *
     * @param id         팔로우 요청 ID
     * @param request    수락 또는 거절 상태를 담은 DTO
     */
    @PatchMapping("/{id}/accept")
    public ResponseEntity<Void>  respondFollowAccept(@PathVariable Long id,
                                    @RequestBody RespondFollowRequestDto request,
                                    @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser) {

        followService.respondFollowRequest(id, request, loginUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 나랑 팔로우한 유저 목록
     *
     * @return 친구 목록 DTO 리스트
     */
    @GetMapping
    public ResponseEntity<List<MyFriendsDto>> friends(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser) {

        List<MyFriendsDto> myFriends = followService.getMyFriends(loginUser.getId());
        return new ResponseEntity<>(myFriends,HttpStatus.OK);
    }

    /**
     * 나에게 팔로우 요청을 보낸 유저 목록 조회
     *
     * @return 팔로우 요청 목록 DTO 리스트
     */
    @GetMapping("/requests")
    public ResponseEntity<List<FollowRequestListDto>> pendingRequests(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser) {

        List<FollowRequestListDto> pendingFollowRequest = followService.getPendingFollowRequests(loginUser.getId());
        return new ResponseEntity<>(pendingFollowRequest, HttpStatus.OK);
    }

    /**
     * 팔로우 삭제 요청 (친구 삭제)
     *
     * @param requestDto 삭제할 대상 유저 ID를 담은 DTO
     * @return HTTP 200 OK 응답
     */
    @DeleteMapping("/unfollows")
    public ResponseEntity<Void> deleteFriends(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser,
            @RequestBody DeleteRequestDto requestDto) {

        followService.receiverIdDelete(loginUser.getId(), requestDto.getReceiverId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

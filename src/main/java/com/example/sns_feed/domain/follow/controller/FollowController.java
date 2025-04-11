package com.example.sns_feed.domain.follow.controller;


import com.example.sns_feed.common.Const;
import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.follow.UnauthorizedException;
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


    /**
     * 팔로우 요청
     *
     * @param session     세션에서 로그인 유저 정보 조회
     * @param requestDto  팔로우 요청 대상 ID를 담은 DTO
     * @return 팔로우 성공 응답 DTO
     * @throws CustomException 인증 실패 시 예외 발생
     */
    @PostMapping
    public FollowResponseDto follow(
            HttpSession session,
            @RequestBody FollowRequestDto requestDto) throws CustomException {
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new UnauthorizedException();
        }
        return followService.followRequest(requestDto, loginUser.getId()); // id 기반 전달
    }

    /**
     * 팔로우 수락/거절
     *
     * @param id         팔로우 요청 ID
     * @param request    수락 또는 거절 상태를 담은 DTO
     * @param session    세션에서 로그인 유저 정보 조회
     * @throws CustomException 인증 실패 시 예외 발생
     */
    @PatchMapping("/{id}/accept")
    public void respondFollowAccept(@PathVariable Long id,
                                    @RequestBody RespondFollowRequestDto request,
                                    HttpSession session) throws CustomException {
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new UnauthorizedException();
        }
        followService.respondFollowRequest(id, request, loginUser.getId());
    }

    /**
     * 나랑 팔로우한 유저 목록
     *
     * @param session 세션에서 로그인 유저 정보 조회
     * @return 친구 목록 DTO 리스트
     * @throws CustomException 인증 실패 시 예외 발생
     */
    @GetMapping
    public ResponseEntity<List<MyFriendsDto>> friends(HttpSession session) throws CustomException{
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new UnauthorizedException();
        }

        return ResponseEntity.ok(followService.getMyFriends(loginUser.getId()));
    }

    /**
     * 나에게 팔로우 요청을 보낸 유저 목록 조회
     *
     * @param session 세션에서 로그인 유저 정보 조회
     * @return 팔로우 요청 목록 DTO 리스트
     * @throws CustomException 인증 실패 시 예외 발생
     */
    @GetMapping("/requests")
    public ResponseEntity<List<FollowRequestListDto>> pendingRequests(HttpSession session) throws CustomException{
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new UnauthorizedException();
        }

        List<FollowRequestListDto> pendingFollowRequest = followService.getPendingFollowRequests(loginUser.getId());
        return ResponseEntity.ok(pendingFollowRequest);
    }

    /**
     * 팔로우 삭제 요청 (친구 삭제)
     *
     * @param session    세션에서 로그인 유저 정보 조회
     * @param requestDto 삭제할 대상 유저 ID를 담은 DTO
     * @return HTTP 200 OK 응답
     * @throws CustomException 인증 실패 시 예외 발생
     */
    @DeleteMapping("/unfollows")
    public ResponseEntity<Void> deleteFriends(
            HttpSession session,
            @RequestBody DeleteRequestDto requestDto) throws CustomException {

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null){
            throw new UnauthorizedException();
        }

        followService.receiverIdDelete(loginUser.getId(), requestDto.getReceiverId());
        return ResponseEntity.ok().build();

    }

}

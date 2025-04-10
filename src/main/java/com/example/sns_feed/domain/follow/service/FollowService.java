package com.example.sns_feed.domain.follow.service;


import com.example.sns_feed.domain.follow.dto.*;
import com.example.sns_feed.domain.follow.entity.Follow;
import com.example.sns_feed.domain.follow.enums.FollowStatus;
import com.example.sns_feed.domain.follow.repository.FollowRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;


    @Transactional
    public FollowResponseDto followRequest(FollowRequestDto requestDto, Long senderId) throws IllegalAccessException {
        User user = userRepository.findUserByIdOrElseThrow(senderId);
        User receiver = userRepository.findUserByIdOrElseThrow(requestDto.getReceiverId());

        // 자기 자신한테 신청 못하도록
        if (receiver.getId().equals(user.getId())){
            throw new IllegalAccessException("자신에게는 요청이 안됩니다.");
        }

        // 팔로우 신청 했던 사람한테 못하도록
        if (followRepository.pendingRequest(user, receiver)){
            throw new IllegalAccessException("이미 요청 보낸 유저 입니다.");
        }
        // 이미 친구인 상태인 유저한테 못하도록
        if (followRepository.alreadyFollowing(user, receiver)) {
            throw new IllegalAccessException("이미 친구입니다.");
        }


        FollowStatus status = FollowStatus.PENDING;

        Follow follow = new Follow(user, receiver, status);

        Follow savedFollow = followRepository.save(follow);

        return new FollowResponseDto(savedFollow);
    }

    //팔로우 수락, 거절
    @Transactional
    public void respondFollowRequest(Long followId, RespondFollowRequestDto request, Long loginUserID) throws IllegalAccessException {
        Follow follow = followRepository.findById(followId).orElseThrow(()-> new IllegalArgumentException("팔로우 요청 없음"));

        // 수락, 거절은 receiver(요청 받은자)만 가능
        if(!follow.getReceiver().getId().equals(loginUserID)){
            throw new IllegalAccessException("권한 요청이 없습니다.");
        }


        if (!follow.getFollowStatus().equals(FollowStatus.PENDING)){
            throw new IllegalAccessException("이미 처리된 요청입니다.");
        }


        if(request.getAccept()){
            // 요청 수락
            follow.updateStatus(FollowStatus.ACCEPTED);
        } else if (request.getReject()) {
            // 요청 거절
            follow.updateStatus(FollowStatus.REJECTED);
        } else {
            throw new IllegalAccessException("잘못된 요청 형식입니다.");
        }

    }


    // 친구 목록
    public List<MyFriendsDto> getMyfriends(Long senderId) {
        List<User> userList = followRepository.findAcceptedFollowingsBySenderId(senderId);
        return userList.stream().map(MyFriendsDto::new).toList();
    }

    // 나를 팔로우 요청보낸 사용자들을 조회
    public List<FollowRequestListDto> getPendingFollowRequests(Long receiverId){
        List<Follow> followRequestList = followRepository.findPendingSenderByReceiverId(receiverId);
        return followRequestList.stream().map(FollowRequestListDto::new).toList();
    }

    // 친구 삭제
    @Transactional
    public void receiverIdDelete(Long senderId, Long receiverId) throws IllegalAccessException {
        boolean following = followRepository.alreadyFollowing(
                userRepository.findUserByIdOrElseThrow(senderId),
                userRepository.findUserByIdOrElseThrow(receiverId)
        );

        if (!following){
            throw new IllegalAccessException("친구 관계가 아닙니다.");
        }

        followRepository.deleteFollow(senderId,receiverId);
    }

}

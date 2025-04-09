package com.example.sns_feed.follow.service;

import com.example.sns_feed.follow.dto.FollowRequestDto;
import com.example.sns_feed.follow.dto.FollowResponseDto;
import com.example.sns_feed.follow.dto.RespondFollowRequestDto;
import com.example.sns_feed.follow.entity.Follow;
import com.example.sns_feed.follow.enums.FollowStatus;
import com.example.sns_feed.follow.repository.FollowRepository;
import com.example.sns_feed.user.entity.User;
import com.example.sns_feed.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            throw new IllegalAccessException("나 한테 친구신청 못함");
        }

        // 팔로우 신청 했던 사람한테 못하도록
        if (followRepository.pendingRequest(user, receiver)){
            throw new IllegalAccessException("이미 요청 보냄");
        }

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
            // follow 수 늘리는 코드
        } else if (request.getReject()) {
            // 요청 거절
            follow.updateStatus(FollowStatus.REJECTED);
        } else {
            throw new IllegalAccessException("잘못된 요청 형식입니다.");
        }

    }



}

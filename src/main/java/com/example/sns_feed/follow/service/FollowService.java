package com.example.sns_feed.follow.service;

import com.example.sns_feed.follow.dto.FollowRequestDto;
import com.example.sns_feed.follow.dto.FollowResponseDto;
import com.example.sns_feed.follow.entity.Follow;
import com.example.sns_feed.follow.enums.FollowStatus;
import com.example.sns_feed.follow.repository.FollowRepository;
import com.example.sns_feed.user.entity.User;
import com.example.sns_feed.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;


    @Transactional
    public FollowResponseDto followRequest(FollowRequestDto requestDto, User sender) throws IllegalAccessException {
        User user = userRepository.findUserByIdOrElseThrow(sender.getId());
        User receiver = userRepository.findUserByIdOrElseThrow(requestDto.getReceiverId());

        // 자기 자신한테 신청 못하도록
        if (receiver.getId().equals(user.getId())){
            throw new IllegalAccessException("자기자신 친구신청 못함");
        }

        // 팔로우 신청 했던 사람한테 못하도록
        if (followRepository.pendingRequest(user, receiver)){
            throw new IllegalAccessException("이미 요청 보냄");
        }

        FollowStatus status = FollowStatus.PENDING;

        Follow follow = new Follow(user, receiver, status);

        Follow savedFollow = followRepository.save(follow);

        return new FollowResponseDto(savedFollow);
    }

//    @Transactional
//    public FollowResponseDto follow(Long followUserId, FollowRequestDto requestDto) {
//        User followUser = userRepository.findById(followUserId).orElseThrow();
//        User followingUser = userRepository.findById(requestDto.getFollowingId()).orElseThrow();
//
//        if (followRepository.findByFollowUserAndFollowingUser(followUser, followingUser).isPresent()) {
//            throw new IllegalArgumentException("이미 팔로우 신청을 했습니다.");
//        }
//
//        Follow follow = new Follow(followUser, followingUser, FollowStatus.PENDING);
//        followRepository.save(follow);
//
//        return FollowResponseDto.builder()
//                .message("팔로우 성공")
//                .followerId(followUser.getId())
//                .followedId(followingUser.getId())
//                .build();
//    }



}

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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public FollowResponseDto follow(Long followUserId, FollowRequestDto requestDto) {
        User followUser = userRepository.findById(followUserId).orElseThrow();
        User followingUser = userRepository.findById(requestDto.getFollowingId()).orElseThrow();

        if (followRepository.findByFollowUserAndFollowingUser(followUser, followingUser).isPresent()) {
            throw new IllegalArgumentException("이미 팔로우 신청을 했습니다.");
        }

        Follow follow = new Follow(followUser, followingUser, FollowStatus.PENDING);
        followRepository.save(follow);

        return FollowResponseDto.builder()
                .message("팔로우 성공")
                .followerId(followUser.getId())
                .followedId(followingUser.getId())
                .build();
    }






}

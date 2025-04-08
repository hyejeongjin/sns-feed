package com.example.sns_feed.follow.service;

import com.example.sns_feed.follow.repository.FollowRepository;
import com.example.sns_feed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

}

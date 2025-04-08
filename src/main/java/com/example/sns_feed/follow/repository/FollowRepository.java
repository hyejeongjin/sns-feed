package com.example.sns_feed.follow.repository;

import com.example.sns_feed.follow.entity.Follow;
import com.example.sns_feed.follow.enums.FollowStatus;
import com.example.sns_feed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowUserAndFollowingUser(User followUser, User followingUser);

    List<Follow> findByFollowingUserAndFollowStatus(User user, FollowStatus status); // 나를 팔로우하는 사람들
    List<Follow> findByFollowUserAndFollowStatus(User user, FollowStatus status);    // 내가 팔로우하는 사람들
}
package com.example.sns_feed.follow.repository;

import com.example.sns_feed.follow.entity.Follow;
import com.example.sns_feed.follow.enums.FollowStatus;
import com.example.sns_feed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {


    // 직관적으로 볼수 있게 할려고
    @Query("select count(f) > 0 from Follow f where f.sender = :sender and f.receiver=:receiver and f.followStatus = 'PENDING'")
    boolean pendingRequest(@Param("sender") User sender, @Param("receiver") User receiver);

    @Query("select f from Follow f where f.sender.id=:senderId and f.receiver.id =:receiverId")
    Optional<Follow> findBySenderAndReceiver(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

    // 쿼리 메서드는 너무 헷갈림
    Optional<Follow> findByFollowUserAndFollowingUser(User followUser, User followingUser);
    List<Follow> findByFollowingUserAndFollowStatus(User user, FollowStatus status); // 나를 팔로우하는 사람들
    List<Follow> findByFollowUserAndFollowStatus(User user, FollowStatus status);    // 내가 팔로우하는 사람들
}
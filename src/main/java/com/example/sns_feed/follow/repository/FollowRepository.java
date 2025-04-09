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

    @Query("SELECT count(f) > 0 FROM Follow f WHERE f.sender = :sender AND f.receiver = :receiver AND f.followStatus = 'ACCEPTED'")
    boolean alreadyFollowing(@Param("sender") User sender, @Param("receiver") User receiver);


    @Query("select f from Follow f where f.sender.id=:senderId and f.receiver.id =:receiverId")
    Optional<Follow> findBySenderAndReceiver(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

}
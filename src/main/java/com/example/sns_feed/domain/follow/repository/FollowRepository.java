package com.example.sns_feed.domain.follow.repository;

import com.example.sns_feed.domain.follow.entity.Follow;
import com.example.sns_feed.domain.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {


    // 직관적으로 볼수 있게 할려고
    @Query("select count(f) > 0 from Follow f where f.sender = :sender and f.receiver = :receiver and f.followStatus = 'PENDING'")
    boolean pendingRequest(@Param("sender") User sender, @Param("receiver") User receiver);

    @Query("select count(f) > 0 from Follow f where f.sender = :sender and f.receiver = :receiver and f.followStatus = 'ACCEPTED'")
    boolean alreadyFollowing(@Param("sender") User sender, @Param("receiver") User receiver);

    @Query("select f.receiver from Follow f where f.sender.id = :senderId and f.followStatus = 'ACCEPTED'")
    List<User> findAcceptedFollowingsBySenderId(@Param("senderId") Long senderId);

    @Query("select f from Follow f where f.receiver.id = :receiverId and f.followStatus = 'PENDING'")
    List<Follow> findPendingSenderByReceiverId(@Param("receiverId") Long receiverId);

    @Transactional
    @Modifying
    @Query("delete from Follow f where f.sender.id = :senderId and f.receiver.id = :receiverId and f.followStatus = 'ACCEPTED'")
    void deleteFollow(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
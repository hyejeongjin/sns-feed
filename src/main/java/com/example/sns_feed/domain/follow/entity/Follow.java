package com.example.sns_feed.domain.follow.entity;

import com.example.sns_feed.domain.follow.enums.FollowStatus;
import com.example.sns_feed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="follows")
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_user_id", nullable = false)
    private User followingUser; // 팔로우 당하는 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_user_id", nullable = false)
    private User followUser;  // 팔로우 신청한 유저


    @Column(name = "follow_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FollowStatus followStatus;

}

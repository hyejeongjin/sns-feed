package com.example.sns_feed.follow.entity;

import com.example.sns_feed.common.entity.BaseEntity;
import com.example.sns_feed.follow.enums.FollowStatus;
import com.example.sns_feed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
        name = "follows",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follow_user_id", "following_user_id"})
)
@NoArgsConstructor
public class Follow extends BaseEntity {

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

    public Follow(User followUser, User followingUser, FollowStatus followStatus) {
        this.followUser = followUser;
        this.followingUser = followingUser;
        this.followStatus = followStatus;
    }
}

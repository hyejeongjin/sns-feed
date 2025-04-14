package com.example.sns_feed.domain.follow.entity;


import com.example.sns_feed.common.entity.BaseEntity;
import com.example.sns_feed.domain.follow.enums.FollowStatus;
import com.example.sns_feed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "follows")
@NoArgsConstructor
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", nullable = false)
    private User sender; // 팔로우 신청 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver", nullable = false)
    private User receiver;  // 팔로우 받는 유저

    @Column(name = "follow_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FollowStatus followStatus;

    public Follow(User sender, User receiver, FollowStatus followStatus) {
        this.sender = sender;
        this.receiver = receiver;
        this.followStatus = followStatus;
    }

    public void updateStatus(FollowStatus status){
        this.followStatus = status;
    }
}

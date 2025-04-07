package com.example.sns_feed.follow.entity;

import com.example.sns_feed.follow.enums.FollowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="follow")
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "following_user_id", nullable = false)
//    private User followingUser;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "follow_user_id", nullable = false)
//    private User followingUser;

    @Column(name = "follow_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FollowStatus followStatus;

}

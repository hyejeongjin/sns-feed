package com.example.sns_feed.user.entity;

import com.example.sns_feed.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    //test
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profile;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String brithDate;

    private LocalDateTime deletedAt;


    public User() {

    }

    public User(String email, String password, String mobileNumber, String brithDate, LocalDateTime deletedAt) {
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.brithDate = brithDate;
        this.deletedAt = deletedAt;
    }
}

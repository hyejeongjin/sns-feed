package com.example.sns_feed.user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user")
public class User {

    //test
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

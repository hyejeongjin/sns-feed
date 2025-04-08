package com.example.sns_feed.user.entity;

import com.example.sns_feed.common.entity.BaseEntity;
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
    private String birthDate;

    private LocalDateTime deletedAt;


    public User() {

    }
   /*
    * 2025 04 07
    * 김형진
    *  생성자 업데이트
    *  */
   public User(String email, String password, String mobileNumber, String birthDate){
       this.email = email;
       this.password = password;
       this.mobileNumber = mobileNumber;
       this.birthDate = birthDate;
   }
    public User(String email, String password, String mobileNumber, String birthDate, LocalDateTime deletedAt) {
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.birthDate = birthDate;
        this.deletedAt = deletedAt;
    }
}

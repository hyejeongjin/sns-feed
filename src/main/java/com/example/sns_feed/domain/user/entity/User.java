package com.example.sns_feed.domain.user.entity;

import com.example.sns_feed.common.entity.BaseEntity;
import com.example.sns_feed.domain.user.dto.requestdto.RequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
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

    @Column
    private String deletedAt;

    public User() {

    }
    /*
    * 2025 04 07
    * 김형진
    *  생성자 업데이트
    *  */
   public User(RequestDto dto){
       this.profile = dto.getProfile();
       this.userName = dto.getUserName();
       this.email = dto.getEmail();
       this.password = dto.getPassword();
       this.mobileNumber = dto.getMobileNumber();
       this.birthDate = dto.getBirthDate();
   }
    /**
     * 2025 04 08
     * 양재호
     * updateUser를 위한 메서드
     */
    public void updateUser(RequestDto dto) {
        this.mobileNumber = dto.getMobileNumber();
    }
    public void updatePassword(String password){
        this.password = password;
    }
    public void updatedeletedAt(String deletedAt){
        this.deletedAt = deletedAt;
    }
}

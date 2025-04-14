package com.example.sns_feed.domain.user.repository;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByUserName(String userName);

    default User findByEmailOrThrow(String Email) {
        return findByEmail(Email).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, "유저 정보를 찾을 수 없습니다."));
    }

    default User findUserByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, "유저 정보를 찾을 수 없습니다."));
    }

    default List<User> findUserByUserName(String userName) {
        return findByUserName(userName);
    }

}

package com.example.sns_feed.user.repository;

import com.example.sns_feed.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.RuntimeErrorException;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByEmailOrThrow(String Email){
        return findByEmail(Email).orElseThrow(()-> new EntityNotFoundException("요청한 정보를 찾을 수 없습니다."));
    }
    default User findByEmailOrThrow(String Email, String password){
        return findByEmail(Email).filter(User-> User.getPassword().equals(password))
                .orElseThrow(() -> new EntityNotFoundException("요청한 정보를 찾을 수 없습니다."));
    }




}

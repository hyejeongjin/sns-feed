package com.example.sns_feed.user.repository;

import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> findByUserName(String userName);


    default User findByEmailOrThrow(String Email){
        return findByEmail(Email).orElseThrow(()-> new EntityNotFoundException("요청한 정보를 찾을 수 없습니다."));
    }

    default User findByEmailOrThrow(String Email, String password){
        return findByEmail(Email).filter(User-> User.getPassword().equals(password))
                .orElseThrow(() -> new EntityNotFoundException("요청한 정보를 찾을 수 없습니다."));
    }

    default User findUserByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    default List<User> findUserByUserName(String userName) {
        return findByUserName(userName);
    }
}

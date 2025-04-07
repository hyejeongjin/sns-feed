package com.example.sns_feed.user.repository;

import com.example.sns_feed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

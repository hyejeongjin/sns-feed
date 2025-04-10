package com.example.sns_feed.redis;

import com.example.sns_feed.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<User, Long> {

}
package com.example.sns_feed.domain.redis.repository;

import com.example.sns_feed.domain.redis.entitiy.RedisItem;
import com.example.sns_feed.domain.user.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RedisRepository extends CrudRepository<RedisItem, String>  {

}

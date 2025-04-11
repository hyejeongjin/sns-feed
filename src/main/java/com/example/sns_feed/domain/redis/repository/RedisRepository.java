package com.example.sns_feed.domain.redis.repository;

import com.example.sns_feed.domain.redis.entitiy.RedisItem;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RedisItem, String>  {

}

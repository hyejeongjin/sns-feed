package com.example.sns_feed.domain.redis.service;

import com.example.sns_feed.domain.redis.entitiy.RedisItem;

public interface RedisService {

   void setRedisData(String email, String code);

   String getRedisData(String code);
}

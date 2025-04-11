package com.example.sns_feed.domain.redis.service;

public interface RedisService {

   void setRedisData(String email, String code);

   void resetCode(String email);

   String getRedisData(String code);
}

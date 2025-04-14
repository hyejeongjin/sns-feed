package com.example.sns_feed.domain.redis.service;

public interface RedisService {

   void setRedisData(String email, String code);

   void deleteVerificationCode(String email);

   String getRedisData(String code);
}

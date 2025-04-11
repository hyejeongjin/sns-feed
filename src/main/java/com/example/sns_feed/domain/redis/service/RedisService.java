package com.example.sns_feed.domain.redis.service;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.domain.redis.entitiy.RedisItem;

public interface RedisService {

   void setRedisData(String email, String code);

   void resetCode(String email);
   String getRedisData(String code);

}

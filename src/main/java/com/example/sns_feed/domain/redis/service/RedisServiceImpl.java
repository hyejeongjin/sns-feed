package com.example.sns_feed.domain.redis.service;

import com.example.sns_feed.domain.redis.entitiy.RedisItem;
import com.example.sns_feed.domain.redis.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{
    @Autowired
    private final RedisRepository redisRepository;
    private final StringRedisTemplate redisTemplate;

    @Override
    public void setRedisData(String email, String code) {
        RedisItem redisItem = new RedisItem(email, code);
        redisRepository.save(redisItem);
    }

    @Override
    public String getRedisData(String code) {
       RedisItem redisItem =  redisRepository.findByCERTCodeOrThrow(code);
       return  redisItem.getEmail();
    }

}

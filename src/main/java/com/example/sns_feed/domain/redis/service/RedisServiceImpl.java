package com.example.sns_feed.domain.redis.service;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private final StringRedisTemplate redisTemplate;

    @Override
    public void setRedisData(String email, String code) {
        //String redisKey = "emailCode:" + email;
        redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
    }

    @Override
    public void deleteVerificationCode(String email) {
        redisTemplate.delete(email);
    }

    @Override
    public String getRedisData(String email) {
        String code = redisTemplate.opsForValue().get(email);
        if(code == null){
            throw new CustomException(ErrorCode.SESSION_NOT_FOUND);
        }
        return code;
    }

}

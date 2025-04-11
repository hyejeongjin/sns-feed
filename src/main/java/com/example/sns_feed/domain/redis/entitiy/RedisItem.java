package com.example.sns_feed.domain.redis.entitiy;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("RedisItem")
public class RedisItem {
    @Id
    private String id;   // redis는 key- value 쌍이기 떄문에 member:<Id> 이런식으로 들어감
    private String email;
    private String cERTCode; // 인증 코드

    public RedisItem(String email, String cERTCode) {
        this.email = email;
        this.cERTCode = cERTCode;
    }
}

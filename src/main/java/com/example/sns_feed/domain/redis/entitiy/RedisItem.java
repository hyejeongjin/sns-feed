package com.example.sns_feed.domain.redis.entitiy;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@RedisHash("RedisItem")
public class RedisItem {
    @Id
    private String id;   // redis는 key- value 쌍이기 떄문에 member:<Id> 이런식으로 들어감
    private String email;
    private String certCode; // 인증 코드
    @TimeToLive
    private Long expiration = 300L; // 예: 5분
    public RedisItem(String email, String certCode) {
        this.email = email;
        this.certCode = certCode;
    }


}

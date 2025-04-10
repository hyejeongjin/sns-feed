package com.example.sns_feed.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    // findUser
    @GetMapping("/redis")
    public ResponseEntity<String> temp(@PathVariable Long id) {

        redisService.temp(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

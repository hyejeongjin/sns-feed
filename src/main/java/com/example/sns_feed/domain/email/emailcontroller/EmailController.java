package com.example.sns_feed.domain.email.emailcontroller;

import com.example.sns_feed.domain.email.dto.EmailRequestDto;
import com.example.sns_feed.domain.email.emailservice.EmailService;
import com.example.sns_feed.domain.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final RedisService redisService;
    private int number;

    @PostMapping("/sendMail")
    public Map<String, Object> mailSend(@RequestBody EmailRequestDto emailDto){
        HashMap<String , Object> map = new HashMap<>();

         try{
             number = emailService.sendMail(emailDto.getEmail());
             String num = String.valueOf(number);
             map.put("number", num);
             redisService.setRedisData(emailDto.getEmail(), num);
         }
         catch (Exception ex){
             map.put("Success:", Boolean.FALSE);
             map.put("Error:", ex.getMessage());
         }

        return Map.of("전송 성공", HttpStatus.OK);
    }
}

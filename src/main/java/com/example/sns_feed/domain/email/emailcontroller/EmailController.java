package com.example.sns_feed.domain.email.emailcontroller;

import com.example.sns_feed.domain.email.dto.EmailRequestDto;
import com.example.sns_feed.domain.email.emailservice.EmailService;
import com.example.sns_feed.domain.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final RedisService redisService;
    private int number;

    @PostMapping("/sendMail")
    public ResponseEntity<String> mailSend(@RequestBody EmailRequestDto emailDto){
        HashMap<String , Object> map = new HashMap<>();

         try{
             number = emailService.sendMail(emailDto.getEmail());
             String num = String.valueOf(number);
             map.put("number", num);
             redisService.setRedisData(emailDto.getEmail(), num);
             return new ResponseEntity<>("이메일을 확인해주세요", HttpStatus.OK);
         }
         catch (Exception ex){
             map.put("Success:", Boolean.FALSE);
             map.put("Error:", ex.getMessage());
             return new ResponseEntity<>("인증코드 발급이 실패하였습니다.", HttpStatus.BAD_REQUEST);
         }
    }
}

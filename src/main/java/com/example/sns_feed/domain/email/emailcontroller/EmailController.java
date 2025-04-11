package com.example.sns_feed.domain.email.emailcontroller;

import com.example.sns_feed.domain.email.dto.EmailRequestDto;
import com.example.sns_feed.domain.email.emailservice.EmailService;
import com.example.sns_feed.domain.email.entity.EmailMessage;
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
    private int number;
    @PostMapping("/mail")
    public Map<String, Object> mailSend(@RequestBody EmailRequestDto EmailDto){
        HashMap<String , Object> map = new HashMap<>();

         try{
             number = emailService.sendMail(EmailDto.getEmail());
             String num = String.valueOf(number);
             map.put("number", number);
         }
         catch (Exception ex){
             map.put("Success:", Boolean.FALSE);
             map.put("Error:", ex.getMessage());
         }
        return Map.of("전송 성공", HttpStatus.OK);
    }
}

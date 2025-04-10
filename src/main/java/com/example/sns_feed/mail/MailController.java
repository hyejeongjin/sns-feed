package com.example.sns_feed.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private int number; // 이메일 인증 숫자를 저장하는 변수

    // 인증 이메일 전송
    @PostMapping("/mailSend")
    public HashMap<String, Object> mailSend(@RequestBody MailRequestDto dto) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            number = mailService.sendMail(dto.getMail());
            String num = String.valueOf(number);

            map.put("success", Boolean.TRUE);
            map.put("number", num);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    // 인증번호 일치여부 확인
    @GetMapping("/mailCheck")
    public ResponseEntity<?> mailCheck(@RequestParam String userNumber) {

        // 번호 검증
        boolean isMatch = userNumber.equals(String.valueOf(number));

        // 맞다면 OK
        return ResponseEntity.ok(isMatch);
    }
}

package com.example.sns_feed.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail= "jaeho9229@gmail.com";
    private static int number;

    // 랜덤으로 숫자 생성
    public static void createNumber() {
        number = (int)(Math.random() * (90000)) + 100000; //(int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public MimeMessage CreateMail(String mail) {
        // 숫자 생성
        createNumber();

        // 메시지 생성
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // 어디서 보낼지 설정
            message.setFrom(senderEmail);
            // 누구에게 보낼지 설정
            message.setRecipients(MimeMessage.RecipientType.TO, mail);

            // 어떻게 표시되는가에 대한 부분
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(String mail) {
        // 위에 있는 메서드를 활용하여 메일을 생성
        MimeMessage message = CreateMail(mail);

        // 메일 전송
        javaMailSender.send(message);

        // 메일에는 이미 number가 있고 그 일치여부를 판단하기 위해 우리한테도 정보를 남기는 것
        return number;
    }
}
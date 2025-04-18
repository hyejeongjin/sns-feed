package com.example.sns_feed.domain.email.emailservice;
import com.example.sns_feed.domain.redis.service.RedisServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final RedisServiceImpl redisService;
    private static int number;

    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public MimeMessage CreateMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();
        try
        {
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setFrom(mail);
            message.setSubject("이메일 인증"); // 이메일 제목
            String body = "";
            body += "<h3>from. 90Company </h3>";
            body += "<h3>요청하신 인증 번호입니다.</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>  감사합니다. </h3>";
            message.setText(body, "UTF-8", "html"); // 이메일 본문
            log.info("sent email: {}", "@90Company");
        }
        catch (MessagingException e){
            log.error("[EmailService.send()] error {}", e.getMessage());
        }

        return message;
    }

    @Transactional
    public int sendMail(String mail){
        MimeMessage mimeMessage = CreateMail(mail);
        javaMailSender.send(mimeMessage);
        return number;
    }
}

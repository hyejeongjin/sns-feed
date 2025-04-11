package com.example.sns_feed.domain.email.entity;

import lombok.Data;
@Data
public class EmailMessage {

    // 수신자
    private String to;

    // 제목
    private  String subject;

    // 메시지
    private String message;

    public EmailMessage() {

    }
    public EmailMessage(String to, String message, String subject) {
        this.to = to;
        this.message = message;
        this.subject = subject;
    }
}

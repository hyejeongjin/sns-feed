package com.example.sns_feed.mail;

import lombok.Getter;

@Getter
public class MailRequestDto {

    private final String mail;

    public MailRequestDto(String mail) {
        this.mail = mail;
    }
}

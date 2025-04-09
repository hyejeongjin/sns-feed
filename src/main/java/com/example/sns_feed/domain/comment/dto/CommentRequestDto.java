package com.example.sns_feed.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private final String content;

    public CommentRequestDto(String content) {
        this.content = content;
    }
}

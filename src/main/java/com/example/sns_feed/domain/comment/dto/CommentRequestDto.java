package com.example.sns_feed.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private final String contents;

    public CommentRequestDto(String contents) {
        this.contents = contents;
    }
}

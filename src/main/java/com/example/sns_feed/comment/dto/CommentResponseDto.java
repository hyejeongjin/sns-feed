package com.example.sns_feed.comment.dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final Long userId;

    private final String contents;

    public CommentResponseDto(Long id, Long userId, String contents) {
        this.id = id;
        this.userId = userId;
        this.contents = contents;
    }
}

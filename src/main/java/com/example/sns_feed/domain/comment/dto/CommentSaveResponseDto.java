package com.example.sns_feed.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentSaveResponseDto {

    private final Long id;

    private final Long userId;

    private final String content;


    public CommentSaveResponseDto(Long id, Long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }
}

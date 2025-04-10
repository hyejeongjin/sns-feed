package com.example.sns_feed.domain.comment.dto;

import lombok.Getter;
import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {
    private final Long id;
    private final Long userId;
    private final Long boardId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public CommentResponseDto(Long id, Long userId, Long boardId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

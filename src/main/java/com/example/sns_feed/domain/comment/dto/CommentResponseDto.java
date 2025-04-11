package com.example.sns_feed.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {

    private final Long id;

    private final Long userId;

    private final Long boardId;

    private final String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

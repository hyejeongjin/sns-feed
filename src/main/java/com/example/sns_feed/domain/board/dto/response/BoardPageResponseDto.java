package com.example.sns_feed.domain.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardPageResponseDto {

    private final Long id;

    private final String userName;

    private final String title;

    private final Long commentCount;

    private final Long likeCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    @QueryProjection
    public BoardPageResponseDto(Long id, String userName, String title, Long commentCount, Long likeCount, LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.updatedAt = updatedAt;
    }
}

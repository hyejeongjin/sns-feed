package com.example.sns_feed.domain.board.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardPageResponseDto {

    private final Long id;
    private final String userName;
    private final String title;
    private final Long commentCount;
    private final LocalDateTime updatedAt;

    public BoardPageResponseDto(Long id, String userName, String title, Long commentCount, LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.commentCount = commentCount;
        this.updatedAt = updatedAt;
    }
}

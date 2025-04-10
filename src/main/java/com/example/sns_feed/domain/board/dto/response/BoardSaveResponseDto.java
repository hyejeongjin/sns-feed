package com.example.sns_feed.domain.board.dto.response;

import com.example.sns_feed.domain.board.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardSaveResponseDto {

    private final Long id;

    private final String userName;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;


    public BoardSaveResponseDto(Board board) {
        this.id = board.getId();
        this.userName = board.getUser().getUserName();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }
}

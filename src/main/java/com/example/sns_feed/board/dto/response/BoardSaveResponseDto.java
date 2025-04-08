package com.example.sns_feed.board.dto.response;

import com.example.sns_feed.board.entity.Board;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BoardSaveResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private static BoardSaveResponseDto toDto(Board board) {
        return new BoardSaveResponseDto(board.getId(), board.getTitle(), board.getContents(), board.getCreateAt(),board.getUpdatedAt());
    }
}

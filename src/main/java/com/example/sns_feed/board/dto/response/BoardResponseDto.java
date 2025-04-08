package com.example.sns_feed.board.dto.response;

import com.example.sns_feed.board.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BoardResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final LocalDateTime updatedAt;

    //이거 따로 뺄지 고민해야 함 (코드 중복)
    private static BoardSaveResponseDto toDto(Board board) {
        return new BoardSaveResponseDto(board.getId(), board.getTitle(), board.getContents(), board.getCreateAt(),board.getUpdatedAt());
    }
}

package com.example.sns_feed.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardSaveLikeResponseDto {

    private final Long boardId;
    private final String title;
    private final String content;
    private final Long userId;

}
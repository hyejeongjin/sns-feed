package com.example.sns_feed.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardLikeResponseDto {

    private final Long boardId;

    private final Long likeCount;

    private final Long userId;

    private final String userName;

    private final boolean isLiked;
}

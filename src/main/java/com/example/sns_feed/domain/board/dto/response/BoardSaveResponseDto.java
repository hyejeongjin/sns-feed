package com.example.sns_feed.domain.board.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BoardSaveResponseDto {

    private final Long id;

    private final String userName;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;


}

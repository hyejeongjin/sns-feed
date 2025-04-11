package com.example.sns_feed.domain.board.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardUpdateRequestDto {

    @Size(max=20, message = "게시글 제목은 20자 이내로 작성해주세요.")
    private final String title;

    @Size(max=100, message = "게시글 내용은 100자 이내로 작성해주세요.")
    private final String content;

}
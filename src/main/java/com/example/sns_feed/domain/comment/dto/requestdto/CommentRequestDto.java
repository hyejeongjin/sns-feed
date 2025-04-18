package com.example.sns_feed.domain.comment.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "내용은 필수로 입력해야합니다!")
    private final String content;

    public CommentRequestDto(String content) {
        this.content = content;
    }
}

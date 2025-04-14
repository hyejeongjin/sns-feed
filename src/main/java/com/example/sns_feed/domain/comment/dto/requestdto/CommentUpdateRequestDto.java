package com.example.sns_feed.domain.comment.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {

    @NotBlank(message = "내용은 필수로 입력해야합니다!")
    private String content;
}

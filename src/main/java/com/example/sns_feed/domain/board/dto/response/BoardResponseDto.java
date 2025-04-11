package com.example.sns_feed.domain.board.dto.response;

import com.example.sns_feed.domain.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class BoardResponseDto {

    private final Long id;

    private final String userName;

    private final String title;

    private final String content;

    private final Long likeCount;

    private final boolean isLiked;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime updatedAt;

    private final List<BoardCommentDto> CommentList;

}

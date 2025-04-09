package com.example.sns_feed.domain.board.dto.response;

import com.example.sns_feed.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class BoardResponseDto {

    private final Long id;

    private final String userName;

    private final String title;

    private final String content;

    private final LocalDateTime updatedAt;

    private final List<Comment> CommentList;

}

package com.example.sns_feed.comment.controller;

import com.example.sns_feed.comment.dto.CommentRequestDto;
import com.example.sns_feed.comment.dto.CommentResponseDto;
import com.example.sns_feed.comment.service.CommentService;
import com.example.sns_feed.common.Const;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    public CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentResponseDto> saveComment(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long boardId,
            @Valid @RequestBody CommentRequestDto requestDto){


         CommentResponseDto commentResponseDto = commentService.save(userId, boardId, requestDto.getContents());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);

    }
}

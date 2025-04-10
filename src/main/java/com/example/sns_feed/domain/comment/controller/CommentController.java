package com.example.sns_feed.domain.comment.controller;


import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.comment.dto.CommentRequestDto;
import com.example.sns_feed.domain.comment.dto.CommentResponseDto;
import com.example.sns_feed.domain.comment.service.CommentService;
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


         CommentResponseDto commentResponseDto = commentService.save(userId, boardId, requestDto.getContent());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);

    }
}

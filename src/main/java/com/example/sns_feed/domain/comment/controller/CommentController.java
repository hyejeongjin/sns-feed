package com.example.sns_feed.domain.comment.controller;


import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.comment.dto.CommentRequestDto;
import com.example.sns_feed.domain.comment.dto.CommentResponseDto;
import com.example.sns_feed.domain.comment.dto.CommentSaveResponseDto;
import com.example.sns_feed.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    public CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentSaveResponseDto> saveComment(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long boardId,
            @Valid @RequestBody CommentRequestDto requestDto){


         CommentSaveResponseDto commentSaveResponseDto = commentService.save(userId, boardId, requestDto.getContent());

        return new ResponseEntity<>(commentSaveResponseDto, HttpStatus.CREATED);

    }

    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findByBoard(@PathVariable Long boardId){
        List<CommentResponseDto> commentResponseDtoList = commentService.findByBoard(boardId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }


}

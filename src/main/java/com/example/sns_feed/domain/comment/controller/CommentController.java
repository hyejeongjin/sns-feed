package com.example.sns_feed.domain.comment.controller;


import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.comment.dto.CommentRequestDto;
import com.example.sns_feed.domain.comment.dto.CommentResponseDto;
import com.example.sns_feed.domain.comment.dto.CommentSaveResponseDto;
import com.example.sns_feed.domain.comment.dto.CommentUpdateRequestDto;
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
    public ResponseEntity<CommentResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long boardId,
            @Valid @RequestBody CommentRequestDto requestDto){

         CommentResponseDto commentResponseDto = commentService.save(userId, boardId, requestDto);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);

    }

    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findByBoard(@PathVariable Long boardId){
        List<CommentResponseDto> commentResponseDtoList = commentService.findByBoard(boardId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> findOne(@PathVariable Long id){
        return new ResponseEntity<>(commentService.findOne(id), HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto>  update(@SessionAttribute(name = Const.LOGIN_USER) Long userId,
                                                      @PathVariable Long id,
                                                      @Valid @RequestBody CommentUpdateRequestDto dto){

        CommentResponseDto commentResponseDto = commentService.update(userId, id, dto);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete (@SessionAttribute(name = Const.LOGIN_USER) Long userId,
                                        @PathVariable Long id){
        commentService.delete(userId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

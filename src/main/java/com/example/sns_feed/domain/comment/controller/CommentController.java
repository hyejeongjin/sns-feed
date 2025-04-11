package com.example.sns_feed.domain.comment.controller;


import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.comment.dto.CommentNameResponseDto;
import com.example.sns_feed.domain.comment.dto.CommentRequestDto;
import com.example.sns_feed.domain.comment.dto.CommentResponseDto;
import com.example.sns_feed.domain.comment.dto.CommentUpdateRequestDto;
import com.example.sns_feed.domain.comment.service.CommentService;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    public final CommentService commentService;

    /**
     * 댓글 저장
     *
     * @param userDto    유저 dto
     * @param boardId    게시글 id
     * @param requestDto 댓글 작성 요청 dto
     * @return 저장된 댓글이 담긴 응답 dto
     */
    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto userDto,
            @PathVariable Long boardId,
            @Valid @RequestBody CommentRequestDto requestDto
    ){
        Long userId = userDto.getId();
        CommentResponseDto commentResponseDto = commentService.save(userId, boardId, requestDto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);

    }

    /**
     * 게시글 내 댓글 조회
     *
     * @param boardId 게시글 id
     * @return 댓글 응답 dto 리스트
     */
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findByBoard(@PathVariable Long boardId){
        List<CommentResponseDto> commentResponseDtoList = commentService.findByBoard(boardId);
        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    /**
     * 단일 댓글 조회
     *
     * @param id 댓글 id
     * @return 댓글 정보 응답 DTO
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentNameResponseDto> findOne(@PathVariable Long id){
        return new ResponseEntity<>(commentService.findOne(id), HttpStatus.OK);
    }

    /**
     * 댓글 수정
     *
     * @param userDto 사용자 dto
     * @param id      댓글 id
     * @param dto     댓글 수정 요청 dto
     * @return 댓글 응답 dto
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto>  update(
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto userDto,
            @PathVariable Long id,
            @Valid @RequestBody CommentUpdateRequestDto dto
    ){
        Long userId = userDto.getId();
        CommentResponseDto commentResponseDto = commentService.update(userId, id, dto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    /**
     * 댓글 삭제
     *
     * @param userDto 사용자 dto
     * @param id      댓글 id
     * @return 삭제 완료
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete (
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto userDto,
            @PathVariable Long id
    ){
        Long userId = userDto.getId();
        commentService.delete(userId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

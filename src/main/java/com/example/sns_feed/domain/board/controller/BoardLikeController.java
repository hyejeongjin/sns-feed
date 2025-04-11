package com.example.sns_feed.domain.board.controller;

import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.board.dto.response.BoardSaveLikeResponseDto;
import com.example.sns_feed.domain.board.service.BoardLikeService;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/boards/{id}")
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;


    /*
     * 2025 04 11
     * 조아현
     * 게시글 좋아요 생성
     * */
    @PostMapping("/like")
    public ResponseEntity<BoardSaveLikeResponseDto> likeBoard (@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @PathVariable Long id){

        return ResponseEntity.status(HttpStatus.CREATED).body(boardLikeService.likeBoard(loginUser.getId(), id));
    }


    /*
     * 2025 04 11
     * 조아현
     * 게시글 좋아요 삭제
     * */
    @DeleteMapping("/like")
    public ResponseEntity<Map<String, String>> unlikeBoard(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @PathVariable Long id){

        boardLikeService.unlikeBoard(loginUser.getId(), id);

        return new ResponseEntity<>(Map.of("message", "좋아요가 취소되었습니다."), HttpStatus.ACCEPTED);
    }

}

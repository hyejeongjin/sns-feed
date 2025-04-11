package com.example.sns_feed.domain.board.controller;

import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.board.dto.response.BoardLikeResponseDto;
import com.example.sns_feed.domain.board.service.BoardLikeService;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{id}")
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    /**
     * 게시글 좋아요 생성 및 삭제
     * @author  조아현
     * @since   2025 04 11
     * @param   loginUser
     * @param   id
     * @return  BoardLikeResponseDto
     */
    @PostMapping("/like")
    public ResponseEntity<BoardLikeResponseDto> likeBoard (
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser,
            @PathVariable Long id
    ){

        return new ResponseEntity<>(boardLikeService.likeBoard(loginUser.getId(), id), HttpStatus.CREATED);
    }


}

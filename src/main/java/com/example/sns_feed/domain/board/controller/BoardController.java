package com.example.sns_feed.domain.board.controller;

import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.board.dto.request.BoardRequestDto;
import com.example.sns_feed.domain.board.dto.request.BoardUpdateRequestDto;
import com.example.sns_feed.domain.board.dto.response.BoardResponseDto;
import com.example.sns_feed.domain.board.dto.response.BoardSaveResponseDto;
import com.example.sns_feed.domain.board.dto.response.BoardUpdateResponseDto;
import com.example.sns_feed.domain.board.service.BoardService;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@Validated
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ResourceUrlProvider resourceUrlProvider;

    @PostMapping
    public ResponseEntity<BoardSaveResponseDto> saveBoard(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @Valid @RequestBody BoardRequestDto boardRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.saveBoard(loginUser.getId(), boardRequestDto));
    }




    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findSingleBoard(@PathVariable Long id) {

        return ResponseEntity.ok(boardService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BoardUpdateResponseDto> updateBoard(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @PathVariable Long id, @Valid @RequestBody BoardUpdateRequestDto dto) {


        return ResponseEntity.ok(boardService.updateBoard(loginUser.getId(), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @PathVariable Long id) {

        boardService.deleteById(id, loginUser.getId());

        return ResponseEntity.ok().build();
    }


}

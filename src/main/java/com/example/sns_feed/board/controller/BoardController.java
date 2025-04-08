package com.example.sns_feed.board.controller;

import com.example.sns_feed.board.dto.request.BoardRequestDto;
import com.example.sns_feed.board.dto.response.BoardSaveResponseDto;
import com.example.sns_feed.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Validated
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

//    public ResponseEntity<BoardSaveResponseDto> saveBoard(@Valid @RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request){
//
//        HttpSession session = request.getSession();
//
//
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.saveBoard());
//    }


}

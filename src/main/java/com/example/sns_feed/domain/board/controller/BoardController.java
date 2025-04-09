package com.example.sns_feed.domain.board.controller;

import com.example.sns_feed.domain.board.dto.request.BoardRequestDto;
import com.example.sns_feed.domain.board.dto.response.BoardSaveResponseDto;
import com.example.sns_feed.domain.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardSaveResponseDto> saveBoard(@Valid @RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute("sessionId", 1);

        Long id = (Long) session.getAttribute("sessionId");

        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.saveBoard(id, boardRequestDto));
    }




}

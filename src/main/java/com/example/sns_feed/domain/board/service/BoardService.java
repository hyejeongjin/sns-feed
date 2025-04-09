package com.example.sns_feed.domain.board.service;

import com.example.sns_feed.domain.board.dto.request.BoardRequestDto;
import com.example.sns_feed.domain.board.dto.response.BoardSaveResponseDto;
import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.repository.BoardRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(Long id, BoardRequestDto dto) {

        User user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Board board = new Board(dto.getTitle(), dto.getContent(), user);

        boardRepository.save(board);

        return new BoardSaveResponseDto(
                                board.getId(),
                                user.getUserName(),
                                board.getTitle(),
                                board.getContent(),
                                board.getCreatedAt(),
                                board.getUpdatedAt());
    }

//    @Transactional(readOnly = true)
//    public BoardResponseDto findById(Long id) {
//
//
//        Board board = boardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        return new BoardResponseDto(
//                            board.getId(),
//                            board.getUser().getUserName(),
//                            board.getTitle(),
//                            board.getContents(),
//                            board.getUpdatedAt());
//    }

//    public BoardResponseDto updateBoard(@PathVariable Long id, )




}

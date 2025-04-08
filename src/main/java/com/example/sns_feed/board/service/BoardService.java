package com.example.sns_feed.board.service;

import com.example.sns_feed.board.dto.request.BoardRequestDto;
import com.example.sns_feed.board.dto.response.BoardSaveResponseDto;
import com.example.sns_feed.board.entity.Board;
import com.example.sns_feed.board.repository.BoardRepository;
import com.example.sns_feed.user.entity.User;
import com.example.sns_feed.user.repository.UserRepository;
import jakarta.validation.Valid;
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

        Board board = new Board(dto.getTitle(), dto.getContents(), user);

        boardRepository.save(board);

        return new BoardSaveResponseDto(
                                board.getId(),
                                board.getTitle(),
                                board.getContents(),
                                board.getCreateAt(),
                                board.getUpdatedAt());
    }
}

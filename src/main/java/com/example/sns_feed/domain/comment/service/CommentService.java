package com.example.sns_feed.domain.comment.service;

import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.repository.BoardRepository;
import com.example.sns_feed.domain.comment.dto.CommentResponseDto;
import com.example.sns_feed.domain.comment.dto.CommentSaveResponseDto;
import com.example.sns_feed.domain.comment.entity.Comment;
import com.example.sns_feed.domain.comment.repository.CommentRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public CommentSaveResponseDto save(Long userId, Long boardId, String content) {

        User user = userRepository.findUserByIdOrElseThrow(userId);
        Board board = boardRepository.getReferenceById(boardId);

        Comment comment = new Comment(content, board, user);
        Comment savedComment = commentRepository.save(comment);

        return new CommentSaveResponseDto(savedComment.getComment_id(), savedComment.getComment_id() , savedComment.getContent());
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findByBoard(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);

        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getComment_id(),
                        comment.getUser().getId(),
                        comment.getBoard().getId(),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()))
                .collect(Collectors.toList());

    }
}

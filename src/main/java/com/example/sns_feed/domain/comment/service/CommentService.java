package com.example.sns_feed.domain.comment.service;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.repository.BoardRepository;
import com.example.sns_feed.domain.comment.dto.CommentRequestDto;
import com.example.sns_feed.domain.comment.dto.CommentResponseDto;
import com.example.sns_feed.domain.comment.dto.CommentSaveResponseDto;
import com.example.sns_feed.domain.comment.dto.CommentUpdateRequestDto;
import com.example.sns_feed.domain.comment.entity.Comment;
import com.example.sns_feed.domain.comment.repository.CommentRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
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

    public CommentResponseDto save(Long userId, Long boardId, CommentRequestDto dto) {

        User user = userRepository.findUserByIdOrElseThrow(userId);
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        Comment comment = new Comment(dto.getContent(), board, user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), user.getId(), board.getId(),
                    comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findByBoard(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);

        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getUser().getId(),
                        comment.getBoard().getId(),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findOne(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        return new CommentResponseDto(comment.getId(), comment.getUser().getId(),
                                    comment.getBoard().getId(), comment.getContent(),
                                    comment.getCreatedAt(), comment.getUpdatedAt());
    }


    public CommentResponseDto update(Long userId, Long id, CommentUpdateRequestDto dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if(!comment.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("본인이 작성한 댓글만 수정 가능합니다.");
        }
        comment.update(dto.getContent());
        return new CommentResponseDto(comment.getId(), comment.getUser().getId(),
                                        comment.getBoard().getId(),comment.getContent(),
                                        comment.getCreatedAt(), comment.getUpdatedAt());
    }

    public void delete(Long userId, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if(!comment.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("본인이 작성한 댓글만 삭제 가능합니다.");
        }
        commentRepository.delete(comment);
    }
}

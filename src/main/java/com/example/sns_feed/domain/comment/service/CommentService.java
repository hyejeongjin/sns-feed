package com.example.sns_feed.domain.comment.service;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.repository.BoardRepository;
import com.example.sns_feed.domain.comment.dto.requestdto.CommentRequestDto;
import com.example.sns_feed.domain.comment.dto.responsedto.CommentResponseDto;
import com.example.sns_feed.domain.comment.dto.responsedto.CommentNameResponseDto;
import com.example.sns_feed.domain.comment.dto.requestdto.CommentUpdateRequestDto;
import com.example.sns_feed.domain.comment.entity.Comment;
import com.example.sns_feed.domain.comment.repository.CommentRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Comment service.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    /**
     * 새로운 댓글을 저장하고, 저장된 댓글을 응답 DTO로 반환.
     *
     * @param userId  작성자 id
     * @param boardId 게시글 id
     * @param dto     댓글 작성 요청 dto
     * @return 저장된 댓글 응답 dto
     */
    public CommentResponseDto save(Long userId, Long boardId, CommentRequestDto dto) {

        User user = userRepository.findUserByIdOrElseThrow(userId);
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        Comment comment = new Comment(dto.getContent(), board, user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), user.getId(), board.getId(),
                    comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }


    /**
     * 특정 게시글의 댓글 전체 조회
     *
     * @param boardId 특정 게시글
     * @return 댓글 목록
     */
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

    /**
     * 단일 댓글 조회
     *
     * @param id 유저 아이디
     * @return 댓글 작성자명과 내용 시간 등을 응답하는 dto
     */
    @Transactional(readOnly = true)
    public CommentNameResponseDto findOne(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        return new CommentNameResponseDto(comment.getId(), comment.getUser().getUserName(),
                                    comment.getBoard().getId(), comment.getContent(),
                                    comment.getCreatedAt(), comment.getUpdatedAt());
    }

    /**
     * Update 댓글 수정
     *
     * @param userId 댓글 작성자 id
     * @param id     댓글 id
     * @param dto    댓글 update 요청 dto
     * @return 댓글 응답 dto
     */
    public CommentResponseDto update(Long userId, Long id, CommentUpdateRequestDto dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if(!comment.getUser().getId().equals(userId)){
            throw new CustomException(ErrorCode.USER_MISMATCH);
        }
        comment.update(dto.getContent());
        return new CommentResponseDto(comment.getId(), comment.getUser().getId(),
                                        comment.getBoard().getId(),comment.getContent(),
                                        comment.getCreatedAt(), comment.getUpdatedAt());
    }

    /**
     * Delete.
     *
     * @param userId 댓글 작성자 id
     * @param id     댓글 id
     */
    public void delete(Long userId, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if(!comment.getUser().getId().equals(userId)){
            throw new CustomException(ErrorCode.COMMENT_FORBIDDEN);
        }
        commentRepository.delete(comment);
    }
}

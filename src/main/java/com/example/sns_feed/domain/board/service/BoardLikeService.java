package com.example.sns_feed.domain.board.service;

import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.common.exception.UserNotFoundException;
import com.example.sns_feed.common.exception.board.BoardLikeFailedException;
import com.example.sns_feed.common.exception.board.BoardLikeNotFoundException;
import com.example.sns_feed.common.exception.board.BoardNotFoundException;
import com.example.sns_feed.domain.board.dto.response.BoardLikeResponseDto;
import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.entity.BoardLike;
import com.example.sns_feed.domain.board.repository.BoardLikeRepository;
import com.example.sns_feed.domain.board.repository.BoardRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    /**
     * 게시글 좋아요 생성 및 삭제
     * - 본인이 작성한 게시글에 좋아요 누를 수 없음
     * - 이미 좋아요를 누른 경우엔 -> 삭제 처리
     * @author  조아현
     * @since   2025 04 011
     * @param   id
     * @param   boardId
     * @return  BoardLikeResponseDto
     * @throws  BoardNotFoundException 게시글이 존재하지 않을 때 발생
     * @throws  UserNotFoundException 유저가 존재하지 않을 때 발생
     * @throws  BoardLikeFailedException 본인 게시글에 좋아요를 누를 때 발생
     */
    @Transactional
    public BoardLikeResponseDto likeBoard(Long id, Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND.getCode()));

        //본인 게시글에 좋아요 못 누름
        if(board.getUser().getId().equals(user.getId())) {
            throw new BoardLikeFailedException(ErrorCode.BOARD_LIKE_FAILED);
        }

        Long likeCount = boardLikeRepository.countByBoard_Id(board.getId());

        boolean isLiked = boardLikeRepository.existsByBoard_IdAndUser_Id(boardId,id);

        //이미 좋아요를 누른 경우 : 좋아요 취소
        if(isLiked) {
            unlikeBoard(id,boardId);

            return new BoardLikeResponseDto(
                    board.getId(),
                    likeCount-1,
                    user.getId(),
                    user.getUserName(),
                    false
            );
        }


        BoardLike boardLike = new BoardLike(board, user);

        boardLikeRepository.save(boardLike);

        return new BoardLikeResponseDto(
                board.getId(),
                likeCount+1,
                user.getId(),
                user.getUserName(),
                true
        );
    }


    /**
     * 게시글 좋아요 삭제
     * @author  조아현
     * @since   2025 04 11
     * @param   id
     * @param   boardId
     * @return
     * @throws  BoardNotFoundException 게시글이 존재하지 않을 때 발생
     * @throws  UserNotFoundException 유저가 존재하지 않을 때 발생
     * @throws  BoardLikeNotFoundException 좋아요 내역이 존재하지 않을 때 발생
     */
    @Transactional
    public void unlikeBoard(Long id, Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND.getCode()));

        BoardLike boardLike = boardLikeRepository.findByBoardAndUser(board, user).orElseThrow(() -> new BoardLikeNotFoundException(ErrorCode.BOARD_LIKE_NOT_FOUND));

        boardLikeRepository.delete(boardLike);
    }




}

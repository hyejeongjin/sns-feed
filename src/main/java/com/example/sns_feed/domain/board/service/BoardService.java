package com.example.sns_feed.domain.board.service;

import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.common.exception.UserNotFoundException;
import com.example.sns_feed.common.exception.board.BoardNotFoundException;
import com.example.sns_feed.common.exception.board.BoardUnauthorizedException;
import com.example.sns_feed.domain.board.dto.request.BoardRequestDto;
import com.example.sns_feed.domain.board.dto.request.BoardUpdateRequestDto;
import com.example.sns_feed.domain.board.dto.response.*;
import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.entity.BoardLike;
import com.example.sns_feed.domain.board.repository.BoardLikeRepository;
import com.example.sns_feed.domain.board.repository.BoardRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final UserRepository userRepository;


    /**
     * 게시글 생성
     * @author  조아현
     * @since   2025 04 19
     * @param   sessionId
     * @param   dto
     * @return  BoardSaveResponseDto
     * @throws  UserNotFoundException 유저가 존재하지 않을 때 발생
     */
    @Transactional
    public BoardSaveResponseDto saveBoard(Long sessionId, BoardRequestDto dto) {

        User user = userRepository.findById(sessionId).orElseThrow(()-> new UserNotFoundException(ErrorCode.USER_NOT_FOUND.getCode()));

        Board board = new Board(dto.getTitle(), dto.getContent(), user);

        boardRepository.save(board);

        return new BoardSaveResponseDto(board);
    }

    /**
     * 게시글 단건 조회
     * @author  조아현
     * @since   2025 04 11
     * @param   loginUserId
     * @param   id
     * @return  BoardResponseDto
     * @throws  BoardNotFoundException 게시글이 존재하지 않을 때 발생
     */
    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long loginUserId, Long id) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        Long likeCount = boardLikeRepository.countByBoard_Id(board.getId());

        boolean isLiked = boardLikeRepository.existsByBoard_IdAndUser_Id(board.getId(),loginUserId);

        List<BoardCommentDto> boardCommentDtoList = board.getComments().stream().map(comment -> new BoardCommentDto(
                comment.getId(),
                comment.getUser().getUserName(),
                comment.getContent(),
                comment.getUpdatedAt())).toList();

        return new BoardResponseDto(
                board.getId(),
                board.getUser().getUserName(),
                board.getTitle(),
                board.getContent(),
                likeCount,
                isLiked,
                board.getUpdatedAt(),
                boardCommentDtoList);
    }


    /**
     * 게시글 전체 목록 조회, 페이지네이션 적용됨
     * - 제목 검색 가능
     * - 팔로우 목록만 조회 가능
     * @author  조아현
     * @since   2025 04 10
     * @param   id
     * @param   titleSearch
     * @param   isFollowingBoard
     * @param   page
     * @return  PageResponseDto
     */
    @Transactional(readOnly = true)
    public PageResponseDto findAllPage(Long id, String titleSearch, Boolean isFollowingBoard, int page) {


        int adjustedPage = (page > 0) ? page - 1 : 0;
        Pageable pageable = PageRequest.of(adjustedPage, 10);

        Page<BoardPageResponseDto> allPage = boardRepository.findBoardsPage(id,titleSearch,isFollowingBoard,pageable);

        //사용자에게 반환할 페이지 객체 정보
        int nowPage = allPage.getNumber() + 1; // 1부터 시작
        int pageRange = 5;
        int totalPages = allPage.getTotalPages();
        int startPage = ((nowPage - 1) / pageRange) * pageRange + 1;
        int endPage = Math.min(startPage + pageRange - 1, totalPages);
        boolean hasPrevious = startPage > 1;
        boolean hasNext = endPage < totalPages;


        return new PageResponseDto (
                allPage.getContent(),
                nowPage,
                allPage.getSize(),
                totalPages,
                hasNext,
                hasPrevious,
                startPage,
                endPage);
    }


    /**
     * 게시글 수정
     * @author  조아현
     * @since   2025 04 09
     * @param   sessionId
     * @param   id
     * @param   dto
     * @return  BoardUpdateResponseDto
     * @throws  BoardNotFoundException 게시글이 존재하지 않을 때 발생
     * @throws  BoardUnauthorizedException 게시글을 작성한 유저만 수정/삭제 가능
     */
    @Transactional
    public BoardUpdateResponseDto updateBoard(Long sessionId, Long id, BoardUpdateRequestDto dto) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        if(!sessionId.equals(board.getUser().getId())) {
            throw new BoardUnauthorizedException(ErrorCode.BOARD_FORBIDDEN);
        }

        board.update(dto.getTitle(), dto.getContent());

        return new BoardUpdateResponseDto(
                board.getId(),
                board.getUser().getUserName(),
                board.getTitle(),
                board.getContent(),
                board.getUpdatedAt()
        );
    }


    /**
     * 게시글 수정
     * @author  조아현
     * @since   2025 04 09
     * @param   id
     * @param   sessionId
     * @return  void
     * @throws  BoardNotFoundException 게시글이 존재하지 않을 때 발생
     * @throws  BoardUnauthorizedException 게시글을 작성한 유저만 수정/삭제 가능
     */
    @Transactional
    public void deleteById(Long id, Long sessionId) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        if(!sessionId.equals(board.getUser().getId())) {
            throw new BoardUnauthorizedException(ErrorCode.BOARD_FORBIDDEN);
        }

        //게시글 좋아요 삭제 (연관관계 삭제 cascade를 구현)
        List<BoardLike> deleteBoardLikes = boardLikeRepository.findAllByBoard_Id(id);
        boardLikeRepository.deleteAll(deleteBoardLikes);

        boardRepository.delete(board);
    }


}

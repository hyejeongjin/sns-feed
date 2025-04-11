package com.example.sns_feed.domain.board.service;

import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.common.exception.board.BoardNotFoundException;
import com.example.sns_feed.common.exception.board.BoardUnauthorizedException;
import com.example.sns_feed.domain.board.dto.request.BoardRequestDto;
import com.example.sns_feed.domain.board.dto.request.BoardUpdateRequestDto;
import com.example.sns_feed.domain.board.dto.response.*;
import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.repository.BoardLikeRepository;
import com.example.sns_feed.domain.board.repository.BoardRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final UserRepository userRepository;


    /*
    * 2025 04 09
    * 조아현
    * 게시글 생성
    * */
    @Transactional
    public BoardSaveResponseDto saveBoard(Long sessionId, BoardRequestDto dto) {

        User user = userRepository.findById(sessionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Board board = new Board(dto.getTitle(), dto.getContent(), user);

        boardRepository.save(board);

        return new BoardSaveResponseDto(board);
    }

    /*
     * 2025 04 09
     * 조아현
     * 게시글 단건 조회
     * */
    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long loginUserId, Long id) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        Long likeCount = boardLikeRepository.countByBoard_Id(board.getId());

        boolean isLiked = boardLikeRepository.existsByBoard_IdAndUser_Id(board.getId(),loginUserId);

        List<BoardCommentDto> boardCommentDtoList = board.getComments().stream().map(comment -> new BoardCommentDto(
                comment.getId(),
                board.getUser().getUserName(),
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

    /*
     * 2025 04 10
     * 조아현
     * 게시글 전체 페이지네이션 조회 & 검색 & 팔로우 게시글 조건
     * */
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

    /*
     * 2025 04 09
     * 조아현
     * 게시글 수정
     * */
    @Transactional
    public BoardUpdateResponseDto updateBoard(Long sessionId, Long id, BoardUpdateRequestDto dto) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        if(!sessionId.equals(board.getUser().getId())) {
            throw new BoardUnauthorizedException(ErrorCode.BOARD_UNAUTHORIZED);
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

    /*
     * 2025 04 09
     * 조아현
     * 게시글 삭제
     * */
    @Transactional
    public ResponseEntity<Map<String, String>> deleteById(Long id, Long sessionId) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        if(!sessionId.equals(board.getUser().getId())) {
            throw new BoardUnauthorizedException(ErrorCode.BOARD_UNAUTHORIZED);
        }
        boardRepository.delete(board);

        return new ResponseEntity<>(Map.of("message", "게시글이 삭제되었습니다."), HttpStatus.ACCEPTED);
    }





}

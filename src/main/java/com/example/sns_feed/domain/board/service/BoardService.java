package com.example.sns_feed.domain.board.service;

import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.common.exception.board.BoardNotFoundException;
import com.example.sns_feed.common.exception.board.BoardUnauthorizedException;
import com.example.sns_feed.domain.board.dto.request.BoardRequestDto;
import com.example.sns_feed.domain.board.dto.request.BoardUpdateRequestDto;
import com.example.sns_feed.domain.board.dto.response.*;
import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.repository.BoardJdbcRepository;
import com.example.sns_feed.domain.board.repository.BoardRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardJdbcRepository boardJcbcRepository;
    private final BoardJdbcRepository boardJDBCRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(Long sessionId, BoardRequestDto dto) {

        User user = userRepository.findById(sessionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Board board = new Board(dto.getTitle(), dto.getContent(), user);

        boardRepository.save(board);

        return new BoardSaveResponseDto(board);
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        List<BoardCommentDto> boardCommentDtoList = board.getComments().stream().map(comment -> new BoardCommentDto(
                comment.getComment_id(),
                board.getUser().getUserName(),
                comment.getContent(),
                comment.getUpdatedAt())).toList();

        return new BoardResponseDto(
                board.getId(),
                board.getUser().getUserName(),
                board.getTitle(),
                board.getContent(),
                board.getUpdatedAt(),
                boardCommentDtoList);
    }

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


    @Transactional
    public void deleteById(Long id, Long sessionId) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND));

        if(!sessionId.equals(board.getUser().getId())) {
            throw new BoardUnauthorizedException(ErrorCode.BOARD_UNAUTHORIZED);
        }
        boardRepository.delete(board);
    }

//    @Transactional(readOnly = true)
//    public PageResponseDto findAllPage(Long id, String titleSearch, Boolean isFollowingBoard, int page) {
//
//        int adjustedPage = (page > 0) ? page - 1 : 0;
//
//        Pageable pageable = PageRequest.of(adjustedPage, 10);
//
//
//        Page<BoardPageResponseDto> allPage = boardJDBCRepository.findAllPage(id, titleSearch, isFollowingBoard, pageable);
//
//        //페이지 dto 객체에 담아 반환할 정보
//        int nowPage = allPage.getNumber() + 1;
//        int pageRange = 5;
//
//
//        int endPage = (int)(Math.ceil(nowPage/(float)pageRange)*pageRange); //현재 범위 중 마지막 페이지
//        int startPage = endPage - (pageRange-1);
//        boolean hasNext = false;
//        boolean hasPrevious = false;
//
//        if(endPage < allPage.getTotalPages()) { //10,7
//            hasNext = true;
//        }
//
//        if(startPage >= 6) {
//            hasPrevious = true;
//        }
//
//        return new PageResponseDto (
//                                    allPage.getContent(),
//                                    nowPage,
//                                    allPage.getSize(),
//                                    allPage.getTotalPages(),
//                                    hasNext,
//                                    hasPrevious,
//                                    startPage,
//                                    endPage);
//    }

    @Transactional(readOnly = true)
    public PageResponseDto findAllPage(Long id, String titleSearch, Boolean isFollowingBoard, int page) {

        int adjustedPage = (page > 0) ? page - 1 : 0;

        Pageable pageable = PageRequest.of(adjustedPage, 10);


        Page<BoardPageResponseDto> allPage = boardRepository.findBoardsPage(id,titleSearch,isFollowingBoard,pageable);


        return new PageResponseDto (
                allPage.getContent(),
                nowPage,
                allPage.getSize(),
                allPage.getTotalPages(),
                hasNext,
                hasPrevious,
                startPage,
                endPage);
    }


}

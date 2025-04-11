package com.example.sns_feed.domain.board.controller;

import com.example.sns_feed.common.Const;
import com.example.sns_feed.domain.board.dto.request.BoardRequestDto;
import com.example.sns_feed.domain.board.dto.request.BoardUpdateRequestDto;
import com.example.sns_feed.domain.board.dto.response.*;
import com.example.sns_feed.domain.board.dto.response.BoardResponseDto;
import com.example.sns_feed.domain.board.dto.response.BoardSaveResponseDto;
import com.example.sns_feed.domain.board.dto.response.BoardUpdateResponseDto;
import com.example.sns_feed.domain.board.service.BoardService;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.Map;

@Validated
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ResourceUrlProvider resourceUrlProvider;


    /**
     * 게시글 생성
     * @author  조아현
     * @since   2025 04 09
     * @param   loginUser
     * @param   boardRequestDto
     * @return  BoardSaveResponseDto
     */
    @PostMapping
    public ResponseEntity<BoardSaveResponseDto> saveBoard(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @Valid @RequestBody BoardRequestDto boardRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.saveBoard(loginUser.getId(), boardRequestDto));
    }


    /**
     * 게시글 전체 페이지네이션 조회 & 검색 & 팔로우한 사용자 게시글 목록만 조회
     * @author  조아현
     * @since   2025 04 10
     * @param   loginUser
     * @param   titleSearch
     * @param   isFollowingBoard
     * @param   page
     * @return  PageResponseDto
     */
    @GetMapping
    public ResponseEntity<PageResponseDto> findAllPage(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser,
            @RequestParam(required = false) String titleSearch,
            @RequestParam(value = "isFollowingBoard", defaultValue = "false") Boolean isFollowingBoard,
            @RequestParam(defaultValue = "1") int page) {

        PageResponseDto pageResponseDto = boardService.findAllPage(loginUser.getId(),titleSearch,isFollowingBoard,page);

        return ResponseEntity.ok(pageResponseDto);
    }


    /**
     * 게시글 단건(+게시글 내 댓글목록) 조회
     * @author  조아현
     * @since   2025 04 09
     * @param   loginUser
     * @param   id
     * @return  BoardResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findSingleBoard(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @PathVariable Long id) {

        return ResponseEntity.ok(boardService.findById(loginUser.getId() ,id));
    }

    /**
     * 게시글 수정
     * @author  조아현
     * @since   2025 04 09
     * @param   loginUser
     * @param   dto
     * @return  BoardSaveResponseDto
     * @throws
     */
    @PatchMapping("/{id}")
    public ResponseEntity<BoardUpdateResponseDto> updateBoard(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @PathVariable Long id, @Valid @RequestBody BoardUpdateRequestDto dto) {

        return ResponseEntity.ok(boardService.updateBoard(loginUser.getId(), id, dto));
    }


    /**
     * 게시글 삭제
     * @author  조아현
     * @since   2025 04 09
     * @param   loginUser
     * @param   id
     * @return  삭제 메시지 출력
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBoard(@SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser, @PathVariable Long id) {

        boardService.deleteById(id, loginUser.getId());

        return new ResponseEntity<>(Map.of("message", "게시글이 삭제되었습니다."), HttpStatus.ACCEPTED);
    }


}

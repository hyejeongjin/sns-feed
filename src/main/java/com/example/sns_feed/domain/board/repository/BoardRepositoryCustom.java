package com.example.sns_feed.domain.board.repository;

import com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BoardPageResponseDto> findBoardsPage(Long id, String titleSearch, Boolean isFollowingBoard, Pageable pageable);

}

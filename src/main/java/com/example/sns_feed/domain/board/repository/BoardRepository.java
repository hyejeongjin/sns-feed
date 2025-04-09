package com.example.sns_feed.domain.board.repository;


import com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto;
import com.example.sns_feed.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleIsLike(String titleSearch, Pageable pageable);


//    @Query(value = "SELECT new com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto(" +"")
//    Page<BoardPageResponseDto> findBoardPageWithCommentCount(Pageable pageable);
}

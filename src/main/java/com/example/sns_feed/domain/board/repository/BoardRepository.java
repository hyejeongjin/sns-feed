package com.example.sns_feed.domain.board.repository;


import com.example.sns_feed.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAByTitleIsLike(String titleSearch, Pageable pageable);
}

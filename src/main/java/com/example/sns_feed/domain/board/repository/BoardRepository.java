package com.example.sns_feed.domain.board.repository;

import com.example.sns_feed.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom{

}

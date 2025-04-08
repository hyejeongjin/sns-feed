package com.example.sns_feed.board.repository;


import com.example.sns_feed.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {


}

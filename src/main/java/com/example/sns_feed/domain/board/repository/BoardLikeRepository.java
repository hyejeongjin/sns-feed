package com.example.sns_feed.domain.board.repository;


import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    long countByBoard_Id(Long boardId);

    boolean existsByBoard_IdAndUser_Id(Long id, Long loginUserId);
}

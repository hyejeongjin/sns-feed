package com.example.sns_feed.domain.board.repository;


import com.example.sns_feed.domain.board.entity.Board;
import com.example.sns_feed.domain.board.entity.BoardLike;
import com.example.sns_feed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    long countByBoard_Id(Long boardId);

    boolean existsByBoard_IdAndUser_Id(Long id, Long loginUserId);

    Optional<BoardLike> findByBoardAndUser(Board board, User user);

    Long id(Long id);


    List<BoardLike> findAllByBoard_Id(Long boardId);
}

package com.example.sns_feed.domain.board.repository;


import com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto;
import com.example.sns_feed.domain.board.dto.response.BoardResponseDto;
import com.example.sns_feed.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom{


    @Query(value = "SELECT new com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto( " +
            " b.id, u.userName, b.title, COUNT(c.comment_id), b.updatedAt ) " +
            "FROM Board b " +
            "JOIN b.user u " +
            "LEFT JOIN Comment c ON c.board.id = b.id " +
            "GROUP BY b.id",

                countQuery = "select count(b) from Board b")
    Page<BoardPageResponseDto> findBoardPageWithCommentCount(Pageable pageable);

    @Query(value = "SELECT new com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto( " +
            " b.id, u.userName, b.title, COUNT(c.comment_id), b.updatedAt ) " +
            "FROM Board b " +
            "JOIN b.user u " +
            "LEFT JOIN Comment c ON c.board.id = b.id " +
            "WHERE b.title LIKE CONCAT('%', :titleSearch, '%') " +
            "GROUP BY b.id, u.userName, b.title, b.updatedAt",

            countQuery =  "SELECT COUNT(b) FROM Board b WHERE b.title LIKE CONCAT('%', :titleSearch, '%')")
    Page<BoardPageResponseDto> findSearchBoardPageWithCommentCount(@Param("titleSearch") String titleSearch, Pageable pageable);

    @Query(value = "SELECT new com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto(" +
            "b.id, u.userName, b.title, COUNT(c.comment_id), b.updatedAt) " +
            "FROM Board b " +
            "JOIN b.user u " +
            "JOIN Comment c ON c.comment_id = u.id " +
            "JOIN Follow f ON f.receiver.id = u.id"+
            " GROUP BY b.id",

            countQuery =  "SELECT COUNT(b) FROM Board b JOIN b.user u JOIN Follow f ON f.receiver.id = u.id")
    Page<BoardPageResponseDto> findFollowingBoardPageWithCommentCount(Pageable pageable);



    @Query(value = "SELECT new com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto(" +
            "b.id, u.userName, b.title, COUNT(c.comment_id), b.updatedAt) " +
            "FROM Board b " +
            "JOIN b.user u " +
            "JOIN Comment c ON c.comment_id = u.id " +
            "JOIN Follow f ON f.receiver.id = u.id "+
            "WHERE b.title LIKE CONCAT('%', :titleSearch, '%') " +
            " GROUP BY b.id",

            countQuery =  "SELECT COUNT(b) FROM Board b JOIN b.user u JOIN Follow f ON f.receiver.id = u.id WHERE b.title LIKE CONCAT('%', :titleSearch, '%')")
    Page<BoardPageResponseDto> findSerachFollowingPageWithCommentCount(@Param("titleSearch") String titleSearch, Pageable pageable);

    List<Board> title(String title);
}

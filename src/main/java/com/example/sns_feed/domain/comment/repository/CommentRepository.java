package com.example.sns_feed.domain.comment.repository;

import com.example.sns_feed.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoardId(Long BoardId);

}

package com.example.sns_feed.comment.repository;

import com.example.sns_feed.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

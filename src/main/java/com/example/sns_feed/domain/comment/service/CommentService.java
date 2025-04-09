package com.example.sns_feed.domain.comment.service;


import com.example.sns_feed.domain.comment.dto.CommentResponseDto;
import com.example.sns_feed.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentResponseDto save(Long userId, Long boardId, String content) {



        return null;
    }
}

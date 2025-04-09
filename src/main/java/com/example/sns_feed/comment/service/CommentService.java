package com.example.sns_feed.comment.service;

import com.example.sns_feed.comment.dto.CommentResponseDto;
import com.example.sns_feed.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentResponseDto save(Long userId, Long boardId, String contents) {



        return null;
    }
}

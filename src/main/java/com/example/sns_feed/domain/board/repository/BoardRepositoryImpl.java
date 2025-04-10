package com.example.sns_feed.domain.board.repository;

import com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<BoardPageResponseDto> findBoardsPage(Long id, String titleSearch, Boolean isFollowingBoard, Pageable pageable) {

        return  null;

    }
}

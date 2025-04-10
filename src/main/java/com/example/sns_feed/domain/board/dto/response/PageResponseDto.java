package com.example.sns_feed.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PageResponseDto {

    private final List<BoardPageResponseDto> BoardList; //게시글 목록
    private final int nowPage; //현재 페이지
    private final int pageSize; // 한 페이지 당 몇 개의 게시글인지
    private final int totalPages; //전체 페이지
    private final boolean hasNext; // 화살표
    private final boolean hasPrevious; //이전 화살표
    private final int startPage; // 페지징 범위 (현재 페이지 기준 -2)
    private final int endPage; //페이징 범위 (현재 페이지 기준 +2)

}

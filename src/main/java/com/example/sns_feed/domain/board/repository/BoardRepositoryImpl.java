package com.example.sns_feed.domain.board.repository;

import com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto;
import com.example.sns_feed.domain.board.dto.response.QBoardPageResponseDto;
import com.example.sns_feed.domain.follow.entity.QFollow;
import com.example.sns_feed.domain.follow.enums.FollowStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.sns_feed.domain.board.entity.QBoard.board;
import static com.example.sns_feed.domain.user.entity.QUser.user;
import static com.example.sns_feed.domain.comment.entity.QComment.comment;
import static com.example.sns_feed.domain.follow.entity.QFollow.follow;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardPageResponseDto> findBoardsPage(Long id, String titleSearch, Boolean isFollowingBoard, Pageable pageable) {

        System.out.println("페이지 리포지터리 "+isFollowingBoard);

        List<BoardPageResponseDto> boardPageResponseDtoList = queryFactory
                .select(new QBoardPageResponseDto(
                        board.id,
                        user.userName,
                        board.title,
                        JPAExpressions.select(comment.count())
                                .from(comment)
                                .where(comment.board.id.eq(board.id)),
                        board.updatedAt
                ))
                .from(board)
                .innerJoin(board.user, user)
                .rightJoin(follow).on(board.user.id.eq(follow.receiver.id))
                .where(
                    titleLike(titleSearch),
                    isFollowing(isFollowingBoard, id)
                )
                .groupBy(board.id,board.user.id,user.userName,board.title,board.updatedAt)
                .orderBy(board.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

//        List<BoardPageResponseDto> boardPageResponseDtoList = queryFactory
//                .select(new QBoardPageResponseDto(
//                        board.id,
//                        user.userName,
//                        board.title,
//                        JPAExpressions.select(comment.count())
//                                .from(comment)
//                                .where(comment.board.id.eq(board.id)),
//                        board.updatedAt
//                ))
//                .from(board)
//                .innerJoin(board.user, user)
//                .leftJoin(follow)
//                .on(board.user.id.eq(follow.receiver.id)
//                        .and(isFollowingBoard != null && isFollowingBoard
//                                ? follow.sender.id.eq(id).and(follow.followStatus.eq(FollowStatus.ACCEPTED))
//                                : null
//                        )
//                )
//                .where(
//                        titleLike(titleSearch)
//                )
//                .groupBy(board.id, board.user.id, user.userName, board.title, board.updatedAt)
//                .orderBy(board.updatedAt.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();

        Long total = queryFactory
                .select(board.count())
                .from(board)
                .leftJoin(board.user, user)
                .leftJoin(follow).on(board.user.id.eq(follow.receiver.id))
                .where(
                    titleLike(titleSearch),
                    isFollowing(isFollowingBoard, id)
                )
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        return new PageImpl<>(boardPageResponseDtoList,pageable,total);
    }

    private BooleanExpression titleLike(String titleSearch) {
        return titleSearch != null ? board.title.like("%"+titleSearch+"%") : null;
    }

    private BooleanExpression isFollowing(boolean isFollowingBoard, Long id) {
        System.out.println("booleanExpression "+isFollowingBoard);
        if(!isFollowingBoard) {
            System.out.println("booleanExpression 조건문 "+isFollowingBoard);
            return null;
        }
        return follow.sender.id.eq(id).and(follow.followStatus.eq(FollowStatus.ACCEPTED));
    }

}

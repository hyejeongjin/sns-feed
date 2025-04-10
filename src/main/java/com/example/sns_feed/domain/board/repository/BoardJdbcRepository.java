package com.example.sns_feed.domain.board.repository;

import com.example.sns_feed.domain.board.dto.response.BoardPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public Page<BoardPageResponseDto> findAllPage(Long id,String titleSearch, Boolean isFollowingBoard, Pageable pageable) {

        StringBuilder querySql = new StringBuilder(
            "select " +
                    "    b.id," +
                    "    u.user_name," +
                    "    b.title," +
                    "    (select" +
                    "         COUNT(c.user_id)" +
                    "     from boards bb" +
                    "              inner join users u on b.user_id = u.id" +
                    "              left join comments c on c.board_id = b.id" +
                    "     where bb.id  = b.id" +
                    "     group by b.id) as comment_count," +
                    "    b.updated_at," +
                    "    COUNT(*) OVER() AS total_count" +
                    " from boards b" +
                    "         inner join users u on b.user_id = u.id" +
                    "         left join follows f on b.user_id = f.receiver "
        );

        List<Object> params = new ArrayList<>();

        if( titleSearch!=null) {
            querySql.append("where title like ? ");
            params.add("%" + titleSearch + "%");
        }

        if(isFollowingBoard) {
            if(titleSearch!=null) {
                querySql.append("and f.follow_status = 'ACCEPTED' and  f.sender = ? ");
            } else {
                querySql.append("where f.follow_status = 'ACCEPTED' and  f.sender = ? ");
            }
            params.add(id);
        }

        querySql.append("order by updated_at desc ");

        querySql.append("limit ? offset ?");
        params.add(pageable.getPageSize());
        params.add(pageable.getPageSize()* pageable.getPageNumber());


        return jdbcTemplate.query(querySql.toString(), rs -> {
            List<BoardPageResponseDto> pageBoardList = new ArrayList<>();
            int total = 0;
            while(rs.next()) {
                if(total==0) {
                    total = rs.getInt("total_count");
                }
                BoardPageResponseDto pageResponseDto = new BoardPageResponseDto(
                        rs.getLong("id"),
                        rs.getString("user_name"),
                        rs.getString("title"),
                        rs.getLong("comment_count"),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
                pageBoardList.add(pageResponseDto);
            }
            return new PageImpl<>(pageBoardList, pageable,total);
        },params.toArray());
    }
}

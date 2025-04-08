package com.example.sns_feed.comment.entity;


import com.example.sns_feed.board.entity.Board;
import com.example.sns_feed.common.entity.BaseEntity;
import com.example.sns_feed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {

    }

    public Comment(String contents, Board board, User user) {
        this.contents = contents;
        this.board = board;
        this.user = user;
    }


}

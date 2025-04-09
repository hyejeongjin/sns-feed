package com.example.sns_feed.domain.board.entity;

import com.example.sns_feed.domain.comment.entity.Comment;
import com.example.sns_feed.common.entity.BaseEntity;
import com.example.sns_feed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="boards")
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> comments = new ArrayList<>();

    public Board() {

    }

    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

}

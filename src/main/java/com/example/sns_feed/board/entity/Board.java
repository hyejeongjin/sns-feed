package com.example.sns_feed.board.entity;

import com.example.sns_feed.comment.entity.Comment;
import com.example.sns_feed.common.entity.BaseEntity;
import com.example.sns_feed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToMany(mappedBy = "boards")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> comments = new ArrayList<>();

    public Board() {

    }

    public Board(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

}

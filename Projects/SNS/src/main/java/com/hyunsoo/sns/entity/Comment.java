package com.hyunsoo.sns.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;

    @Column(nullable = false)
    private String content;

    @Builder
    public Comment(Long id, User user, Post post, Comment comment, String content) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.parent = comment;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}

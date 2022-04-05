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

    @Column(nullable = false)
    private String content;

    @Builder
    public Comment(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}

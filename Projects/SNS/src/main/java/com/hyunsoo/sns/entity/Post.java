package com.hyunsoo.sns.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 1000)
    private String content;

    @Column
    private Long likes;

    @Builder
    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.likes = 0L;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseLike() {
        this.likes++;
    }

    public void decreaseLike() {
        this.likes--;
    }
}

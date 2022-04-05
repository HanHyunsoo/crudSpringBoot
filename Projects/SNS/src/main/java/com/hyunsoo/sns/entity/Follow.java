package com.hyunsoo.sns.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Follow extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    private User to;

    @Builder
    public Follow(User from, User to) {
        this.from = from;
        this.to = to;
    }
}

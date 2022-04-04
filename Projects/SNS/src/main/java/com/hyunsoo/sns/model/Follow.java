package com.hyunsoo.sns.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
@NoArgsConstructor
public class Follow extends BaseTime {

    @Id
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

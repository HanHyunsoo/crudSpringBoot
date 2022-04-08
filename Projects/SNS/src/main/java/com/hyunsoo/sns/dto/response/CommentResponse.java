package com.hyunsoo.sns.dto.response;

import com.hyunsoo.sns.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private final Long id;

    private final String comment;

    private final String writer;

    private final LocalDateTime createdTime;

    private final LocalDateTime modifiedTime;

    public CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.comment = entity.getContent();
        this.writer = entity.getUser().getUsername();
        this.createdTime = entity.getCreatedDate();
        this.modifiedTime = entity.getModifiedDate();
    }
}

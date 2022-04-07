package com.hyunsoo.sns.dto.response;

import com.hyunsoo.sns.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private final String title;

    private final String content;

    private final String writer;

    private final Long likes;

    private final LocalDateTime createdTime;

    private final LocalDateTime modifiedTime;

    public PostResponse(Post entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getUser().getUsername();
        this.likes = entity.getLikes();
        this.createdTime = entity.getCreatedDate();
        this.modifiedTime = entity.getModifiedDate();
    }
}

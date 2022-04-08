package com.hyunsoo.sns.dto.response;

import com.hyunsoo.sns.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class ParentCommentResponse extends CommentResponse {

    private final List<CommentResponse> childComments;

    public ParentCommentResponse(Comment entity, List<CommentResponse> childComments) {
        super(entity);
        this.childComments = childComments;
    }
}

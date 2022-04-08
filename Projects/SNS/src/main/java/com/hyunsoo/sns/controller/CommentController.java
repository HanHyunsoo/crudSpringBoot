package com.hyunsoo.sns.controller;

import com.hyunsoo.sns.dto.request.CommentRequest;
import com.hyunsoo.sns.dto.response.CommentResponse;
import com.hyunsoo.sns.dto.response.ParentCommentResponse;
import com.hyunsoo.sns.model.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Create
     * @param commentRequest Request body
     * @param username       Current user
     * @return 404, 200 status
     */
    @PostMapping("/comment")
    public ResponseEntity<Object> createComment(@RequestBody final CommentRequest commentRequest,
                                                @AuthenticationPrincipal String username) {

        commentService.save(commentRequest, username);

        return ResponseEntity.ok("작성 성공");
    }

    /**
     * id로 comment read
     * @param id Comment pk
     * @return 404, 200(body=commentResponse) status
     */
    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable final Long id) {
        CommentResponse commentResponse = commentService.findById(id);

        return ResponseEntity.ok(commentResponse);
    }

    /**
     * post id로 모든 comment read
     * @param postId Post pk
     * @return 404, 204, 200(body=commentResponses) status
     */
    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<ParentCommentResponse>> findAllByPost(@PathVariable final Long postId) {
        List<ParentCommentResponse> response = commentService.findAllByPostId(postId);

        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * update
     * @param id             Comment pk
     * @param username       Current user
     * @param commentRequest Request body
     * @return 404, 403, 200 status
     */
    @PatchMapping("/comment/{id}")
    public ResponseEntity<Object> update(@PathVariable final Long id,
                                         @AuthenticationPrincipal String username,
                                         @RequestBody CommentRequest commentRequest) {
        commentService.update(id, username, commentRequest);

        return ResponseEntity.ok("수정 성공");
    }

    /**
     * delete
     * @param id       Comment pk
     * @param username Current user
     * @return 404, 403, 200 status
     */
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Object> delete(@PathVariable final Long id,
                                         @AuthenticationPrincipal String username) {
        commentService.delete(id, username);

        return ResponseEntity.ok("삭제 성공");
    }
}

package com.hyunsoo.sns.controller;

import com.hyunsoo.sns.dto.request.PostRequest;
import com.hyunsoo.sns.dto.response.PostResponse;
import com.hyunsoo.sns.model.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    /**
     * post 저장
     * path = /post
     * method = POST
     * @param username    : 현재 로그인한 사용자이름
     * @param postRequest : request body
     * @return 404 or 200 status response
     */
    @PostMapping
    public ResponseEntity<Object> save(@AuthenticationPrincipal final String username,
                                       @RequestBody final PostRequest postRequest) {

        postService.save(username, postRequest);

        return ResponseEntity.ok("글 작성 성공");
    }

    /**
     * 모든 post 찾기
     * path = /post
     * method = GET
     * @return 204 or 200(body=posts) status response
     */
    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        List<PostResponse> posts = postService.findAll();

        return ResponseEntity.ok(posts);
    }

    /**
     * 특정 id로 post 찾기
     * path = /post/{id}
     * method = GET
     * @param id : post id
     * @return 404 or 200(body=post) status response
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable final Long id) {
        PostResponse post = postService.findById(id);

        return ResponseEntity.ok(post);
    }

    /**
     * username이 작성한 모든 글 찾기
     * path = /post/user/{username}
     * method = GET
     * @param username : username
     * @return 404, 204 or 200(body=posts) status response
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponse>> findByUserName(@PathVariable final String username) {
        List<PostResponse> posts = postService.findByUserName(username);

        if (posts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "해당 하는 유저의 post가 존재 하지 않습니다.");
        }

        return ResponseEntity.ok(posts);
    }

    /**
     * 해당 id의 post 작성자가 맞는지 확인하고 수정
     * @param username    : 현재 로그인 한 유저
     * @param id          : 수정하고 싶은 id
     * @param postRequest : 수정하고 싶은 내용
     * @return 404, 403 or 200 status response
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@AuthenticationPrincipal final String username,
                                         @PathVariable final Long id,
                                         @RequestBody PostRequest postRequest) {

        postService.update(username, id, postRequest);

        return ResponseEntity.ok("글 수정 성공");
    }

    /**
     * 해당 id의 post 작성자가 맞는지 확인하고 삭제
     * path = /post/{id}
     * method = DELETE
     * @param username : 현재 로그인 한 유저
     * @param id       : 삭제하고 싶은 id
     * @return 404, 403 or 200 status response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@AuthenticationPrincipal final String username,
                                         @PathVariable final Long id) {

        postService.delete(username, id);

        return ResponseEntity.ok("글 삭제 성공");
    }
}

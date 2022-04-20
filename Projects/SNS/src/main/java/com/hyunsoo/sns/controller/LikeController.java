package com.hyunsoo.sns.controller;

import com.hyunsoo.sns.dto.request.LikeRequest;
import com.hyunsoo.sns.model.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Object> like(@AuthenticationPrincipal final String username,
                                       @RequestBody final LikeRequest body) {

        likeService.like(username, body.getPostId());
        return ResponseEntity.created(URI.create(String.valueOf(body.getPostId()))).build();
    }

    @DeleteMapping
    public ResponseEntity<Object> unLike(@AuthenticationPrincipal final String username,
                                         @RequestBody final LikeRequest body) {
        likeService.unLike(username, body.getPostId());
        return ResponseEntity.ok().build();
    }

}

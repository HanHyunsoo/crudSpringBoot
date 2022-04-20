package com.hyunsoo.sns.model;

import com.hyunsoo.sns.entity.Like;
import com.hyunsoo.sns.entity.Post;
import com.hyunsoo.sns.entity.User;
import com.hyunsoo.sns.repository.LikeRepository;
import com.hyunsoo.sns.repository.PostRepository;
import com.hyunsoo.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void like(String username, Long postId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User를 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post를 찾을 수 없습니다."));

        Like like = likeRepository.findByUserAndPost(user, post).orElse(null);
        if (like != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 좋아요를 한 상태 입니다.");
        }

        post.increaseLike();
        likeRepository.save(
                Like.builder()
                        .user(user)
                        .post(post)
                        .build()
        );
    }

    @Transactional
    public void unLike(String username, Long postId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User를 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post를 찾을 수 없습니다."));

        Like like = likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "좋아요를 한 상태가 아닙니다."));

        post.decreaseLike();
        likeRepository.delete(like);
    }
}

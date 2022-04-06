package com.hyunsoo.sns.controller;

import com.hyunsoo.sns.dto.response.FollowResponse;
import com.hyunsoo.sns.model.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/follow")
@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * follow
     * @param toUserName   : 팔로우 대상
     * @param fromUserName : 팔로우 하는 사람
     * @return : 204 또는 409 http status
     */
    @PostMapping("/{username}")
    public ResponseEntity<Object> follow(@PathVariable(value = "username") String toUserName,
                                         @AuthenticationPrincipal String fromUserName) {

        boolean isNotExistFollow = followService.follow(fromUserName, toUserName);

        if (!isNotExistFollow) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 팔로우가 되있습니다.");
        }

        String successMessage = String.format("%s가 %s를 팔로우 했습니다.", fromUserName, toUserName);
        return ResponseEntity.ok(successMessage);
    }

    /**
     * unfollow
     * @param toUserName   : 언팔로우 대상
     * @param fromUserName : 언팔로우 하는 사람
     * @return : 204 또는 404 http status
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<Object> unFollow(@PathVariable(value = "username") String toUserName,
                                           @AuthenticationPrincipal String fromUserName) {

        boolean isExistFollow = followService.unFollow(fromUserName, toUserName);

        if (!isExistFollow) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("팔로우를 하지 않은 상태입니다.");
        }

        String successMessage = String.format("%s가 %s를 언팔로우 했습니다.", fromUserName, toUserName);
        return ResponseEntity.ok(successMessage);
    }

    /**
     * 해당 user의 following 반환
     * @param username : user
     * @return : 204 또는 200(body: FollowResponse) http status
     */
    @GetMapping("/{username}/following")
    public ResponseEntity<FollowResponse> getFollowingByUserName(@PathVariable String username) {
        FollowResponse followings = followService.getFollowingAllByUserName(username);

        if (followings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(followings);
    }

    /**
     * 해당 user의 follower 반환
     * @param username : user
     * @return : 204 또는 200(body: FollowResponse) http status
     */
    @GetMapping("/{username}/follower")
    public ResponseEntity<FollowResponse> getFollowerByUserName(@PathVariable String username) {
        FollowResponse followers = followService.getFollowerAllByUserName(username);

        if (followers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(followers);
    }
}

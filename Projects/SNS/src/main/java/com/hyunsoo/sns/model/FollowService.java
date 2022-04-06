package com.hyunsoo.sns.model;

import com.hyunsoo.sns.dto.response.FollowResponse;
import com.hyunsoo.sns.entity.Follow;
import com.hyunsoo.sns.entity.User;
import com.hyunsoo.sns.repository.FollowRepository;
import com.hyunsoo.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    /**
     * 팔로우 하는 메소드
     * @param fromUserName : 팔로우를 하는 사람
     * @param toUserName   : 팔로우 대상
     * @return : true = 정상적으로 함수 종료, false = 이미 팔로우가 정보가 존재해 중간에 함수 종료
     */
    @Transactional
    public boolean follow(String fromUserName, String toUserName) {
        User fromUser = userRepository.findByUsername(fromUserName)
                .orElseThrow(() -> new IllegalArgumentException("from User를 찾을 수 없습니다."));
        User toUser = userRepository.findByUsername(toUserName)
                .orElseThrow(() -> new IllegalArgumentException("to User를 찾을 수 없습니다."));

        Follow follow = followRepository.findByFromUserAndToUser(fromUser, toUser);

        // follow가 존재하면 false 반환.
        if (follow != null) {
            return false;
        }

        followRepository.save(Follow.builder()
                        .fromUser(fromUser)
                        .toUser(toUser)
                        .build()
        );

        fromUser.increaseFollowing();
        toUser.increaseFollower();

        return true;
    }

    /**
     * 언팔로우 하는 메소드
     * @param fromUserName : 언팔로우 하는 사람
     * @param toUserName   : 언팔로우 대상
     * @return : true = 정상적으로 함수 종료, false = 팔로우가 안되있어 중간에 함수 종료
     */
    @Transactional
    public boolean unFollow(String fromUserName, String toUserName) {
        User fromUser = userRepository.findByUsername(fromUserName)
                .orElseThrow(() -> new IllegalArgumentException("from User를 찾을 수 없습니다."));
        User toUser = userRepository.findByUsername(toUserName)
                .orElseThrow(() -> new IllegalArgumentException("to User를 찾을 수 없습니다."));

        Follow follow = followRepository.findByFromUserAndToUser(fromUser, toUser);

        // follow가 없으면 false를 반환
        if (follow == null) {
            return false;
        }

        followRepository.delete(follow);

        fromUser.decreaseFollowing();
        toUser.decreaseFollower();

        return true;
    }

    /**
     * 해당 user의 팔로워들을 list형태로 반환하는 메소드
     * @param username : 팔로우를 된 사람 username
     * @return : 해당 user의 팔로워 수와 username들
     */
    @Transactional(readOnly = true)
    public FollowResponse getFollowerAllByUserName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));

        List<Follow> followers = followRepository.findAllByToUser(user);
        List<String> users = followers.stream().map(x -> x.getToUser().getUsername()).collect(Collectors.toList());

        return FollowResponse.builder()
                .users(users)
                .build();
    }

    /**
     * 해당 user가 팔로우 한 사람들을 list형태로 반환하는 메소드
     * @param username : 팔로우 한 사람
     * @return 해당 user의 팔로잉 수와 username들
     */
    @Transactional(readOnly = true)
    public FollowResponse getFollowingAllByUserName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));

        List<Follow> followings = followRepository.findAllByFromUser(user);
        List<String> users = followings.stream().map(x -> x.getToUser().getUsername()).collect(Collectors.toList());

        return FollowResponse.builder()
                .users(users)
                .build();
    }
}

package com.hyunsoo.sns.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FollowResponse {

    private final long totalCount;
    private final List<String> users;

    public boolean isEmpty() {
        return totalCount == 0;
    }

    @Builder
    public FollowResponse(List<String> users) {
        this.totalCount = users.size();
        this.users = users;
    }
}

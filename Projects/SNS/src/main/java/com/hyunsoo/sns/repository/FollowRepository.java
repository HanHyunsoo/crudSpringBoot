package com.hyunsoo.sns.repository;

import com.hyunsoo.sns.entity.Follow;
import com.hyunsoo.sns.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByToUser(User toUser);

    List<Follow> findAllByFromUser(User fromUser);

    Follow findByFromUserAndToUser(User fromUser, User toUser);
}

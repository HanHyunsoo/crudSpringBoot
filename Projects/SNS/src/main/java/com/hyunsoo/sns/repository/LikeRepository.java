package com.hyunsoo.sns.repository;

import com.hyunsoo.sns.entity.Like;
import com.hyunsoo.sns.entity.Post;
import com.hyunsoo.sns.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);
}

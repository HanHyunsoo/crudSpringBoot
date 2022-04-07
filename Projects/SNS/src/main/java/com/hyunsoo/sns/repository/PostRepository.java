package com.hyunsoo.sns.repository;

import com.hyunsoo.sns.entity.Post;
import com.hyunsoo.sns.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);
}

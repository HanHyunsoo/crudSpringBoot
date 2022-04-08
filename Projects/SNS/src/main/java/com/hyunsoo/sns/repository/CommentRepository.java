package com.hyunsoo.sns.repository;

import com.hyunsoo.sns.entity.Comment;
import com.hyunsoo.sns.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 해당 post의 모든 댓글 불러오기
     * @param post : post entity
     * @return List<Comment>
     */
    List<Comment> findAllByPostAndParentIsNull(Post post);

    /**
     * 해당 post와 부모 댓글을 이용해 모든 대댓글 불러오기
     * @param post   : post entity
     * @param parent : 부모 comment entity
     * @return List<Comment>
     */
    List<Comment> findAllByPostAndParent(Post post, Comment parent);
}

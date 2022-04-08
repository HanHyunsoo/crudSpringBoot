package com.hyunsoo.sns.model;

import com.hyunsoo.sns.dto.request.CommentRequest;
import com.hyunsoo.sns.dto.response.CommentResponse;
import com.hyunsoo.sns.dto.response.ParentCommentResponse;
import com.hyunsoo.sns.entity.Comment;
import com.hyunsoo.sns.entity.Post;
import com.hyunsoo.sns.entity.User;
import com.hyunsoo.sns.repository.CommentRepository;
import com.hyunsoo.sns.repository.PostRepository;
import com.hyunsoo.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * comment를 저장하는 메소드
     * @param commentRequest Request body
     * @param writer         작성자
     */
    @Transactional
    public void save(CommentRequest commentRequest,
                     String writer) {

        // 404
        User user = userRepository.findByUsername(writer).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
        );

        // 404
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post를 찾을 수 없습니다.")
        );

        Comment comment;
        // request body에 commentId 정보가 있다면 대댓글, 없다면 댓글이다.
        if (commentRequest.getCommentId() != null) {
            // 404
            comment = commentRepository.findById(commentRequest.getCommentId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.")
            );
        } else {
            comment = null;
        }

        Comment entity = Comment.builder()
                .user(user)
                .comment(comment)
                .post(post)
                .content(commentRequest.getContent())
                .build();

        commentRepository.save(entity);
    }

    /**
     * comment를 찾고 Response 형태로 반환하는 메소드
     * @param id Comment pk
     * @return CommentResponse
     */
    @Transactional(readOnly = true)
    public CommentResponse findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.")
        );

        return new CommentResponse(comment);
    }

    /**
     * 해당 post에 있는 댓글(대댓글 포함)을 Response형태로 반환하는 메소드
     * @param postId Post pk
     * @return List<ParentCommentResponse>
     */
    @Transactional(readOnly = true)
    public List<ParentCommentResponse> findAllByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post를 찾을 수 없습니다.")
        );

        List<Comment> comments = commentRepository.findAllByPostAndParentIsNull(post);
        List<ParentCommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : comments) {
            List<CommentResponse> childComments = commentRepository.findAllByParent(comment)
                    .stream()
                    .map(CommentResponse::new)
                    .collect(Collectors.toList());

            commentResponses.add(new ParentCommentResponse(comment, childComments));
        }

        return commentResponses;
    }

    /**
     * comment를 업데이트 하는 메소드
     * @param id             Comment pk
     * @param user           Current user
     * @param commentRequest Request body
     */
    @Transactional
    public void update(Long id, String user, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.")
        );

        String writer = comment.getUser().getUsername();

        // 로그인한 사용자와 댓글 작성자가 다르면
        if (!user.equals(writer)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자가 아닙니다.");
        }

        comment.update(commentRequest.getContent());
    }

    /**
     * comment를 삭제하는 메소드
     * @param id   Comment pk
     * @param user Current user
     */
    @Transactional
    public void delete(Long id, String user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.")
        );

        String writer = comment.getUser().getUsername();

        // 로그인한 사용자와 댓글 작성자가 다르면
        if (!user.equals(writer)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자가 아닙니다.");
        }

        commentRepository.delete(comment);
    }
}

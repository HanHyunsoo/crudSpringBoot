package com.hyunsoo.sns.model;

import com.hyunsoo.sns.dto.request.PostRequest;
import com.hyunsoo.sns.dto.response.PostResponse;
import com.hyunsoo.sns.entity.Post;
import com.hyunsoo.sns.entity.User;
import com.hyunsoo.sns.repository.PostRepository;
import com.hyunsoo.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    /**
     * save 메소드
     * @param writer      : 글 쓴 사람
     * @param postRequest : request body
     */
    @Transactional
    public void save(String writer, PostRequest postRequest) {
        User user = userRepository.findByUsername(writer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User를 찾을 수 없습니다."));

        postRepository.save(
                Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .user(user)
                .build()
        );
    }

    /**
     * 모든 post를 response 형태로 변환해서 불러오는 메소드
     * @return List<PostResponse>
     */
    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        List<Post> posts = postRepository.findAll();

        if (posts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "데이터가 존재하지 않습니다.");
        }

        return posts.stream().map(PostResponse::new).collect(Collectors.toList());
    }

    /**
     * id를 이용해 post를 찾는 메소드
     * @param id : post의 id
     * @return PostResponse
     */
    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post를 찾을 수 없습니다.")
        );

        return new PostResponse(entity);
    }

    /**
     * 해당 username이 작성한 모든 post를 response형태로 바꿔서 반환하는 메소드
     * @param username : 작성자
     * @return List<PostResponse>
     */
    @Transactional(readOnly = true)
    public List<PostResponse> findByUserName(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User를 찾을 수 없습니다.")
        );

        List<Post> posts = postRepository.findAllByUser(user);

        return posts.stream().map(PostResponse::new).collect(Collectors.toList());
    }

    /**
     * post를 업데이트 하는 메소드
     * 해당 id로 존재하는 post가 없으면 404 status response를 반환하고 함수 종료
     * id로 찾은 post의 작성자가 요청한 작성자와 다르면 403 status response를 반환하고 함수 종료
     * @param writer      : 요청 작성자
     * @param id          : post id
     * @param postRequest : 수정 할 response body
     */
    @Transactional
    public void update(String writer, Long id, PostRequest postRequest) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post를 찾을 수 없습니다.")
        );

        String entityUserName = entity.getUser().getUsername();
        if (!writer.equals(entityUserName)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "유저가 일치 하지 않습니다.");
        }

        entity.update(
                postRequest.getTitle(),
                postRequest.getContent()
        );
    }

    /**
     * post를 삭제하는 메소드
     * 해당 id로 존재하는 post가 없으면 404 status response를 반환하고 함수 종료
     * id로 찾은 post의 작성자가 요청한 작성자와 다르면 403 status response를 반환하고 함수 종료
     * @param writer : 요청 작성자
     * @param id     : post id
     */
    @Transactional
    public void delete(String writer, Long id) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post를 찾을 수 없습니다.")
        );

        String entityUserName = entity.getUser().getUsername();
        if (!writer.equals(entityUserName)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "유저가 일치 하지 않습니다.");
        }

        postRepository.delete(entity);
    }
}

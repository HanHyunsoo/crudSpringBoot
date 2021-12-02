package com.board;

import com.board.domain.CommentDTO;
import com.board.service.CommentService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTests {

    @Autowired
    private CommentService commentService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void registerComments() {
        for (int i = 0; i < 21; i++) {
            CommentDTO params = new CommentDTO();
            params.setBoardIdx((long) 1654);
            params.setContent(i + "번 댓글 추가합니다");
            params.setWriter(i + "번 회원");
            commentService.registerComment(params);
        }

        logger.debug("댓글 20개 등록 완료");
    }

    @Test
    public void deleteComment() {
        commentService.deleteComment((long) 10);

        getCommentList();
    }


    @Test
    public void updateComment() {
        CommentDTO params = new CommentDTO();
        params.setIdx((long) 1);
        params.setBoardIdx((long) 1654);
        params.setContent("수정 합니다");
        params.setWriter("수정합니다");
        commentService.registerComment(params);
    }

    @Test
    public void getCommentList() {
        CommentDTO params = new CommentDTO();
        params.setBoardIdx((long) 1654);

        commentService.getCommentList(params);
    }
}

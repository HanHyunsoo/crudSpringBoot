package com.board.controller;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.service.BoardService;
import com.board.util.UiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BoardController extends UiUtils {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "board/write.do")
    public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) {
        if (idx == null) {
            model.addAttribute("board", new BoardDTO());
        } else {
            BoardDTO board = boardService.getBoardDetail(idx);

            if (board == null) {
                return "redirect:/board/list.do";
            }
            model.addAttribute("board", board);
        }

        return "board/write";
    }

    @PostMapping(value = "/board/register.do")
    public String registerBoard(@Valid final BoardDTO params, Model model) {
        try {
            boolean isRegistered = boardService.registerBoard(params);
            if (!isRegistered) {
                return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
        }

        return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list.do", Method.GET, null, model);
    }

    @GetMapping(value = "/board/list.do")
    public String openBoardList(Model model) {
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);

        return "board/list";
    }

    @GetMapping(value = "/board/view.do")
    public String openBoardDetail(@RequestParam(value = "idx", required = false) Long idx, Model model) {
        if (idx == null) {
            // TODO -> 올바르지 않은 메세지 전송, 리스트페이지로 리다이렉트
            return "redirect:/board/list.do";
        }

        BoardDTO board = boardService.getBoardDetail(idx);
        if (board == null || board.isDeleteYn()) {
            // TODO -> 없는 게시글이거나 삭제된 게시글 이라는 메세지 전송하고 리스트페이지로 리다이렉트
            return "redirect:/board/list.do";
        }

        model.addAttribute("board", board);

        return "board/view";
    }

    @PostMapping(value = "/board/delete.do")
    public String deleteBoard(@RequestParam(value = "idx", required = false) Long idx, Model model) {
        if (idx == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
        }

        try {
            boolean isDeleted = boardService.deleteBoard(idx);
            if (!isDeleted) {
                return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list.do", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
        }

        return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list.do", Method.GET, null, model);
    }
}

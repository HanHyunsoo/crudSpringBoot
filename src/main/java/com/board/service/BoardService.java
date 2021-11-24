package com.board.service;

import com.board.domain.AttachDTO;
import com.board.domain.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    boolean registerBoard(BoardDTO params);

    boolean registerBoard(BoardDTO params, MultipartFile[] files);

    BoardDTO getBoardDetail(Long idx);

    boolean deleteBoard(Long idx);

    List<BoardDTO> getBoardList(BoardDTO params);
}

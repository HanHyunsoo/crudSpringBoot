package com.board.service;

import com.board.domain.BoardDTO;

import java.util.List;

public interface BoardService {

    boolean registerBoard(BoardDTO params);

    BoardDTO getBoardDetail(Long idx);

    boolean deleteBoard(Long idx);

    List<BoardDTO> getBoardList();
    
}

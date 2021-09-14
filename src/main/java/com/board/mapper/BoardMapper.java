package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {

    int insertBoard(BoardDTO params);

    BoardDTO selectBoardDetail(Long idx);

    int updateBoard(BoardDTO params);

    int deleteBoard(Long idx);

    List<BoardDTO> selectBoardList();

    int selectBoardTotalCount();

}
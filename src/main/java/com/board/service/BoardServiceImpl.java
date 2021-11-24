package com.board.service;

import com.board.domain.AttachDTO;
import com.board.domain.BoardDTO;
import com.board.mapper.AttachMapper;
import com.board.mapper.BoardMapper;
import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;
import com.board.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private AttachMapper attachMapper;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public boolean registerBoard(BoardDTO params) {
        int queryResult;

        if (params.getIdx() == null) {
            queryResult = boardMapper.insertBoard(params);
        } else {
            queryResult = boardMapper.updateBoard(params);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean registerBoard(BoardDTO params, MultipartFile[] files) {
        int queryResult = 1;

        if (!registerBoard(params)) return false;

        List<AttachDTO> fileList = fileUtils.uploadFiles(files, params.getIdx());

        if (!CollectionUtils.isEmpty(fileList)) {
            queryResult = attachMapper.insertAttach(fileList);
            if (queryResult < 1) queryResult = 0;
        }

        return (queryResult > 0);
    }

    @Override
    public BoardDTO getBoardDetail(Long idx) {
        return boardMapper.selectBoardDetail(idx);
    }

    @Override
    public boolean deleteBoard(Long idx) {
        int queryResult = 0;

        BoardDTO boardDTO = boardMapper.selectBoardDetail(idx);
        if (boardDTO != null && !boardDTO.isDeleteYn()) {
            queryResult = boardMapper.deleteBoard(idx);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<BoardDTO> getBoardList(BoardDTO params) {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.selectBoardTotalCount(params);

        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);

        if (boardTotalCount > 0) {
            boardList = boardMapper.selectBoardList(params);
        }

        return boardList;
    }
}

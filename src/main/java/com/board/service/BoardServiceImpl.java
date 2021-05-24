package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean registerBoard(BoardVO params) {
		
		int queryResult =0;
		
		if (params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		}else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		return (queryResult == 1)? true:false;
	}

	@Override
	public BoardVO getBoardDetail(long idx) {
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(long idx) {
		int queryResult = 0;

		BoardVO board = boardMapper.selectBoardDetail(idx);

		if (board != null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = Collections.emptyList();

		int boardTotalCount = boardMapper.selectBoardTotalCount();

		if (boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}

		return boardList;
	}

}

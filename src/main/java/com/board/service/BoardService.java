package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardService {
	boolean registerBoard(BoardVO params);
	BoardVO getBoardDetail(long idx);
	boolean deleteBoard(long idx);
	List<BoardVO> getBoardList();
	
}

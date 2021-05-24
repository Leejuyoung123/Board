package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardVO;

@Mapper
public interface BoardMapper {
	 
	 int insertBoard(BoardVO params); 

	 BoardVO selectBoardDetail(Long idx);
	 
	 int updateBoard(BoardVO params);
	 
	 int deleteBoard(Long idx);
	 
	 List<BoardVO> selectBoardList();
	 
	 int selectBoardTotalCount();
}

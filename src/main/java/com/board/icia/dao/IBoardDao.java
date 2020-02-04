package com.board.icia.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;

public interface IBoardDao {

	List<Board> getBoardList(Integer pageNumber);

	Board getContents(Integer bNum);

	List<Reply> getReplyList(Integer bNum);
	
	@Select("SELECT COUNT(*) FROM BOARD_LIST_1")
	int getBoardCount();


}

package com.board.icia.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.board.icia.dto.Bfile;
import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;

public interface IBoardDao {

	List<Board> getBoardList(Integer pageNumber);

	Board getContents(Integer bNum);

	List<Reply> getReplyList(Integer bNum);
	
	@Select("SELECT COUNT(*) FROM BOARD_LIST_1")
	int getBoardCount();

	boolean replyInsert(Reply r);
	
	boolean replyDelete(Integer bNum);
	
	boolean boardDelete(Integer bNum);
	
	@Select("SELECT BOARD_NUMBER_SEQ.CURRVAL FROM DUAL")
	int getCurBoardNum();
	
	boolean boardWrite(Board board);
	
	boolean fileInsert(Map<String, String> fMap);

	@Select("SELECT * FROM BF WHERE BOARD_FILE_BOARD_NUMBER=#{bNum}")
	List<Bfile> getbfList(Integer bNum);
	@Delete("DELETE FROM BF WHERE BOARD_FILE_BOARD_NUMBER=#{bNum}")
	boolean fileDelete(Integer bNum);


}
